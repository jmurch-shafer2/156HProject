use joelm;

drop table if exists Stock;
drop table if exists DepositAccount;
drop table if exists PrivateInvestment;
drop table if exists Portfolio;
drop table if exists Address;
drop table if exists Email;
drop table if exists Person;

create table Person(
	personId int not null primary key auto_increment,
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    brokerType varchar(1),
    secIdentifier varchar(10)    
);
create table Email(
	emailId int not null primary key auto_increment,
	email varchar(100) not null,
    personId int not null,
    foreign key (personId) references Person(personId)
);

create table Address(
	addressId int not null primary key auto_increment,
    personId int not null,
    street varchar(50) not null,
    city varchar(50) not null,
    zipCode int not null,
    country varchar(50),
	foreign key (personId) references Person(personId)
);

create table Portfolio(
	portfolioId int not null primary key auto_increment,
    ownerId int not null,
    managerId int not null,
    beneficiaryId int,
    foreign key (ownerId) references Person(personId),
    foreign key (managerId) references Person(personId)
);



create table DepositAccount(
	depositAccountId int not null primary key auto_increment,
	portfolioId int not null,
    apr int not null,
    totalValue double not null,
    foreign key (portfolioId) references Portfolio(portfolioId)
);

create table PrivateInvestment(
	privateInvestmentId int not null primary key auto_increment,
	portfolioId int not null,
    baseRateReturn double not null,
    quarterlyDividend double not null,
    baseOmegaMeasure double not null,
    totalValue double not null,
    percentageOwned double not null,
    foreign key (portfolioId) references Portfolio(portfolioId)
);

create table Stock(
	stockId int not null primary key auto_increment,
	portfolioId int not null,
    stockSymbol varchar(10) not null,
    sharesOwned int not null,
    quarterlyDividend double not null,
    sharePrice double not null,
    betaMeasure double not null,
    baseRateReturn double not null,
    foreign key (portfolioId) references Portfolio(portfolioId)
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
