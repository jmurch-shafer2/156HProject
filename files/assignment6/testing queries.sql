use joelm;

SELECT personId from Person where personCode = "02a";

INSERT Email(personId, email) VALUES (1, "Johnjapplesauce@yahhhhhhh.com");

SELECT * from Person 
	join Email e on Person.personId = e.personId
    where personCode = "02a";
    
    
select * from Asset;

select typeOfAsset,label,quarterlyDividend,baseRateReturn,baseOmegaMeasure,totalValue,apr,betaMeasure,stockSymbol,sharePrice from Asset where assetCode = 003;


-- select typeOfAsset,label,quarterlyDividend,baseRateReturn,baseOmegaMeasure,totalValue,apr,betaMeasure,stockSymbol,sharePrice from Asset where assetCode = ?

insert Address (street, city, state, zipCode, country) values ("Chinook","Fullerton","CA",92640,"United States"); -- "Brent","Elphinston"
insert Person(personCode, addressId, firstName, lastName, brokerType, secIdentifier) values ("02a",1,"Brent","Elphinston","","");

select addressId from Address
	where street = "Chinook" && city = "Fullerton" && state = "CA";

-- PortfolioData.addPerson("code", "first", "last", "street", "city", "state", "68008", "country", "brokerType", "secBrokerId");
insert Address (street, city, state, zipCode, country) values ("street","city","state",68008,"country");

select addressId from Address
	where street = "street" && city = "city" && state = "state";

insert Person(personCode, addressId, firstName, lastName, brokerType, secIdentifier) values ("code",12,?,?,?,?);

select * from Asset where assetCode = "codename 007";

-- private investment
update Asset set percentageOwned = 32 where assetId = 18;

-- deposit account
update Asset set totalValue = 32 where assetId = 18;

-- stock
update Asset set sharesOwned = 32 where assetId = 18;

select * from Portfolio;
select * from PortfolioAsset;

select assetId,typeOfAsset from Asset where assetCode = "AGTSAV" && totalValue = 26534.21;

select * from Asset;
select assetId from Asset where assetcode = "BSS007" && percentageOwned = null && totalValue = null && sharesOwned = null;

select percentageOwned,sharesOwned,label,quarterlyDividend,baseRateReturn,baseOmegaMeasure,totalValue,apr,betaMeasure,stockSymbol,sharePrice from Asset where assetCode = 1;



select * from Person;
