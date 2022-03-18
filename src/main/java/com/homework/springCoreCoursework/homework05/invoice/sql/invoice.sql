/*建立商品項目*/
DROP TABLE IF EXISTS ItemProduct;
create table ItemProduct(
	id INTEGER not null auto_increment,
	text VARCHAR(50) not null,
	price INTEGER not null,
	inventory INTEGER not null,
	primary key(id)
);
--新增商品
insert into itemproduct (text, price, inventory) values('Pen', 10, 20);
insert into itemproduct (text, price, inventory) values('Book', 15, 50);
insert into itemproduct (text, price, inventory) values('Toy', 20, 40);
/*建立發票*/
DROP TABLE IF EXISTS Invoice;
create table Invoice(
	id INTEGER not null auto_increment,
	invdate Date not null,
	primary key(id)
);
--新增發票
insert into invoice (invdate) values ('2020-11-23');
insert into invoice (invdate) values ('2020-11-22');
insert into invoice (invdate) values ('2020-11-21');
--刪除某個column
alter table invoice 
drop column price
/*發票項目*/
DROP TABLE IF EXISTS Item;
create table Item(
	id INTEGER not null auto_increment,
	amount INTEGER not null,
	ipid INTEGER not null,
	invid INTEGER not null,
	primary key(id),
	foreign key (ipid) references itemproduct(id),
	foreign key (invid) references invoice(id)
);
--新增發票項目
insert into item (amount, ipid, invid) values (5, 1, 1);
insert into item (amount, ipid, invid) values (3, 2, 1);
insert into item (amount, ipid, invid) values (4, 1, 2);
insert into item (amount, ipid, invid) values (1, 3, 2);
insert into item (amount, ipid, invid) values (6, 2, 3);