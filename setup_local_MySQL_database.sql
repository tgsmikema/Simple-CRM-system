CREATE SCHEMA `super_chat_pal_crm`;
USE `super_chat_pal_crm`;


CREATE TABLE `login` (
		  `user_id` int NOT NULL AUTO_INCREMENT,
		  `email` varchar(45) NOT NULL,
		  `password` varchar(45) NOT NULL,
		  `full_name` varchar(100) NOT NULL,
		  `auth_level` int NOT NULL,
		  PRIMARY KEY (`user_id`),
		  UNIQUE KEY `email_UNIQUE` (`email`),
		  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
		) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `contacts` (
  `contact_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone_or_mobile` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `address_line_1` text,
  `address_line_2` text,
  `city` varchar(45) DEFAULT NULL,
  `state_or_county` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `description` mediumtext,
  `industry` varchar(300) DEFAULT NULL,
  `company` varchar(300) DEFAULT NULL,
  `job_title` varchar(300) DEFAULT NULL,
  `created_by` varchar(100) NOT NULL,
  `created_date_and_time` timestamp NOT NULL,
  `contact_source` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE KEY `contact_id_UNIQUE` (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



CREATE TABLE `leads` (
  `contact_id` int NOT NULL,
  `lead_source` varchar(300) DEFAULT NULL,
  `lead_status` varchar(45) NOT NULL,
  `if_lost_reasons` varchar(300) DEFAULT NULL,
  `lead_created_by` varchar(45) NOT NULL,
  `lead_created_date_and_time` timestamp NOT NULL,
  `assigned_to` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  UNIQUE KEY `contact_id_UNIQUE` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `tasks` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `contact_id` int NOT NULL,
  `task_type` varchar(300) NOT NULL,
  `task_summary` mediumtext,
  `task_description` mediumtext,
  `task_created_by` varchar(100) NOT NULL,
  `task_created_date_and_time` timestamp NOT NULL,
  `task_assigned_to` varchar(100) DEFAULT NULL,
  `due_date_and_time` timestamp NULL DEFAULT NULL,
  `priority` varchar(45) DEFAULT NULL,
  `progress` int DEFAULT NULL,
  `task_current_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `task_id_UNIQUE` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `activities` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `contact_id` int NOT NULL,
  `activity_type` varchar(200) NOT NULL,
  `activity_summary` mediumtext,
  `activity_description` mediumtext,
  `activity_created_by` varchar(100) NOT NULL,
  `activity_created_date_and_time` timestamp NOT NULL,
  PRIMARY KEY (`activity_id`),
  UNIQUE KEY `activity_id_UNIQUE` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `temp_data` (
  `user_id` int NOT NULL DEFAULT '-1',
  `contact_id` int NOT NULL DEFAULT '-1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `contact_id_UNIQUE` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO login VALUE (DEFAULT,'@admin','root','ADMIN',9);


