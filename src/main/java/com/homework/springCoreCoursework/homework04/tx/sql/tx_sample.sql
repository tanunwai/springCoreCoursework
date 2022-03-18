-- 交易 TX (Transcation) 所需要的資料表
-- 建立：book (書籍資料)、stock（庫存資料）、wallet（客戶雲端錢包）
-- 建立 book (書籍資料)
create table if not exists book(
	bid integer not null auto_increment,
	bname varchar(20) not null,
	price integer default 0,
	ct timestamp default current_timestamp,
	primary key (bid) -- 主鍵
);
-- 建立 stock（庫存資料）
create table if not exists stock(
	sid integer not null auto_increment,
	bid integer not null, -- book (書籍資料) 的 id
	amount integer default 0,
	primary key (sid), -- 主鍵
	foreign key(bid) references book(bid) -- 外鍵關聯
);
-- 建立 wallet（客戶雲端錢包）
create table if not exists wallet(
	wid integer not null auto_increment,
	wname varchar(20) not null,
	money integer default 0,
	primary key (wid) -- 主鍵
);
--建立 order_log(訂單記錄)
create table if not exists order_log(
	o_id integer not null auto_increment,
	p_id integer not null,
	u_id integer not null,
    receiverinfo varchar(20) not null,
    pname varchar(20) not null,
    totalprice double not null,
    quantity integer not null,
    ordertime varchar(50) not null,
	primary key (o_id),
	foreign key (p_id) references book (bid),
	foreign key (u_id) references wallet (wid)
);
--新增book資料
insert into book(bname, price) values ('java', 150);
insert into book(bname, price) values (python, 100);
--新增stock資料
insert into stock(bid, amount) values (1, 10);
insert into stock(bid, amount) values (2, 10);
--新增wallet
insert into wallet (wname, money) values ('Webster', 500);
insert into wallet (wname, money) values ('vincent', 1000);