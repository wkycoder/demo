
CREATE TABLE `account` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `username` varchar(50) DEFAULT NULL COMMENT '用户名',
   `balance` decimal(50,0) DEFAULT NULL COMMENT '账户余额',
   `created_by` int(11) DEFAULT NULL COMMENT '创建人',
   `last_updated_by` int(11) DEFAULT NULL COMMENT '更新人',
   `created_at` datetime DEFAULT NULL COMMENT '创建时间',
   `last_updated_at` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户表';
INSERT INTO `account` VALUES (1,'zhangsan',100,NULL,NULL,NULL,NULL),(2,'huandu',500,NULL,NULL,NULL,NULL),(3,'wky',0,NULL,NULL,NULL,NULL),(4,'wky',0,NULL,NULL,NULL,NULL);

CREATE TABLE `red` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `total_amount` int(11) DEFAULT NULL COMMENT '红包的总金额',
   `remaining_amount` int(11) DEFAULT NULL COMMENT '红包的剩余金额',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
INSERT INTO `red` VALUES (1,100,100);


CREATE TABLE `send_record` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `receiver` varchar(50) DEFAULT NULL COMMENT '消息接收者',
   `content` varchar(255) DEFAULT NULL COMMENT '消息内容',
   `fail_msg` varchar(255) DEFAULT NULL COMMENT '失败信息',
   `status` int(2) DEFAULT NULL COMMENT '状态，0表示失败，1表示成功',
   `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
   `created_at` datetime DEFAULT NULL COMMENT '创建时间',
   `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
   `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO `send_record` VALUES (16,'wky','账号创建成功',NULL,NULL,NULL,'2022-01-25 12:57:24',NULL,'2022-01-25 12:57:24');


CREATE TABLE `student` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `name` varchar(20) DEFAULT NULL COMMENT '姓名',
   `age` int(10) DEFAULT NULL COMMENT '年龄',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO `student` VALUES (1,'zhangsan',18),(4,'zhangsan',18),(5,'zhangsan',18),(6,'zhangsan',18);


CREATE TABLE `tb_order` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `amount` decimal(11,0) DEFAULT NULL COMMENT '订单金额',
    `user_id` int(11) DEFAULT NULL COMMENT '所属用户ID',
    `deleted` int(2) DEFAULT NULL COMMENT '是否删除，0表示未删除，1表示已删除',
    `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
    `created_at` datetime DEFAULT NULL COMMENT '创建时间',
    `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `tb_user` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
   `name` varchar(50) DEFAULT NULL COMMENT '用户名',
   `myname` varchar(50) DEFAULT NULL COMMENT 'xxx',
   `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
   `deleted` int(2) DEFAULT NULL COMMENT '是否删除，0表示未删除，1表示已删除',
   `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
   `created_at` datetime DEFAULT NULL COMMENT '创建时间',
   `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
   `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO `tb_user` VALUES (1,'zs','xx','123456',0,NULL,NULL,NULL,NULL);


CREATE TABLE `test_uuid` (
     `uuid` varchar(255) NOT NULL COMMENT 'uuid',
     `code` varchar(255) DEFAULT NULL COMMENT 'code',
     `type` varchar(1) DEFAULT NULL COMMENT 'type',
     PRIMARY KEY (`uuid`),
     KEY `code` (`code`),
     KEY `idx_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO `test_uuid` VALUES ('222','rrr','1'),('555','yyy','0'),('555fff','fee','0'),('888ee','dddd','1'),('dde','ggg','中');













