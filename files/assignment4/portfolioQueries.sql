

select personId as "ID", personCode as "Person Code", firstName as "First Name",lastName as "Last Name" from Person per
	join Portfolio por on por.ownerId = per.personId
    join PortfolioAsset pa on pa.portfolioId = por.portfolioId
    join Asset a on a.assetId = pa.assetId
    left join DepositAccount da on da.assetId = a.assetId
	left join PrivateInvestment pi on pi.assetId = a.assetId
	left join Stock s on s.assetId = a.assetId;


 -- 1.
select personId as "ID", personCode as "Person Code", firstName as "First Name",lastName as "Last Name" from Person;


-- 2.
select e.email as "Email" from Person p
	join Email e on e.personId = p.personId
    	where p.personId = 1;


-- 3. 
insert Email(personId,email) values (1,"championhotbeef@yahoo.com");


-- 4.
update Email set email = "championhotbeef@cse.unl.edu"
		where emailId = 18;


-- 5. -- TODO make soft deletes
delete from Email where personId = 1;
delete from Address where personId = 1;
delete from PortfolioAsset where portfolioAssetId = 1;
delete from PortfolioAsset where portfolioAssetId = 2;
delete from Portfolio where ownerId = 1; 
delete from Portfolio where managerId = 1;
delete from Portfolio where beneficiaryId = 1;
delete from Person where personId = 1;


-- 6.
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("badundam", "Rick", "Riarodian","","");


-- 7.
select * from Portfolio p 
	left join PortfolioAsset pa on pa.portfolioId = p.portfolioId
    join Asset a on pa.assetId = a.assetId
    left join DepositAccount d on d.assetId = a.assetId
    left join PrivateInvestment pi on pi.assetId = a.assetId
    left join Stock s on s.assetId = a.assetId
    where p.portfolioId = 1;


-- 8.
select * from Person pert 
	join Portfolio p on pert.personId = p.ownerId
	left join PortfolioAsset pa on pa.portfolioId = p.portfolioId
    join Asset a on pa.assetId = a.assetId
    left join DepositAccount d on d.assetId = a.assetId
    left join PrivateInvestment pi on pi.assetId = a.assetId
    left join Stock s on s.assetId = a.assetId
    where pert.personId = 1;


-- 9. --TODO do more
insert Asset (assetCode,typeOfAsset) values ("codeName","D");
insert DepositAccount(assetCode,assetId,apr,totalValue) values ("codeName",(select assetId from Asset where assetCode = "codeName"),0.95,4000000);


-- 10.
-- making new people to make a new portfolio for
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("newOwner","new","owner","","");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("newManager","new","manager","E","00000000000");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("newBeneficiary","new","hedgeFundChild","","");

-- Making the new portfolio
insert Portfolio(portfolioCode,ownerId,managerId,beneficiaryId) values ("newPortfolio",(select personId from Person where personCode = "newOwner"),(select personId from Person where personCode = "newManager"),(select personId from Person where personCode = "newBeneficiary"));


-- 11.
insert Asset (assetCode,typeOfAsset) values ("newasset","D");
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "newasset"),(select portfolioId from Portfolio where portfolioCode ="newPortfolio"));


-- 12. -- TODO add to this
select p.personId, p.firstName, p.lastName, count(por.ownerId) from Person p
	join Portfolio por on p.personId = por.ownerId
    group by por.ownerId;


-- 13.
select p.personId, p.firstName, p.lastName, count(por.managerId) from Person p
	join Portfolio por on p.personId = por.managerId
    group by por.managerId;


-- 14. -- TODO check this
select p.portfolioCode as "Portfolio Code", p.portfolioId, sum(s.sharesOwned * s.sharePrice) from Portfolio p
	join PortfolioAsset pa on p.portfolioId = pa.portfolioId
    join Asset a on a.assetId = pa.assetId
    join Stock s on s.assetId = a.assetId
    group by p.portfolioId;


-- 15.
select p.portfolioId as "Portfolio ID",a.assetCode as "Asset Code",pi.privateInvestmentId as "Private Investment Id",pi.assetCode as "Code" ,sum(pi.percentageOwned) as "Total Percentage" from Portfolio p
	join PortfolioAsset pa on p.portfolioId = pa.portfolioId
    join Asset a on a.assetId = pa.assetId
    join PrivateInvestment pi on pi.assetId = a.assetId
    where 100 > (select sum(pi.percentageOwned) from Portfolio p
					join PortfolioAsset pa on p.portfolioId = pa.portfolioId
					join Asset a on a.assetId = pa.assetId
					join PrivateInvestment pi on pi.assetId = a.assetId);










