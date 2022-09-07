/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.29 : Database - pttl_auction
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

/*Table structure for table `player` */

DROP TABLE IF EXISTS `player`;

CREATE TABLE `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `aution_price` double DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `base_price` double DEFAULT NULL,
  `isSold` tinyint(1) DEFAULT NULL,
  `isCaptain` tinyint(1) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_team_id` (`team_id`),
  CONSTRAINT `fk_team_id` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `player` */

insert  into `player`(`id`,`name`,`email`,`phone_no`,`aution_price`,`team_id`,`base_price`,`isSold`,`isCaptain`,`image`) values 
(1,'Vamshi','vamshi@gmail.com','9982734839',NULL,1,100000,NULL,NULL,NULL),
(2,'Vijay','vijay@gmail.com','9381838293',NULL,NULL,100000,NULL,NULL,NULL),
(3,'Abhinav','Abhinavu@gmail.com','9987278721',NULL,NULL,NULL,NULL,NULL,NULL),
(4,'Adhitya','Adhitya@gmail.com','8898389929',NULL,NULL,NULL,NULL,NULL,NULL),
(5,'Anusha','Anusha@gmail.com','7827787881',NULL,NULL,NULL,NULL,NULL,NULL),
(6,'Varsha','Varsha@gmail.com','9388392928',NULL,NULL,NULL,NULL,NULL,NULL),
(7,'Jaya','Jaya@gmail.com','7884883838',NULL,NULL,NULL,NULL,NULL,NULL),
(8,'Shah','Shah@gmail.com','8477483884',NULL,NULL,NULL,NULL,NULL,NULL),
(9,'Kavya','Kavya@gmail.com','8837277323',NULL,NULL,NULL,NULL,NULL,NULL),
(10,'Sagar','Sagar@gmail.com','8883822773',NULL,NULL,NULL,NULL,NULL,NULL),
(11,'Raju','Raju@gmail.com','9927637272',NULL,NULL,NULL,NULL,NULL,NULL),
(12,'Raghu','Raghu@gmail.com','7642772837',NULL,NULL,NULL,NULL,NULL,NULL),
(13,'Vinod','Viond@gmail.com','9777367723',NULL,NULL,NULL,NULL,NULL,NULL),
(14,'Hanshu','Hanshu@gmail.com','9777377234',NULL,NULL,NULL,NULL,NULL,NULL),
(15,'Vedha','Vedha@gmail.com','8737627743',NULL,NULL,NULL,NULL,NULL,NULL),
(16,'Venkat','Venkat@gmail.com','7747737366',NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `id` int NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `balance_amount` double DEFAULT NULL,
  `captain_id` int DEFAULT NULL,
  `player_count` int DEFAULT NULL,
  `alloted_amount` double DEFAULT NULL,
  `max_palyer_count` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_captain_id` (`captain_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `team` */

insert  into `team`(`id`,`name`,`balance_amount`,`captain_id`,`player_count`,`alloted_amount`,`max_palyer_count`) values 
(1,'lions',10000000,1,2,10000000,4),
(2,'tiger',10000000,2,2,10000000,4),
(3,'cheetha',10000000,3,3,10000000,4),
(4,'Shark',10000000,6,1,10000000,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
