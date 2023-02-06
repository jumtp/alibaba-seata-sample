create database if not exists seata_demo;
use seata_demo;
create table if not exists user(
                                   id bigint(20) primary key comment '主键id',
                                   account varchar(12) unique not null default '' comment '账号',
                                   username varchar(64) not null default '' comment '用户名',
                                   password varchar(256) not null default '' comment 'MD5加密后的密码',
                                   create_time datetime not null default '2011-11-11 00:00:00' comment '创建时间',
                                   update_time datetime not null default '2011-11-11 00:00:00' comment '更新时间'
) engine=InnoDB default charset=utf8 comment '用户表';

create table if not exists balance(
                                      id bigint(20) primary key comment '主键id',
                                      user_id bigint(20) unique not null default 0 comment '用户id',
                                      balance bigint(20) not null default 0 comment '账号余额',
                                      create_time datetime not null default '2011-11-11 00:00:00' comment '创建时间',
                                      update_time datetime not null default '2011-11-11 00:00:00' comment '更新时间'
) engine=InnoDB default charset=utf8 comment '用户账号余额表';

create table if not exists address(
                                      id bigint(20) primary key comment '主键id',
                                      user_id bigint(20) not null default 1 comment '用户id',
                                      nick_name varchar(64) not null default '' comment '收件人',
                                      phone varchar(64) not null default '' comment '手机号',
                                      province varchar(64) not null default '' comment '省',
                                      city varchar(64) not null default '' comment '市',
                                      address varchar(256) not null default '' comment '详细地址',
                                      create_time datetime not null default '2011-11-11 00:00:00' comment '创建时间',
                                      update_time datetime not null default '2011-11-11 00:00:00' comment '更新时间'
) engine=InnoDB default charset=utf8 comment '用户地址表';

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
    status         int      not null default 0 comment '商品状态',
    create_time    datetime     not null default '2011-11-11 00:00:00' comment '创建时间',
    update_time    datetime     not null default '2011-11-11 00:00:00' comment '更新时间'
) engine = InnoDB
  default charset = utf8 comment '商品表';


# Seata
CREATE TABLE `undo_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20)   NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11)      NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;