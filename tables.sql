SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Procedure structure for create_app_state_table
-- ----------------------------
DROP PROCEDURE IF EXISTS `create_app_state_table`;
DELIMITER ;;
CREATE DEFINER =`husky`@`%` PROCEDURE `create_app_state_table`()
  BEGIN
    SET @tbCreate = CONCAT('create table ', 'app_state_', DATE_FORMAT(NOW(), '%Y'), "(
    `id` INT (16) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	`user` VARCHAR (16) NOT NULL,
	`app_id` varchar (32) NOT NULL,
	`state` varchar (16) NOT NULL,
	`start_time` datetime NOT NULL,
	`end_time` datetime

	)ENGINE=INNODB,CHARSET=utf8,COLLATE=utf8_general_ci");

    PREPARE stmt FROM @tbCreate;
    EXECUTE stmt;
  END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for create_container_stats_table
-- ----------------------------
DROP PROCEDURE IF EXISTS `create_container_stats_table`;
DELIMITER ;;
CREATE DEFINER =`husky`@`%` PROCEDURE `create_container_stats_table`()
  BEGIN
    SET @tbCreate = CONCAT('create table ', 'container_stats_', DATE_FORMAT(NOW(), '%Y%m%d'), "(
    `id` INT (16) PRIMARY KEY AUTO_INCREMENT NOT NULL,
	`user` VARCHAR (16) NOT NULL,
	`app_id` VARCHAR (32) NOT NULL,
	`container_name` VARCHAR (64) NOT NULL,
	`fetch_time` DATETIME NOT NULL,
	`cpu_total_usage` VARCHAR (64),
	`cpu_sys_usage` VARCHAR (64),
	`pre_cpu_total_usage` VARCHAR (64),
	`pre_cpu_sys_usage` VARCHAR (64),
	`cpus` INT (4),
	`mem_usage` VARCHAR (64),
	`mem_stats_cache` VARCHAR (64),
	`mem_limit` VARCHAR (64),
	`current_cpu` INT (11),
	`current_gpu` INT (11),
	`current_memory` INT (11),
	`gpu_labels` INT (4),
	`gpu_rate_0` INT (4),
	`gpu_rate_1` INT (4),
	`gpu_rate_2` INT (4),
	`gpu_rate_3` INT (4),
	`gpu_rate_4` INT (4),
	`gpu_rate_5` INT (4),
	`gpu_rate_6` INT (4),
	`gpu_rate_7` INT (4),
	`net_i` VARCHAR (64),
	`net_o` VARCHAR (64),
	`blk_i` VARCHAR (64),
	`blk_o` VARCHAR (64)

	)ENGINE=INNODB,CHARSET=utf8,COLLATE=utf8_general_ci");

    PREPARE stmt FROM @tbCreate;
    EXECUTE stmt;
  END
;;
DELIMITER ;

-- ----------------------------
-- Event structure for create_app_state_table
-- ----------------------------
DROP EVENT IF EXISTS `create_app_state_table`;
DELIMITER ;;
CREATE EVENT `create_app_state_table`
  ON SCHEDULE
    EVERY '1' YEAR STARTS '2019-03-01 11:27:55'
DO CALL create_app_state_table
;;
DELIMITER ;

-- ----------------------------
-- Event structure for create_container_stats_table_0
-- ----------------------------
DROP EVENT IF EXISTS `create_container_stats_table_0`;
DELIMITER ;;
CREATE EVENT `create_container_stats_table_0`
  ON SCHEDULE
    EVERY '1' DAY STARTS '2019-02-23 00:00:00'
DO CALL create_container_stats_table
;;
DELIMITER ;

SET FOREIGN_KEY_CHECKS = 1;
