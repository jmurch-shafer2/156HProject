use joelm;

select addressId, street, city, state, zipCode, country from Address where addressId = ?;

select personId, personCode, addressId, firstName, lastName, brokerType, secIdentifier from Person p
	where personId = 3;
select emailId, email from Email
	where personId = 3;
select addressId, street, city, state, zipCode, country from Address
	where addressId = 3;

select portfolioId, portfolioCode, ownerId, managerId, beneficiaryId from Portfolio;

select assetId from Portfolio p
	left join PortfolioAsset pa on p.portfolioId = pa.portfolioId
    where p.portfolioId = 2;
    
	
select * from Asset a 
	left join DepositAccount da on a.assetId = da.assetId
    left join Stock s on a.assetId = s.assetId
    left join PrivateInvestment pi on a.assetId = pi.assetId;
    
select * from Asset;

select * from Portfolio;
select * from Person;