/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/8/03 10:20:01                           */
/*==============================================================*/


drop table if exists m_black_list;

/*==============================================================*/
/* Table: m_black_list                                       */
/*==============================================================*/
create table m_black_list
(
   id                   varchar(32) NOT NULL comment '主键id',
   user_id              varchar(32) DEFAULT NULL comment '用户id',
   black_user_id        varchar(32) DEFAULT NULL comment '被拉黑用户id',
   primary key (id)
);

alter table m_black_list comment '黑名单表';







