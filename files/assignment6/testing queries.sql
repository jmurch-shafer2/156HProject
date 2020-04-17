use joelm;

SELECT personId from Person where personCode = "02a";

INSERT Email(personId, email) VALUES (1, "Johnjapplesauce@yahhhhhhh.com");

SELECT * from Person 
	join Email e on Person.personId = e.personId
    where personCode = "02a";
    
    
select * from Asset;

insert Address (street, city, state, zipCode, country) values ("Chinook","Fullerton","CA",92640,"United States"); -- "Brent","Elphinston"
insert Person(personCode, addressId, firstName, lastName, brokerType, secIdentifier) values ("02a",1,"Brent","Elphinston","","");

select addressId from Address
	where street = "Chinook" && city = "Fullerton" && state = "CA";


