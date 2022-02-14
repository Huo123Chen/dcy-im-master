ALTER TABLE m_user ADD nick_name  varchar(256) comment '昵称';
ALTER TABLE m_user ADD account  varchar(64) comment '账号';
ALTER TABLE m_user ADD address  varchar(256) comment '地址';
ALTER TABLE m_user ADD sex  char(1) comment '性别';
ALTER TABLE m_user ADD personalized_signature varchar(256) comment '个性签名';
ALTER TABLE m_user ADD quick_mark varchar(256) comment '二维码';
ALTER TABLE m_user comment '用户表';