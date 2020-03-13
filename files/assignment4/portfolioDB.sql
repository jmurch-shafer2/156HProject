

-- This RDB was created by Joel Murch-Shafer and Natalie Ruckman 
-- This DB holds infomation to model the all assets, people and portfolios 
-- held by TBF banking

drop table if exists Stock;
drop table if exists DepositAccount;
drop table if exists PrivateInvestment;
drop table if exists PortfolioAsset;
drop table if exists Portfolio;
drop table if exists Address;
drop table if exists Email;
drop table if exists Person;
drop table if exists Asset;


create table Person(
	personId int not null primary key auto_increment,
    personCode varchar(255) not null unique,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    brokerType varchar(255),
    secIdentifier varchar(255)    
);
create table Email(
	emailId int not null primary key auto_increment,
	email varchar(255) not null,
    personId int not null,
    foreign key (personId) references Person(personId)
);

create table Address(
	addressId int not null primary key auto_increment,
    personId int not null,
    street varchar(255) not null,
    city varchar(255) not null,
    state varchar(255) not null,
    zipCode int not null,
    country varchar(255),
	foreign key (personId) references Person(personId)
);

create table Asset(
	assetId int not null primary key auto_increment,
    assetCode varchar(255) not null unique,
	typeOfAsset varchar(255) not null
);

create table Portfolio(
	portfolioId int not null primary key auto_increment,
    portfolioCode varchar(255) not null unique,
    ownerId int not null,
    managerId int not null,
    beneficiaryId int,
    foreign key (ownerId) references Person(personId),
    foreign key (managerId) references Person(personId),
    foreign key (beneficiaryId) references Person(personId)
);

create table PortfolioAsset(
	portfolioAssetId int not null primary key auto_increment,
	assetId int not null,
	portfolioId int not null,
	foreign key (assetId) references Asset(assetId),
	foreign key (portfolioId) references Portfolio(portfolioId)
);

create table DepositAccount(
	depositAccountId int not null primary key auto_increment,
    assetCode varchar(255) not null unique,
	assetId int not null,
    apr int not null,
    totalValue double not null,
    foreign key (assetId) references Asset(assetId)
);

create table PrivateInvestment(
	privateInvestmentId int not null primary key auto_increment,
    assetCode varchar(255) not null unique,
	assetId int not null,
    name varchar(255),
    baseRateReturn double not null,
    quarterlyDividend double not null,
    baseOmegaMeasure double not null,
    totalValue double not null,
    percentageOwned double not null,
    foreign key (assetId) references Asset(assetId)
);

create table Stock(
	stockId int not null primary key auto_increment,
    assetCode varchar(255) not null unique,
    label varchar(255) not null,
	assetId int not null,
    stockSymbol varchar(255) not null,
    sharesOwned int not null,
    quarterlyDividend double not null,
    sharePrice double not null,
    betaMeasure double not null,
    baseRateReturn double not null,
    foreign key (assetId) references Asset(assetId)
);

-- Person
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("02a","Brent","Elphinston","","");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("005","O'Shevlin","Maurits","","");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("02b","Tregona","Jaquenette","","");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("02c","Hazelgrove","Waylan","","");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("02d","Sancias","Fifi","","");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("014","Gniewosz","Morissa","E","005");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("025","Braxay","Ty","E","006");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("00a","Cooke","Devy","J","001");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("040","Bamforth","Francene","J","009");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("03e","Feltham","Axel","","");
insert Person(personCode, firstName, lastName, brokerType, secIdentifier) values ("03f","Johnsey","Lu","","");

-- Email
insert Email(personId,email) values((select personId from Person where personCode = "02a"),"belphinston11@taobao.com"); -- "Brent","Elphinston"
insert Email(personId,email) values((select personId from Person where personCode = "02a"),"belphinston11@cbc.ca"); -- "Brent","Elphinston"
insert Email(personId,email) values((select personId from Person where personCode = "005"),"moshevlin0@netscape.com"); -- "O'Shevlin","Maurits"
insert Email(personId,email) values((select personId from Person where personCode = "02b"),"jtregona12@noaa.gov"); -- "Tregona","Jaquenette"
insert Email(personId,email) values((select personId from Person where personCode = "02b"),"jtregona12@java.com"); -- "Tregona","Jaquenette"
insert Email(personId,email) values((select personId from Person where personCode = "02c"),"whazelgrove13@goo.gl"); -- "Hazelgrove","Waylan"
insert Email(personId,email) values((select personId from Person where personCode = "02c"),"whazelgrove13@samsung.com"); -- "Hazelgrove","Waylan"
insert Email(personId,email) values((select personId from Person where personCode = "02d"),"fsancias14@ovh.net"); -- "Sancias","Fifi"
insert Email(personId,email) values((select personId from Person where personCode = "02d"),"fsancias14@cdbaby.com"); -- "Sancias","Fifi"
insert Email(personId,email) values((select personId from Person where personCode = "014"),"mgniewoszf@livejournal.com"); -- "Gniewosz","Morissa"
insert Email(personId,email) values((select personId from Person where personCode = "025"),"tbraxayw@house.gov"); -- "Braxay","Ty"
insert Email(personId,email) values((select personId from Person where personCode = "025"),"tbraxayw@sbwire.com"); -- "Braxay","Ty"
insert Email(personId,email) values((select personId from Person where personCode = "00a"),"dcooke5@imgur.com"); -- "Cooke","Devy"
insert Email(personId,email) values((select personId from Person where personCode = "00a"),"dcooke5@purevolume.com"); -- "Cooke","Devy"
-- "Bamforth","Francene" no emails
insert Email(personId,email) values((select personId from Person where personCode = "03e"),"afeltham1l@4shared.com"); -- "Feltham","Axel"
insert Email(personId,email) values((select personId from Person where personCode = "03f"),"ljohnsey1m@about.com"); -- "Johnsey","Lu"

