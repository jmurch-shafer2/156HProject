use joelm;

select addressId, street, city, state, zipCode, country from Address where addressId = ?;
