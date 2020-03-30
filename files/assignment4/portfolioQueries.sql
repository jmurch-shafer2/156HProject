-- This RDB was created by Joel Murch-Shafer and Natalie Ruckman 
-- These queries are for assignment 4

 -- 1. A query to retrieve the major Fields for every person
select p.personId as "ID", p.personCode as "Person Code", p.firstName as "First Name", p.lastName as "Last Name", e.email as "Email", a.Street as "Street", a.City as "City", a.state as "State" from Person p
	join Address a on a.addressId = p.addressId
    join Email e on e.personId = p.personId;


-- 2. A query to retrieve the email(s) of a given person
select e.email as "Email" from Person p
	join Email e on e.personId = p.personId
    	where p.personId = 1;


-- 3. A query to add an email to a specifc person
insert Email(personId,email) values (1,"championhotbeef@yahoo.com");


-- 4. A query to change the email address of a given email record
update Email set email = "championhotbeef@cse.unl.edu"
		where emailId = 18;


-- 5. A query (or series of queries) to remove a given person record
delete from Email where personId = 1;
delete from PortfolioAsset where portfolioAssetId = 1;
delete from PortfolioAsset where portfolioAssetId = 2;
delete from Portfolio where ownerId = 1; 
update Portfolio set managerId = null 
	where managerId = 1;
delete from Portfolio where beneficiaryId = 1;
delete from Person where personId = 1;
delete from Address where addressId = (select addressId from Person where personId = 1);

-- 6. A query to create a person record
insert Person(personCode, addressId, firstName, lastName, brokerType, secIdentifier) values ("badundam", 1, "Rick", "Riarodian","","");


-- 7. A query to get all the assets in a particular portfolio
select p.portfolioId as "Portfolio ID", p.portfolioCode as "Code", p.ownerId as "Owner ID", a.assetId as "Asset ID", a.assetCode as "Asset Code", a.typeOfAsset as "Type of Asset", d.totalValue as "Total Value", pi.totalValue as "Total Value", pi.percentageOwned as "Percentage Owned", s.stockSymbol as "Stock Symbol", s.sharePrice as "Share Price", s.sharesOwned as "Shares Owned" from Portfolio p 
	left join PortfolioAsset pa on pa.portfolioId = p.portfolioId
    join Asset a on pa.assetId = a.assetId
    left join DepositAccount d on d.assetId = a.assetId
    left join PrivateInvestment pi on pi.assetId = a.assetId
    left join Stock s on s.assetId = a.assetId
    where p.portfolioId = 2;


-- 8. A query to get all the assets of a particular person
select pert.personId as "Owner ID", pert.personCode as "Person Code", a.assetId as "Asset ID", a.assetCode as "Asset Code", a.typeOfAsset as "Type of Asset", d.totalValue as "Total Value", pi.totalValue as "Total Value", pi.percentageOwned as "Percentage Owned", s.stockSymbol as "Stock Symbol", s.sharePrice as "Share Price", s.sharesOwned as "Shares Owned" from Person pert 
	join Portfolio p on pert.personId = p.ownerId
	left join PortfolioAsset pa on pa.portfolioId = p.portfolioId
    join Asset a on pa.assetId = a.assetId
    left join DepositAccount d on d.assetId = a.assetId
    left join PrivateInvestment pi on pi.assetId = a.assetId
    left join Stock s on s.assetId = a.assetId
    where pert.personId = 2;


-- 9. A query to create a new asset record
insert Asset (assetCode,typeOfAsset) values ("codeName","D");
insert DepositAccount(assetCode,assetId,apr,totalValue) values ("codeName",(select assetId from Asset where assetCode = "codeName"),0.95,4000000);


-- 10. A query to create a new portfolio record
-- making new people to make a new portfolio for
insert Person(personCode, addressId, firstName, lastName, brokerType, secIdentifier) values ("newOwner",1,"new","owner","","");
insert Person(personCode, addressId, firstName, lastName, brokerType, secIdentifier) values ("newManager",1,"new","manager","E","00000000000");
insert Person(personCode, addressId, firstName, lastName, brokerType, secIdentifier) values ("newBeneficiary",1,"new","hedgeFundChild","","");
-- Making the new portfolio
insert Portfolio(portfolioCode,ownerId,managerId,beneficiaryId) values ("newPortfolio",(select personId from Person where personCode = "newOwner"),(select personId from Person where personCode = "newManager"),(select personId from Person where personCode = "newBeneficiary"));


-- 11. A query to associate a particular asset with a particular portfolio
insert Asset (assetCode,typeOfAsset) values ("newasset","D");
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "newasset"),(select portfolioId from Portfolio where portfolioCode ="newPortfolio"));


-- 12. A query to find the total number of portfolios owned by each person
select p.personId as "Person Code", p.firstName as "First Name", p.lastName as "Last Name", count(por.ownerId) as "Number of Portfolios per Person" from Person p
	left join Portfolio por on p.personId = por.ownerId
    group by por.ownerId;


-- 13. A query to find the total number of portfolios managed by each person
select p.personId as "Person ID", p.firstName as "First Name", p.lastName as "Last Name", count(por.managerId) as "Number of Portfolios Managed" from Person p
	left join Portfolio por on p.personId = por.managerId
    group by por.managerId;


-- 14. A query to find the total value of all stocks in each portfolio
select p.portfolioCode as "Portfolio Code", p.portfolioId as "Portfolio ID", sum(s.sharesOwned * s.sharePrice) as "Total Value" from Portfolio p
	join PortfolioAsset pa on p.portfolioId = pa.portfolioId
    join Asset a on a.assetId = pa.assetId
    join Stock s on s.assetId = a.assetId
    group by p.portfolioId;


-- 15. A query to detect an invalid distribution of private investment assets
select p.portfolioId as "Portfolio ID",a.assetCode as "Asset Code",pi.privateInvestmentId as "Private Investment ID",pi.assetCode as "Code" ,sum(pi.percentageOwned) as "Total Percentage" from Portfolio p
	join PortfolioAsset pa on p.portfolioId = pa.portfolioId
    join Asset a on a.assetId = pa.assetId
    join PrivateInvestment pi on pi.assetId = a.assetId
    where 100 < (select sum(pi.percentageOwned) from Portfolio p
					join PortfolioAsset pa on p.portfolioId = pa.portfolioId
					join Asset a on a.assetId = pa.assetId
					join PrivateInvestment pi on pi.assetId = a.assetId);

