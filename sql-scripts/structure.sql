CREATE DATABASE  IF NOT EXISTS `U04ioP` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `U04ioP`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 192.168.1.121    Database: U04ioP
-- ------------------------------------------------------
-- Server version	5.5.57-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressId` int(10) NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `address2` varchar(50) NOT NULL,
  `cityId` int(10) NOT NULL,
  `postalCode` varchar(10) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `address_view`
--

DROP TABLE IF EXISTS `address_view`;
/*!50001 DROP VIEW IF EXISTS `address_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `address_view` AS SELECT 
 1 AS `addressid`,
 1 AS `address`,
 1 AS `address2`,
 1 AS `cityId`,
 1 AS `city`,
 1 AS `countryId`,
 1 AS `country`,
 1 AS `postalCode`,
 1 AS `phone`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointmentId` int(10) NOT NULL AUTO_INCREMENT,
  `customerId` int(10) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `location` text NOT NULL,
  `contact` text NOT NULL,
  `url` varchar(255) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`appointmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `appointment_view`
--

DROP TABLE IF EXISTS `appointment_view`;
/*!50001 DROP VIEW IF EXISTS `appointment_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `appointment_view` AS SELECT 
 1 AS `appointmentid`,
 1 AS `customerId`,
 1 AS `title`,
 1 AS `description`,
 1 AS `location`,
 1 AS `contact`,
 1 AS `url`,
 1 AS `start`,
 1 AS `end`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `cityId` int(10) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `countryId` int(10) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `city_old`
--

DROP TABLE IF EXISTS `city_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city_old` (
  `cityId` int(10) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `countryId` int(10) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `city_view`
--

DROP TABLE IF EXISTS `city_view`;
/*!50001 DROP VIEW IF EXISTS `city_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `city_view` AS SELECT 
 1 AS `cityId`,
 1 AS `city`,
 1 AS `countryid`,
 1 AS `country`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `countryId` int(10) NOT NULL AUTO_INCREMENT,
  `country` varchar(50) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `country_old`
--

DROP TABLE IF EXISTS `country_old`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country_old` (
  `countryId` int(10) NOT NULL AUTO_INCREMENT,
  `country` varchar(50) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerId` int(10) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(45) NOT NULL,
  `addressId` int(10) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `createDate` datetime NOT NULL,
  `createdBy` varchar(40) NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdateBy` varchar(40) NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `customer_view`
--

DROP TABLE IF EXISTS `customer_view`;
/*!50001 DROP VIEW IF EXISTS `customer_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `customer_view` AS SELECT 
 1 AS `customerid`,
 1 AS `customerName`,
 1 AS `addressid`,
 1 AS `address`,
 1 AS `address2`,
 1 AS `cityid`,
 1 AS `city`,
 1 AS `postalCode`,
 1 AS `countryid`,
 1 AS `country`,
 1 AS `phone`,
 1 AS `active`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `incrementtypes`
--

DROP TABLE IF EXISTS `incrementtypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incrementtypes` (
  `incrementTypeId` int(11) NOT NULL,
  `incrementTypeDescription` varchar(45) NOT NULL,
  PRIMARY KEY (`incrementTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reminder`
--

DROP TABLE IF EXISTS `reminder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reminder` (
  `reminderId` int(10) NOT NULL,
  `reminderDate` datetime NOT NULL,
  `snoozeIncrement` int(11) NOT NULL,
  `snoozeIncrementTypeId` int(11) NOT NULL,
  `appointmentId` int(10) NOT NULL,
  `createdBy` varchar(45) NOT NULL,
  `createdDate` datetime NOT NULL,
  `remindercol` varchar(45) NOT NULL,
  PRIMARY KEY (`reminderId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `active` tinyint(4) NOT NULL,
  `createBy` varchar(40) NOT NULL,
  `createDate` datetime NOT NULL,
  `lastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdatedBy` varchar(50) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'U04ioP'
--
/*!50003 DROP PROCEDURE IF EXISTS `add_customer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`192.168.1.%` PROCEDURE `add_customer`(
	IN custName VARCHAR(45), 
    IN addrId INT,
    IN isActive TINYINT(1),
    OUT custId INT)
BEGIN
    INSERT INTO customer(
		customerName,
        addressid,
        active)
	VALUES (custName, addrId, isActive);
    SET custId = LAST_INSERT_ID();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `fix_addresses` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`192.168.1.%` PROCEDURE `fix_addresses`()
BEGIN



DECLARE loop0_eof BOOLEAN DEFAULT FALSE; 

		declare aid int;
      declare countryName varchar(50);
	  declare cityName varchar(50);
       
      DECLARE cur0 CURSOR FOR SELECT addressId, city, country 
								FROM address
                                Inner join city on address.cityId=city.cityId
                                inner join country on city.countryId=country.countryId
                                order by addressId asc;
                 

      DECLARE CONTINUE HANDLER FOR NOT FOUND SET loop0_eof = TRUE; 

      OPEN cur0; 
			ALTER TABLE `city` DISABLE KEYS;
            ALTER TABLE `country` DISABLE KEYS;
            loop0: LOOP 
                  FETCH cur0 INTO aid, cityName, countryName; 
             
                  IF loop0_eof THEN 
                        LEAVE loop0; 
                  END IF; 

				  insert into country_tmp (countryId, country) values (aid, countryName);
                  INSERT INTO city_tmp (cityId, countryId, city) VALUES (aid, aid, cityName); 
                  update address set cityId=aid where addressId = aid;

            END LOOP loop0; 
            ALTER TABLE `city` Enable KEYS;
            ALTER TABLE `country` Enable KEYS;

      CLOSE cur0; 






END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `remove_customer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`192.168.1.%` PROCEDURE `remove_customer`(IN custId INT)
BEGIN
	DECLARE addrId INT;
    DECLARE ctyId INT;
    DECLARE cntryId INT;
    SELECT addressid INTO addrId
		FROM customer WHERE customerId=custId;
	SELECT cityid INTO ctyId
		FROM address WHERE addressid=addrId;
	SELECT countryid INTO cntryId
		FROM city WHERE cityid=ctyId;
	DELETE FROM customer WHERE customerId=custId;
    DELETE FROM address WHERE addressid=addrId;
    DELETE FROM city WHERE cityid=ctyId;
    DELETE FROM country WHERE countryid=cntryId;
    DELETE FROM appointment WHERE customerId=custId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `reportNumApptTypesByMonth` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`192.168.1.%` PROCEDURE `reportNumApptTypesByMonth`(IN user VARCHAR(50), IN apptYear INT)
BEGIN
SELECT 
    MONTH(start) AS apptMonth, title, COUNT(*) AS subtotal
FROM
    appointment
WHERE YEAR(start)=apptYear
GROUP BY contact, apptMonth, title
HAVING contact = user
ORDER BY apptMonth;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `address_view`
--

/*!50001 DROP VIEW IF EXISTS `address_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`192.168.1.%` SQL SECURITY DEFINER */
/*!50001 VIEW `address_view` AS select `address`.`addressId` AS `addressid`,`address`.`address` AS `address`,`address`.`address2` AS `address2`,`city`.`cityId` AS `cityId`,`city`.`city` AS `city`,`country`.`countryId` AS `countryId`,`country`.`country` AS `country`,`address`.`postalCode` AS `postalCode`,`address`.`phone` AS `phone` from ((`address` left join `city` on((`address`.`cityId` = `city`.`cityId`))) left join `country` on((`city`.`countryId` = `country`.`countryId`))) order by `address`.`addressId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `appointment_view`
--

/*!50001 DROP VIEW IF EXISTS `appointment_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`192.168.1.%` SQL SECURITY DEFINER */
/*!50001 VIEW `appointment_view` AS select `appointment`.`appointmentId` AS `appointmentid`,`appointment`.`customerId` AS `customerId`,`appointment`.`title` AS `title`,`appointment`.`description` AS `description`,`appointment`.`location` AS `location`,`appointment`.`contact` AS `contact`,`appointment`.`url` AS `url`,`appointment`.`start` AS `start`,`appointment`.`end` AS `end` from `appointment` order by `appointment`.`start` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `city_view`
--

/*!50001 DROP VIEW IF EXISTS `city_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`192.168.1.%` SQL SECURITY DEFINER */
/*!50001 VIEW `city_view` AS select `city`.`cityId` AS `cityId`,`city`.`city` AS `city`,`country`.`countryId` AS `countryid`,`country`.`country` AS `country` from (`city` left join `country` on((`city`.`countryId` = `country`.`countryId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `customer_view`
--

/*!50001 DROP VIEW IF EXISTS `customer_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`192.168.1.123` SQL SECURITY DEFINER */
/*!50001 VIEW `customer_view` AS select `customer`.`customerId` AS `customerid`,`customer`.`customerName` AS `customerName`,`customer`.`addressId` AS `addressid`,`address`.`address` AS `address`,`address`.`address2` AS `address2`,`address`.`cityId` AS `cityid`,`city`.`city` AS `city`,`address`.`postalCode` AS `postalCode`,`city`.`countryId` AS `countryid`,`country`.`country` AS `country`,`address`.`phone` AS `phone`,`customer`.`active` AS `active` from (((`customer` left join `address` on((`customer`.`addressId` = `address`.`addressId`))) left join `city` on((`city`.`cityId` = `address`.`cityId`))) left join `country` on((`country`.`countryId` = `city`.`countryId`))) order by `customer`.`customerId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-16 21:13:33
