use joelm;

drop table if exists Stock;
drop table if exists DepositAccount;
drop table if exists PrivateInvestment;
drop table if exists Portfolio;
drop table if exists Address;
drop table if exists Email;
drop table if exists Person;
drop table if exists PortfolioAsset;
drop table if exists Asset;

create table Person(
	personId int not null primary key auto_increment,
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
    zipCode int not null,
    country varchar(255),
	foreign key (personId) references Person(personId)
);

create table Asset(
	assetId int not null primary key auto_increment, 
	typeOfAsset varchar(255) not null
);

create table PortfolioAsset(
	portfolioAssetId int not null primary key auto_increment,
	assetId int not null,
	portfolioId int not null,
	foreign key (assetId) references Asset(assetId),
	foreign key (portfolioId) references Portfolio(portfolioId)
);

create table Portfolio(
	portfolioId int not null primary key auto_increment,
    ownerId int not null,
    managerId int not null,
    beneficiaryId int,
    foreign key (beneficiaryId) references Person(personId),
    foreign key (ownerId) references Person(personId),
    foreign key (managerId) references Person(personId)
);

create table DepositAccount(
	depositAccountId int not null primary key auto_increment,
	assetId int not null,
    apr int not null,
    totalValue double not null,
    foreign key (assetId) references Asset(assetId)
);

create table PrivateInvestment(
	privateInvestmentId int not null primary key auto_increment,
	assetId int not null,
    baseRateReturn double not null,
    quarterlyDividend double not null,
    baseOmegaMeasure double not null,
    totalValue double not null,
    percentageOwned double not null,
    foreign key (assetId) references Asset(assetId)
);

create table Stock(
	stockId int not null primary key auto_increment,
	assetId int not null,
    stockSymbol varchar(255) not null,
    sharesOwned int not null,
    quarterlyDividend double not null,
    sharePrice double not null,
    betaMeasure double not null,
    baseRateReturn double not null,
    foreign key (assetId) references Asset(assetId)
);

insert Person(firstName, lastName, brokerType, secIdentifier) values ("Brent","Elphinston","","");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("O'Shevlin","Maurits","","");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("Tregona","Jaquenette","","");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("Hazelgrove","Waylan","","");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("Sancias","Fifi","","");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("Gniewosz","Morissa","E","005");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("Braxay","Ty","E","006");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("Cooke","Devy","J","001");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("Bamforth","Francene","J","009");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("Feltham","Axel","","");
insert Person(firstName, lastName, brokerType, secIdentifier) values ("Johnsey","Lu","","");

insert Email(personId,email) values();

insert Address