-- Address
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "02a"),"Chinook","Fullerton","CA",92640,"United States"); -- "Brent","Elphinston"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "005"),"Barby","Columbus","GA",31914,"United States"); -- "O'Shevlin","Maurits"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "02b"),"Emmet","Memphis","TN",38126,"United States"); -- "Tregona","Jaquenette"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "02c"),"Eastlawn","Amarillo","TX",79165,"United States"); -- "Hazelgrove","Waylan"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "02d"),"Boyd","Phoenix","AZ",85010,"United States"); -- "Sancias","Fifi"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "014"),"Morrow","Fort Wayne","IN",46814,"United States"); -- "Gniewosz","Morissa"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "025"),"Chinook","Newark","NJ",07188,"United States"); -- "Braxay","Ty"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "00a"),"Basil","Albany","GA",31704,"United States"); -- "Cooke","Devy"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "040"),"Namekagon","Fort Wayne","IN",46805,"United States"); -- "Bamforth","Francene"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "03e"),"Paget","Tulsa","OK",74116,"United States"); -- "Feltham","Axel"
insert Address (personId, street, city, state, zipCode, country) values ((select personId from Person where personCode = "03f"),"Pleasure","Des Moines","IA",50347,"United States"); -- "Johnsey","Lu"

-- Asset
insert Asset (assetCode,typeOfAsset) values ("003","D");
insert Asset (assetCode,typeOfAsset) values ("033","S");
insert Asset (assetCode,typeOfAsset) values ("03c","S");
insert Asset (assetCode,typeOfAsset) values ("034","S");
insert Asset (assetCode,typeOfAsset) values ("028","S");
insert Asset (assetCode,typeOfAsset) values ("029","S");
insert Asset (assetCode,typeOfAsset) values ("02d","S");
insert Asset (assetCode,typeOfAsset) values ("004","D");
insert Asset (assetCode,typeOfAsset) values ("00a","D");
insert Asset (assetCode,typeOfAsset) values ("005","D");
insert Asset (assetCode,typeOfAsset) values ("00e","D");
insert Asset (assetCode,typeOfAsset) values ("02c","S");
insert Asset (assetCode,typeOfAsset) values ("00c","D");
insert Asset (assetCode,typeOfAsset) values ("021","S");
insert Asset (assetCode,typeOfAsset) values ("008","D");
insert Asset (assetCode,typeOfAsset) values ("03e","S");
insert Asset (assetCode,typeOfAsset) values ("0ff","P");

-- Portfolio
insert Portfolio(portfolioCode,ownerId,managerId,beneficiaryId) values ("000",(select personId from Person where personCode = "02a"),(select personId from Person where personCode = "014"),(select personId from Person where personCode = "03e"));
insert Portfolio(portfolioCode,ownerId,managerId,beneficiaryId) values ("010",(select personId from Person where personCode = "005"),(select personId from Person where personCode = "025"),(select personId from Person where personCode = "03e"));
insert Portfolio(portfolioCode,ownerId,managerId,beneficiaryId) values ("020",(select personId from Person where personCode = "02b"),(select personId from Person where personCode = "00a"),(select personId from Person where personCode = "03e"));
insert Portfolio(portfolioCode,ownerId,managerId,beneficiaryId) values ("030",(select personId from Person where personCode = "02c"),(select personId from Person where personCode = "040"),(select personId from Person where personCode = "03f"));
insert Portfolio(portfolioCode,ownerId,managerId,beneficiaryId) values ("040",(select personId from Person where personCode = "02d"),(select personId from Person where personCode = "040"),(select personId from Person where personCode = "03f"));


