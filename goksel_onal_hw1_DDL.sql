drop table if exists Customers;
drop table if exists Branches;
drop table if exists Products;
drop table if exists Purchases;
drop table if exists Stocks;

create table Customers
( 
	cid          int          not null auto_increment,
	cname        varchar(30)  not null,
	surname      varchar(30)  not null,
    caddress     varchar(30)  not null,
    phoneNumber  varchar(11)  not null,
    primary key(cid)
);

create table Branches 
(
	bid       int          not null auto_increment,
	bname     varchar(30)  not null,
    baddress  varchar(30)  not null,
    primary key(bid)
);

create table Products
(
    pid             int           not null auto_increment,
	pname           varchar(30)   not null,
    description     varchar(50)   not null,
    price           numeric(6,2)  not null,
    primary key(pid)
);

create table Purchases
(
	purchaseid   int not null auto_increment,
	cid          int not null,
	bid          int not null,
	pid          int not null,
    pquantity    int not null,
    orderDate    date not null,
    primary key (purchaseid,cid,bid,pid),
    foreign key (cid) references customers(cid),
	foreign key (bid) references branches(bid),
	foreign key (pid) references products(pid)
);

create table Stocks
(
	bid         int not null,
	pid         int not null,
    squantity   int not null,
	primary key (bid,pid),
	foreign key (bid) references branches(bid),
	foreign key (pid) references products(pid)
);


