CREATE TABLE `m_equipment` (
  `id` varchar(36) NOT NULL COMMENT '主键id',
  `equipment` varchar(200) DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备表';