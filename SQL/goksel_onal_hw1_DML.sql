# customers
insert into Customers (cname, surname, caddress, phoneNumber) values ('Seçkin', 'Ağırbaş', 'Alibeyköy', '05001112233');
insert into Customers (cname, surname, caddress, phoneNumber) values ('Mahmut', 'Acar'   , 'Kartal'   , '05002221133');
insert into Customers (cname, surname, caddress, phoneNumber) values ('Merve' , 'Özkan'  , 'Beşiktaş' , '05003332211');
insert into Customers (cname, surname, caddress, phoneNumber) values ('Aysel' , 'Tekin'  , 'Kadıköy'  , '05002223311');
insert into Customers (cname, surname, caddress, phoneNumber) values ('Orhan' , 'Yavuz'  , 'Ataşehir' , '05003331122');

#branches
insert into Branches (bname,baddress) values ('Alibeyköy Center', 'Alibeyköy');
insert into Branches (bname,baddress) values ('Kadıköy Center'  , 'Kadıköy');
insert into Branches (bname,baddress) values ('Kadıköy Pier'    , 'Kadıköy');
insert into Branches (bname,baddress) values ('Beşiktaş Square' , 'Beşiktaş');
insert into Branches (bname,baddress) values ('Taksim Square'   , 'Taksim');

#products
insert into Products (pname, description,price) values ('FoodHorse Olive Oil', 'FoodHorse brand olive oil 1L', 11.50);
insert into Products (pname, description,price) values ('FoodHorse Rice', 'FoodHorse brand rice 2.5kg', 13.75);
insert into Products (pname, description,price) values ('FoodHorse Milk', 'FoodHorse brand whole milk 1L', 3.75);
insert into Products (pname, description,price) values ('FoodHorse Kosher Dill Pickles', 'FoodHorse brand pickles 680g', 4.75);
insert into Products (pname, description,price) values ('FoodHorse Strawberry Jam', 'FoodHorse Strawberry Jam 380g', 6.45);

#stocks for branch 1
insert into Stocks (bid,pid,squantity) values (1,1,55);
insert into Stocks (bid,pid,squantity) values (1,2,30);
insert into Stocks (bid,pid,squantity) values (1,3,41);
insert into Stocks (bid,pid,squantity) values (1,5,45);

#stocks for branch 2
insert into Stocks (bid,pid,squantity) values (2,1,70);
insert into Stocks (bid,pid,squantity) values (2,3,67);
insert into Stocks (bid,pid,squantity) values (2,4,56);
insert into Stocks (bid,pid,squantity) values (2,5,44);

#stocks for branch 3
insert into Stocks (bid,pid,squantity) values (3,1,68);
insert into Stocks (bid,pid,squantity) values (3,4,28);
insert into Stocks (bid,pid,squantity) values (3,5,23);

#stocks for branch 4
insert into Stocks (bid,pid,squantity) values (4,2,81);
insert into Stocks (bid,pid,squantity) values (4,3,85);
insert into Stocks (bid,pid,squantity) values (4,4,72);


#stocks for branch 5
insert into Stocks (bid,pid,squantity) values (5,1,61);
insert into Stocks (bid,pid,squantity) values (5,2,34);
insert into Stocks (bid,pid,squantity) values (5,5,63);


