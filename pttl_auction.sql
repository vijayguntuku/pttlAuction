/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.30 : Database - pttl_auction
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pttl_auction` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `pttl_auction`;

/*Table structure for table `bid` */

DROP TABLE IF EXISTS `bid`;

CREATE TABLE `bid` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` varchar(50) DEFAULT NULL,
  `end_time` varchar(50) DEFAULT NULL,
  `player_id` int DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `issold` tinyint(1) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_team_id` (`team_id`),
  KEY `fk_palyer_id` (`player_id`),
  CONSTRAINT `fk_team_id` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bid` */

/*Table structure for table `config` */

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config` (
  `id` int DEFAULT NULL,
  `base_price` double DEFAULT NULL,
  `min_palyer_count` int DEFAULT NULL,
  `allotment_amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `config` */

insert  into `config`(`id`,`base_price`,`min_palyer_count`,`allotment_amount`) values 
(1,100000,7,10000000);

/*Table structure for table `player` */

DROP TABLE IF EXISTS `player`;

CREATE TABLE `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `auction_price` double DEFAULT NULL,
  `team_id` int NOT NULL,
  `base_price` double DEFAULT NULL,
  `isSold` bit(1) DEFAULT NULL,
  `isCaptain` bit(1) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_team_id1` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `player` */

insert  into `player`(`id`,`name`,`email`,`phone_no`,`auction_price`,`team_id`,`base_price`,`isSold`,`isCaptain`,`image`) values 
(4,'vamshi','vamshi@gmail.com','9381743675',100000,1,0,'\0','\0',NULL),
(5,'vamshi','vamshi@gmail.com','9381743675',100000,2,0,'\0','\0',NULL);

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `id` int NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `balance_amount` double DEFAULT NULL,
  `captain_id` int DEFAULT NULL,
  `player_count` int DEFAULT NULL,
  `base_price` double DEFAULT NULL,
  `min_player_count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_captain_id` (`captain_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `team` */

insert  into `team`(`id`,`name`,`balance_amount`,`captain_id`,`player_count`,`base_price`,`min_player_count`) values 
(1,'lions',100000,1,5,100000,6),
(2,'tiger',10000,2,3,10000,6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
