use joelm;

drop table if exists Portfolio;
create table Portfolio(
	portfolioId int not null primary key auto_increment
);

drop table if exists Person;
create table Person(
	personId int not null primary key auto_increment,
    portfolioId int not null, 
    firstName varchar(50),
    lastName varchar(50),
    personType varchar(1),
    brokerType varchar(1),
    secIdentifier varchar(10),
    foreign key (portfolioId) references Portfolio(portfolioId)
);

drop table if exists Email;
create table Email(
	emailId int not null primary key auto_increment,
	email varchar(100),
    personId int not null,
    foreign key (personId) references Person(personId)
);

drop table if exists Address;
create table Address(
	addressId int not null primary key auto_increment,
    personId int,
    street varchar(50),
    city varchar(50),
    zipCode int,
    country varchar(50),
	foreign key (personId) references Person(personId)
);

drop table if exists DepositAccount;
create table DepositAccount(
	depositAccountId int not null primary key auto_increment,
	portfolioId int,
    apr int,
    value double,
    foreign key (portfolioId) references Portfolio(portfolioId)
);

drop table if exists PrivateInvestment;
create table PrivateInvestment(
	privateInvestmentId int not null primary key auto_increment,
	portfolioId int,
    baseRateReturn double,
    quarterlyDividend double,
    baseOmegaMeasure double,
    totalValue double,
    percentageOwned double,
    foreign key (portfolioId) references Portfolio(portfolioId)
);

drop table if exists Stock;
create table Stock(
	stockId int not null primary key auto_increment,
	portfolioId int,
    sharesOwned int,
    quarterlyDividend double,
    sharePrice double,
    betaMeasure double,
    baseRateReturn double,
    foreign key (portfolioId) references Portfolio(portfolioId)
);



