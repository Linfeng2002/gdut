/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.28 : Database - chatsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chatsystem` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `chatsystem`;

/*Table structure for table `chathistory` */

DROP TABLE IF EXISTS `chathistory`;

CREATE TABLE `chathistory` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parentId` int unsigned NOT NULL COMMENT '父类id，可以是群可以是人',
  `content` varchar(5000) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `pic` longblob COMMENT '图片',
  PRIMARY KEY (`id`),
  UNIQUE KEY `parentId` (`parentId`),
  CONSTRAINT `chathistory_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

/*Data for the table `chathistory` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '用户名账号',
  `password` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '用户名密码',
  `online` int NOT NULL DEFAULT '0' COMMENT '是否在线',
  `friends` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '好友id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`online`,`friends`) values (1,'123456','666',0,'2,3'),(2,'123456789','666',0,'1,3'),(3,'123','666',0,'1,2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