-- PortfolioAsset
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "003"),(select portfolioId from Portfolio where portfolioCode ="000"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "033"),(select portfolioId from Portfolio where portfolioCode ="000"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "004"),(select portfolioId from Portfolio where portfolioCode ="010"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "00a"),(select portfolioId from Portfolio where portfolioCode ="010"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "03c"),(select portfolioId from Portfolio where portfolioCode ="010"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "005"),(select portfolioId from Portfolio where portfolioCode ="020"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "034"),(select portfolioId from Portfolio where portfolioCode ="020"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "00e"),(select portfolioId from Portfolio where portfolioCode ="020"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "02c"),(select portfolioId from Portfolio where portfolioCode ="020"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "02d"),(select portfolioId from Portfolio where portfolioCode ="020"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "028"),(select portfolioId from Portfolio where portfolioCode ="030"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "00c"),(select portfolioId from Portfolio where portfolioCode ="030"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "021"),(select portfolioId from Portfolio where portfolioCode ="030"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "029"),(select portfolioId from Portfolio where portfolioCode ="040"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "008"),(select portfolioId from Portfolio where portfolioCode ="040"));
insert PortfolioAsset(assetId,portfolioId) values ((select assetId from Asset where assetCode = "03e"),(select portfolioId from Portfolio where portfolioCode ="040"));

-- DepositAccount
insert DepositAccount(assetCode,assetId,apr,totalValue) values ("004",(select assetId from Asset where assetCode = "004"),0.95,4000000);
insert DepositAccount(assetCode,assetId,apr,totalValue) values ("00a",(select assetId from Asset where assetCode = "00a"),0.75,500);
insert DepositAccount(assetCode,assetId,apr,totalValue) values ("005",(select assetId from Asset where assetCode = "005"),0.18,500);
insert DepositAccount(assetCode,assetId,apr,totalValue) values ("00e",(select assetId from Asset where assetCode = "00e"),0.57,1599);
insert DepositAccount(assetCode,assetId,apr,totalValue) values ("003",(select assetId from Asset where assetCode = "003"),0.31,79000);
insert DepositAccount(assetCode,assetId,apr,totalValue) values ("00c",(select assetId from Asset where assetCode = "00c"),0.05,7855);
insert DepositAccount(assetCode,assetId,apr,totalValue) values ("008",(select assetId from Asset where assetCode = "008"),0.83,23000);

-- PrivateInvestment
insert PrivateInvestment(name,assetCode,assetId,baseRateReturn,quarterlyDividend,baseOmegaMeasure,totalValue,percentageOwned) values ("Living Wake, The","0ff",(select assetId from Asset where assetCode = "033"),06.95,366285.46,0.17,6662000000,14);


-- Stock 
insert Stock(assetCode,assetId,stockSymbol,sharesOwned,quarterlyDividend,sharePrice,betaMeasure,baseRateReturn, label) values ("033", (select assetId from Asset where assetCode = "033"), "IMQJ",17,95.57,80.08,0.44,08.40,"Arena Pharmaceuticals, Inc.");
insert Stock(assetCode,assetId,stockSymbol,sharesOwned,quarterlyDividend,sharePrice,betaMeasure,baseRateReturn, label) values ("03c", (select assetId from Asset where assetCode = "03c"), "CLIM",654,68.79,43.11,0.24,03.29,"Guggenheim Enhanced Equity Income Fun");
insert Stock(assetCode,assetId,stockSymbol,sharesOwned,quarterlyDividend,sharePrice,betaMeasure,baseRateReturn, label) values ("034", (select assetId from Asset where assetCode = "034"), "AJDM",31,31.90,72.40,0.26,00.37,"KKR and Co. L.P.");
insert Stock(assetCode,assetId,stockSymbol,sharesOwned,quarterlyDividend,sharePrice,betaMeasure,baseRateReturn, label) values ("028", (select assetId from Asset where assetCode = "028"), "KJEJ",3,12.72,31.46,0.62,04.61,"Aircastle Limited");
insert Stock(assetCode,assetId,stockSymbol,sharesOwned,quarterlyDividend,sharePrice,betaMeasure,baseRateReturn, label) values ("029", (select assetId from Asset where assetCode = "029"), "QRIW",16,64.12,46.65,0.92,07.88,"Dillards, Inc.");
insert Stock(assetCode,assetId,stockSymbol,sharesOwned,quarterlyDividend,sharePrice,betaMeasure,baseRateReturn, label) values ("02d", (select assetId from Asset where assetCode = "02d"), "ZHRL",76,96.85,65.66,0.64,02.35,"NCI, Inc.");
insert Stock(assetCode,assetId,stockSymbol,sharesOwned,quarterlyDividend,sharePrice,betaMeasure,baseRateReturn, label) values ("03e", (select assetId from Asset where assetCode = "03e"), "YJDT",22,52.44,77.54,0.07,06.10,"Safe Bulkers Inc");
insert Stock(assetCode,assetId,stockSymbol,sharesOwned,quarterlyDividend,sharePrice,betaMeasure,baseRateReturn, label) values ("02c", (select assetId from Asset where assetCode = "02c"), "RJVX",56,19.27,31.34,0.18,00.19,"Nuveen Ohio Quality Municipal Income Fund");
insert Stock(assetCode,assetId,stockSymbol,sharesOwned,quarterlyDividend,sharePrice,betaMeasure,baseRateReturn, label) values ("021", (select assetId from Asset where assetCode = "021"), "GFDZ",5,21.57,42.13,0.45,00.90,"Zafgen, Inc.");