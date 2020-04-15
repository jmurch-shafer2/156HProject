use joelm;

SELECT personId from Person where personCode = "02a";

INSERT Email(personId, email) VALUES (1, "Johnjapplesauce@yahhhhhhh.com");

SELECT * from Person 
	join Email e on Person.personId = e.personId
    where personCode = "02a";
    
    
select * from Asset;