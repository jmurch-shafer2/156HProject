use joelm;

select addressId, street, city, state, zipCode, country from Address where addressId = ?;

select personId, personCode, addressId, firstName, lastName, brokerType, secIdentifier from Person p
	where personId = 3;
select emailId, email from Email
	where personId = 3;
select addressId, street, city, state, zipCode, country from Address
	where addressId = 3;

select * from Person;