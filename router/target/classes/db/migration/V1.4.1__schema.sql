/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/8/07 10:20:01                           */
/*==============================================================*/

ALTER TABLE m_group_user ADD user_role int(1) default 1 comment '群组角色 0:群主 1:群组成员';
ALTER TABLE m_group_user ADD is_talk int(1) default 1 comment '是否禁言 0:禁言 1:正常';
ALTER TABLE m_group_user ADD username varchar(50) default '' comment '用户名';

/*==============================================================*/
/* 群组测试数据：设置群组1中用户1为群主                            */
/*==============================================================*/
UPDATE m_group_user SET user_role = 0 WHERE group_id = 1 AND user_id = 1;

UPDATE m_group_user SET username = 'zhangsan' WHERE user_id = 1;
UPDATE m_group_user SET username = 'lisi' WHERE user_id = 2;
UPDATE m_group_user SET username = 'wangwu' WHERE user_id = 3;
















