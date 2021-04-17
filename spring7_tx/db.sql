#创建mysql库
create database testBank;
use testBank;
#账号信息表
create table accounts
(
    accountid int primary key auto_increment,
    balance   numeric(10, 2)
);
#创建流水表
create table oprecord
(
    id         int primary key auto_increment,
    accountid  int references accounts (accountid),
    opmoney    numeric(10, 2),
    optime     datetime,
    optype     enum ('deposite','withdraw','transfer') not null,
    transferid varchar(50)
);

#使用触发器来完成数据检测
CREATE TRIGGER after_balance_update
    after update
    ON accounts
    FOR EACH ROW
BEGIN
    IF (NEW.balance < 0) THEN
        update accounts set balance=OLD.balance where accountid = OLD.accountid;
    END IF;
END;
#插入测试数据测试触发器是否OK
insert into accounts(balance)
values (1);
#更新金额，会发现后台报错.
update accounts
set balance=balance - 1
where accountid = 1;

update accounts
set balance=balance + 1
where accountid = 1;
select *
from accounts;


insert into accounts(balance)
values (10)