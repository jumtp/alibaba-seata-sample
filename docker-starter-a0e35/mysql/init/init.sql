/*!40101 SET NAMES utf8 */;
create database if not exists seata_demo;
use seata_demo;
create table if not exists user
(
    id          bigint(20) primary key comment '主键id',
    account     varchar(12) unique not null default '' comment '账号',
    username    varchar(64)        not null default '' comment '用户名',
    password    varchar(256)       not null default '' comment 'MD5加密后的密码',
    create_time datetime           not null default '2011-11-11 00:00:00' comment '创建时间',
    update_time datetime           not null default '2011-11-11 00:00:00' comment '更新时间'
) engine = InnoDB
  default charset = utf8 comment '用户表';
truncate user;
insert into user values(1, 'lam', 'LM', '[noon]password', now(),now()) ;

create table if not exists balance
(
    id          bigint(20) primary key comment '主键id',
    user_id     bigint(20) unique not null default 0 comment '用户id',
    balance     bigint(20)        not null default 0 comment '账号余额',
    create_time datetime          not null default '2011-11-11 00:00:00' comment '创建时间',
    update_time datetime          not null default '2011-11-11 00:00:00' comment '更新时间'
) engine = InnoDB
  default charset = utf8 comment '用户账号余额表';
truncate balance;
insert into balance values(1,1,100000,now(),now());

create table if not exists goods
(
    id             bigint(20) primary key comment '主键id',
    category_id    bigint(20)   not null comment '商品分类',
    name           varchar(64)  not null default '' comment '商品名称',
    description    varchar(512) not null default '' comment '商品描述',
    pic            varchar(64)  not null default '' comment '商品图片',
    price          int          not null default 1 comment '商品价格',
    sales_quantity int          not null default 0 comment '商品销售量',
    inventory      int          not null default 0 comment '商品库存',
    status         int          not null default 0 comment '商品状态',
    create_time    datetime     not null default '2011-11-11 00:00:00' comment '创建时间',
    update_time    datetime     not null default '2011-11-11 00:00:00' comment '更新时间'
) engine = InnoDB
  default charset = utf8 comment '商品表';

truncate goods;
insert into goods values(1,1001,'儿童滑板','高级木头滑板(送滑轮)','',50,0,10000,101,now(),now());
insert into goods values(2,1002,'木制手枪','高纺木制手枪(送1W颗子弹)','',200,0,10000,101,now(),now());
insert into goods values(3,1003,'自行车','越野自行车','',2000,0,0,101,now(),now());

create table if not exists `order`
(
    id           bigint(20) primary key comment '主键id',
    user_id      bigint(20)   not null comment '用户id',
    address_id   bigint(20)   not null comment '地址id',
    order_detail varchar(512) not null default '' comment '订单描述',
    create_time  datetime     not null default '2011-11-11 00:00:00' comment '创建时间',
    update_time  datetime     not null default '2011-11-11 00:00:00' comment '更新时间'
) engine = InnoDB
  default charset = utf8 comment '订单表';


#seata
CREATE TABLE `undo_log` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `branch_id` bigint(20) NOT NULL,
                            `xid` varchar(100) NOT NULL,
                            `context` varchar(128) NOT NULL,
                            `rollback_info` longblob NOT NULL,
                            `log_status` int(11) NOT NULL,
                            `log_created` datetime NOT NULL,
                            `log_modified` datetime NOT NULL,
                            `ext` varchar(100) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;