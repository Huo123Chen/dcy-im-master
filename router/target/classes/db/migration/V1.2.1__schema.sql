ALTER TABLE `m_off_p2p_mes`
MODIFY COLUMN `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息' AFTER `receive_user_id`;

ALTER TABLE `m_off_group_mes`
MODIFY COLUMN `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息' AFTER `group_id`;