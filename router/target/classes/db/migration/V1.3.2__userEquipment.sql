CREATE TABLE `m_user_equipment` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `equipment_id` varchar(36) DEFAULT NULL COMMENT '设备id',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户id',
  `logging_status` varchar(255) DEFAULT NULL COMMENT '登录状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户设备登录状态表';