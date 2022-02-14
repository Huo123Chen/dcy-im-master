/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/30 14:26:01                           */
/*==============================================================*/


drop table if exists m_off_group_mes;

drop table if exists m_off_p2p_mes;

/*==============================================================*/
/* Table: m_off_group_mes                                       */
/*==============================================================*/
create table m_off_group_mes
(
   id                   varchar(32) not null comment '主键id',
   user_id              varchar(32) comment '用户id',
   group_id             varchar(32) comment '群id',
   message              varchar(500) comment '消息',
   send_time            datetime comment '发送时间',
   primary key (id)
);

alter table m_off_group_mes comment '离线群聊消息表';

/*==============================================================*/
/* Table: m_off_p2p_mes                                         */
/*==============================================================*/
create table m_off_p2p_mes
(
   id                   varchar(32) not null comment '主键id',
   user_id              varchar(32) comment '用户id',
   receive_user_id      varchar(32) comment '接收人id',
   message              varchar(500) comment '消息',
   send_time            datetime comment '发送时间',
   primary key (id)
);

alter table m_off_p2p_mes comment '离线单聊消息表';

