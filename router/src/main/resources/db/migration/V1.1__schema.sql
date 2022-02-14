/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/22 15:32:20                           */
/*==============================================================*/


drop table if exists m_group;

drop table if exists m_group_user;

drop table if exists m_user;

drop table if exists m_user_friend;

/*==============================================================*/
/* Table: m_group                                               */
/*==============================================================*/
create table m_group
(
   id                   varchar(32) not null comment '主键id',
   name                 varchar(50) comment '组名称',
   primary key (id)
);

alter table m_group comment '群组表';

/*==============================================================*/
/* Table: m_group_user                                          */
/*==============================================================*/
create table m_group_user
(
   group_id             varchar(32) comment '群组id',
   user_id              varchar(32) comment '用户id'
);

alter table m_group_user comment '组用户表';

/*==============================================================*/
/* Table: m_user                                                */
/*==============================================================*/
create table m_user
(
   id                   varchar(32) not null comment '主键id',
   username             varchar(50) comment '用户名',
   password             varchar(200) comment '密码',
   phone                varchar(50) comment '手机号',
   pic                  varchar(200) comment '头像',
   primary key (id)
);

alter table m_user comment '用户表';

/*==============================================================*/
/* Table: m_user_friend                                         */
/*==============================================================*/
create table m_user_friend
(
   user_id              varchar(32) comment '用户id',
   friend_id            varchar(32) comment '好友id'
);

alter table m_user_friend comment '用户好友表';

