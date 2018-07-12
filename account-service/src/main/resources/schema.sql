 DROP TABLE IF EXISTS customers;
 DROP TABLE IF EXISTS account;
 DROP TABLE IF EXISTS transaction;

create table customers
(
   customerId BIGINT primary key,
   name varchar(255) not null,
   surName varchar(255) not null
);

create table account
(
   accountId BIGINT primary key,
   balance double,
   accountType varchar(255) not null,
   customerId BIGINT not null,
   foreign key(customerId) REFERENCES customers (customerId)
);

create table transaction
(
   transactionId BIGINT primary key,
   balance double not null,
   accountId BIGINT not null,
   date varchar(255) not null,
   transactionType varchar(255) not null,
   foreign key(accountId) REFERENCES account (accountId)
);
insert into customers values(333333333,'Brad','Pritkin');
insert into account values(555555555,5000,'primary',333333333);