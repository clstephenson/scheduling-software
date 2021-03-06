-- -----------------------------------------------------------------------------------------------------
-- STRUCTURE SECTION
-- -----------------------------------------------------------------------------------------------------

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



-- ------------------------------------------------------------------------------------------------------
--  DATA SECTION
-- ------------------------------------------------------------------------------------------------------

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
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'460 Baflod Highway','',1,'14050','8647472770','0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(2,'816 Nupig Manor','',2,'63021','9004835247','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(3,'1617 Sacep Drive','',3,'13563','5454458692','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(4,'698 Akaji Trail','',4,'82985','9685108415','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(5,'1981 Cogama Pike','',5,'97213','8366412609','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(6,'653 Acelo Extension','',6,'08484','5565621391','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(7,'282 Toma Grove','',7,'12827','6759809220','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(8,'1084 Kikser Park','',8,'03827','9405278828','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(9,'1135 Budwa Manor','',9,'01117','6738035452','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(10,'554 Agnej Court','',10,'21041','2555796020','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(11,'1810 Akdi Trail','',11,'72043','9357444016','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(12,'1803 Uwani Point','',12,'59827','4378378155','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(13,'1172 Navo Way','',13,'47450','5844468930','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(14,'544 Kewvi Road','',14,'77360','9773042645','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(15,'1503 Vemu Junction','',15,'29545','8115223040','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(16,'1409 Welpen Point','',16,'51494','6458422302','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(17,'859 Egguz Pike','',17,'10836','7387058476','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(18,'1549 Figu Pass','',18,'33631','5033899496','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(19,'1111 Nijo Mill','',19,'43094','4724691593','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(20,'1610 Mitov Loop','',20,'99603','7588318076','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(21,'737 Solos Terrace','',21,'74954','8202921277','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(22,'1244 Fipne Point','',22,'01845','5573514920','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(23,'1714 Wahiza Manor','',23,'37004','4712101127','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(24,'705 Asigur Square','',24,'86963','7432724672','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(25,'1801 Rihic Terrace','',25,'12619','8315859957','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(26,'832 Hemow Square','',26,'45990','4447984475','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(27,'1888 Rovko Drive','',27,'49462','4678269529','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(28,'1379 Lijuv Place','',28,'70729','7753416818','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(29,'462 Omfem Glen','',29,'88706','6019493733','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(30,'1247 Vukuv Trail','',30,'88269','9749837122','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(31,'128 Upsej Way','',31,'72993','8232974941','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(32,'1794 Econ Street','',32,'23435','4645857587','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(33,'853 Pekwa Turnpike','',33,'26980','5836934431','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(34,'496 Gucmeg Circle','',34,'39500','8115734616','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(35,'843 Wihez Glen','',35,'84673','8455508068','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(36,'1305 Ipvu Junction','',36,'25929','5065514577','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(37,'1080 Fifici Mill','',37,'18063','9112037124','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(38,'1437 Zalo Grove','',38,'59728','5722216831','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(39,'1058 Vavo Plaza','',39,'17893','3374737393','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(40,'1805 Huoz Path','',40,'00717','5333815133','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(41,'1103 Bilur River','',41,'12298','5428584086','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(42,'791 Gahwur Grove','',42,'59901','7115782989','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(43,'1492 Emaad Point','',43,'33047','9052135855','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(44,'1552 Sedhej Point','',44,'69630','7548886559','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(45,'1726 Pufpe Drive','',45,'33121','6708949720','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(46,'1758 Goweri Street','',46,'23315','9173277979','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(47,'182 Zuivi Street','',47,'65543','3399268303','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(48,'1330 Midu Point','',48,'28720','7548435282','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(49,'104 Zepi Grove','',49,'52825','6434395178','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(50,'1424 Zula Drive','',50,'31074','9487358288','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(51,'1699 Ditze Key','',51,'28206','4809327724','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(52,'69 Abga Path','',52,'11565','6069734923','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(53,'600 Pubaw Center','',53,'27591','4425221502','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(54,'910 Piov River','',54,'23273','5243456184','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(55,'915 Duzob Pike','',55,'65000','6324333993','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(56,'39 Fohi View','',56,'74215','3285827814','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(57,'1001 Dielo Highway','',57,'58183','2026035033','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(58,'1076 Tavwi Terrace','',58,'31037','3829972354','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(59,'570 Jifaz Street','',59,'67923','2349359621','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(60,'1090 Esdo Mill','',60,'95191','3536452884','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(61,'1855 Akema Street','',61,'23306','7087678821','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(62,'1131 Zibev Key','',62,'17801','2347937777','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(63,'590 Pugaw Street','',63,'09287','9287255985','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(64,'511 Sawza Drive','',64,'44968','4738354326','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(65,'1301 Cigpog Junction','',65,'11940','8692582198','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(66,'810 Naculu Plaza','',66,'84527','5358991662','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(67,'1462 Amrup Manor','',67,'85034','9838294321','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(68,'70 Pazkij Ridge','',68,'69112','9789314589','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(69,'95 Zuwec Pike','',69,'55635','9844155727','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(70,'1287 Folif Drive','',70,'42719','3717408309','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(71,'1459 Aheked Center','',71,'17058','2889502465','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(72,'1526 Hahobi Avenue','',72,'54783','5167158226','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(73,'1545 Ajagu View','',73,'26634','3836093799','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(74,'1139 Rufgo Court','',74,'85953','9042235956','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(75,'1209 Wukmep Turnpike','',75,'02408','6775525751','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(76,'760 Oluzod Parkway','',76,'17326','8872694372','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(77,'1135 Vipve Way','',77,'47749','9344389286','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(78,'1328 Pigmet Pike','',78,'34494','6292283335','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(79,'1233 Tujom Parkway','',79,'12117','6502692078','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(80,'187 Busfo Key','',80,'07425','3738736467','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(81,'1550 Ugadam Boulevard','',81,'62033','3338077667','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(82,'1054 Ohaaku Center','',82,'01363','9312288754','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(83,'139 Bodbom River','',83,'21831','4205486118','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(84,'1822 Ocez Drive','',84,'25608','2145404081','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(85,'677 Dufvek Heights','',85,'95708','6309284027','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(86,'67 Pohibi Way','',86,'46355','7394419734','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(87,'1098 Fusaf Pike','',87,'65084','8676661319','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(88,'1651 Soleh Grove','',88,'54255','2492066872','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(89,'1340 Losha Parkway','',89,'63045','2345279964','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(90,'426 Vozhaz Park','',90,'95092','3769944067','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(91,'692 Puwid Extension','',91,'67228','7579306975','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(92,'1921 Deel Plaza','',92,'24599','9806113547','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(93,'1082 Acine Glen','',93,'64263','7593269054','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(94,'1009 Kujes Ridge','',94,'05885','7494653597','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(95,'1151 Iweciz Heights','',95,'12603','4274496088','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(96,'113 Tuvib Glen','',96,'03462','2475553067','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(97,'303 Wuloz Boulevard','',97,'79183','8839919973','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(98,'274 Geuc Terrace','',98,'46859','6434386815','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(99,'1426 Jalop Point','',99,'11080','6627921729','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(100,'1617 Tasdi Park','',100,'28817','2506506073','0000-00-00 00:00:00','','2018-06-25 03:27:40','');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,1,'INTRO','First Meeting','NEW_YORK','test','','2019-06-20 17:00:00','2019-06-20 18:00:00','0000-00-00 00:00:00','','2019-06-16 19:15:38','test'),(2,1,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-06-23 18:00:00','2018-06-23 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(3,1,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-23 19:00:00','2018-06-23 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(4,2,'INTRO','First Meeting','NEW_YORK','test','','2019-06-26 20:00:00','2019-06-26 21:00:00','0000-00-00 00:00:00','','2019-06-16 19:25:24','test'),(5,2,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-06-23 21:00:00','2018-06-23 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(6,2,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-23 22:00:00','2018-06-23 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(7,3,'INTRO','First Meeting','NEW_YORK','test','','2018-06-23 23:00:00','2018-06-24 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(8,3,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-24 16:00:00','2018-06-24 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(9,3,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-24 17:00:00','2018-06-24 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(10,4,'INTRO','First Meeting','NEW_YORK','test','','2018-06-24 18:00:00','2018-06-24 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(11,4,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-24 19:00:00','2018-06-24 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(12,4,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-24 20:00:00','2018-06-24 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(13,5,'INTRO','First Meeting','NEW_YORK','test','','2018-06-24 21:00:00','2018-06-24 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(14,5,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-24 22:00:00','2018-06-24 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(15,5,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-24 23:00:00','2018-06-25 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(16,6,'INTRO','First Meeting','NEW_YORK','test','','2018-06-25 16:00:00','2018-06-25 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(17,6,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-06-25 17:00:00','2018-06-25 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(18,6,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-25 18:00:00','2018-06-25 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(19,7,'INTRO','First Meeting','NEW_YORK','test','','2018-06-25 19:00:00','2018-06-25 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(20,7,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-25 20:00:00','2018-06-25 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(21,7,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-25 21:00:00','2018-06-25 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(22,8,'INTRO','First Meeting','NEW_YORK','test','','2018-06-25 22:00:00','2018-06-25 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(23,8,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-25 23:00:00','2018-06-26 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(24,8,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-06-26 16:00:00','2018-06-26 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(25,9,'INTRO','First Meeting','NEW_YORK','test','','2018-06-26 17:00:00','2018-06-26 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(26,9,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-26 18:00:00','2018-06-26 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(27,9,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-06-26 19:00:00','2018-06-26 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(28,10,'INTRO','First Meeting','NEW_YORK','test','','2018-06-26 20:00:00','2018-06-26 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(29,10,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-26 21:00:00','2018-06-26 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(30,10,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-06-26 22:00:00','2018-06-26 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(31,11,'INTRO','First Meeting','NEW_YORK','test','','2018-06-26 23:00:00','2018-06-27 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(32,11,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-29 16:00:00','2018-06-29 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(33,11,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-06-29 17:00:00','2018-06-29 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(34,12,'INTRO','First Meeting','NEW_YORK','test','','2018-06-29 18:00:00','2018-06-29 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(35,12,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-29 19:00:00','2018-06-29 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(36,12,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-29 20:00:00','2018-06-29 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(37,13,'INTRO','First Meeting','NEW_YORK','test','','2018-06-29 21:00:00','2018-06-29 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(38,13,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-29 22:00:00','2018-06-29 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(39,13,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-29 23:00:00','2018-06-30 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(40,14,'INTRO','First Meeting','NEW_YORK','test','','2018-06-30 16:00:00','2018-06-30 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(41,14,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-30 17:00:00','2018-06-30 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(42,14,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-06-30 18:00:00','2018-06-30 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(43,15,'INTRO','First Meeting','NEW_YORK','test','','2018-06-30 19:00:00','2018-06-30 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(44,15,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-30 20:00:00','2018-06-30 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(45,15,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-06-30 21:00:00','2018-06-30 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(46,16,'INTRO','First Meeting','NEW_YORK','test','','2018-06-30 22:00:00','2018-06-30 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(47,16,'CONSULT','Stock Market','NEW_YORK','test','','2018-06-30 23:00:00','2018-07-01 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(48,16,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-01 16:00:00','2018-07-01 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(49,17,'INTRO','First Meeting','NEW_YORK','test','','2018-07-01 17:00:00','2018-07-01 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(50,17,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-01 18:00:00','2018-07-01 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(51,17,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-07-01 19:00:00','2018-07-01 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(52,18,'INTRO','First Meeting','NEW_YORK','test','','2018-07-01 20:00:00','2018-07-01 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(53,18,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-07-01 21:00:00','2018-07-01 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(54,18,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-01 22:00:00','2018-07-01 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(55,19,'INTRO','First Meeting','NEW_YORK','test','','2018-07-01 23:00:00','2018-07-02 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(56,19,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-07-02 16:00:00','2018-07-02 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(57,19,'CONSULT','Stock Market','NEW_YORK','test','','2018-07-02 17:00:00','2018-07-02 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(58,20,'INTRO','First Meeting','NEW_YORK','test','','2018-07-02 18:00:00','2018-07-02 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(59,20,'CONSULT','Stock Market','NEW_YORK','test','','2018-07-02 19:00:00','2018-07-02 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(60,20,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-02 20:00:00','2018-07-02 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(61,21,'INTRO','First Meeting','NEW_YORK','test','','2018-07-02 21:00:00','2018-07-02 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(62,21,'CONSULT','Stock Market','NEW_YORK','test','','2018-07-02 22:00:00','2018-07-02 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(63,21,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-07-02 23:00:00','2018-07-03 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(64,22,'INTRO','First Meeting','NEW_YORK','test','','2018-07-03 16:00:00','2018-07-03 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(65,22,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-03 17:00:00','2018-07-03 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(66,22,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-07-03 18:00:00','2018-07-03 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(67,23,'INTRO','First Meeting','NEW_YORK','test','','2018-07-03 19:00:00','2018-07-03 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(68,23,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-03 20:00:00','2018-07-03 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(69,23,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-07-03 21:00:00','2018-07-03 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(70,24,'INTRO','First Meeting','NEW_YORK','test','','2018-07-03 22:00:00','2018-07-03 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(71,24,'CONSULT','Stock Market','NEW_YORK','test','','2018-07-03 23:00:00','2018-07-04 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(72,24,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-06 16:00:00','2018-07-06 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(73,25,'INTRO','First Meeting','NEW_YORK','test','','2018-07-06 17:00:00','2018-07-06 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(74,25,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-06 18:00:00','2018-07-06 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(75,25,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-06 19:00:00','2018-07-06 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(76,26,'INTRO','First Meeting','NEW_YORK','test','','2018-07-06 20:00:00','2018-07-06 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(77,26,'CONSULT','Stock Market','NEW_YORK','test','','2018-07-06 21:00:00','2018-07-06 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(78,26,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-06 22:00:00','2018-07-06 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(79,27,'INTRO','First Meeting','NEW_YORK','test','','2018-07-06 23:00:00','2018-07-07 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(80,27,'CONSULT','Stock Market','NEW_YORK','test','','2018-07-07 16:00:00','2018-07-07 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(81,27,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-07 17:00:00','2018-07-07 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(82,28,'INTRO','First Meeting','NEW_YORK','test','','2018-07-07 18:00:00','2018-07-07 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(83,28,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-07 19:00:00','2018-07-07 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(84,28,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-07-07 20:00:00','2018-07-07 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(85,29,'INTRO','First Meeting','NEW_YORK','test','','2018-07-07 21:00:00','2018-07-07 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(86,29,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-07 22:00:00','2018-07-07 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(87,29,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-07 23:00:00','2018-07-08 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(88,30,'INTRO','First Meeting','NEW_YORK','test','','2018-07-08 16:00:00','2018-07-08 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(89,30,'CONSULT','Retirement Plan','NEW_YORK','test','','2018-07-08 17:00:00','2018-07-08 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(90,30,'CONSULT','Tax Preparation','NEW_YORK','test','','2018-07-08 18:00:00','2018-07-08 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(91,31,'INTRO','First Meeting','PHOENIX','test2','','2018-06-23 22:00:00','2018-06-23 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(92,31,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-06-23 23:00:00','2018-06-24 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(93,31,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-06-24 00:00:00','2018-06-24 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(94,32,'INTRO','First Meeting','PHOENIX','test2','','2018-06-24 01:00:00','2018-06-24 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(95,32,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-06-24 02:00:00','2018-06-24 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(96,32,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-24 19:00:00','2018-06-24 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(97,33,'INTRO','First Meeting','PHOENIX','test2','','2018-06-24 20:00:00','2018-06-24 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(98,33,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-06-24 21:00:00','2018-06-24 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(99,33,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-06-24 22:00:00','2018-06-24 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(100,34,'INTRO','First Meeting','PHOENIX','test2','','2018-06-24 23:00:00','2018-06-25 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(101,34,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-25 00:00:00','2018-06-25 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(102,34,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-25 01:00:00','2018-06-25 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(103,35,'INTRO','First Meeting','PHOENIX','test2','','2018-06-25 02:00:00','2018-06-25 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(104,35,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-06-25 19:00:00','2018-06-25 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(105,35,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-06-25 20:00:00','2018-06-25 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(106,36,'INTRO','First Meeting','PHOENIX','test2','','2018-06-25 21:00:00','2018-06-25 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(107,36,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-25 22:00:00','2018-06-25 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(108,36,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-25 23:00:00','2018-06-26 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(109,37,'INTRO','First Meeting','PHOENIX','test2','','2018-06-26 00:00:00','2018-06-26 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(110,37,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-06-26 01:00:00','2018-06-26 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(111,37,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-26 02:00:00','2018-06-26 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(112,38,'INTRO','First Meeting','PHOENIX','test2','','2018-06-26 19:00:00','2018-06-26 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(113,38,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-26 20:00:00','2018-06-26 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(114,38,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-26 21:00:00','2018-06-26 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(115,39,'INTRO','First Meeting','PHOENIX','test2','','2018-06-26 22:00:00','2018-06-26 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(116,39,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-06-26 23:00:00','2018-06-27 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(117,39,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-06-27 00:00:00','2018-06-27 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(118,40,'INTRO','First Meeting','PHOENIX','test2','','2018-06-27 01:00:00','2018-06-27 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(119,40,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-06-27 02:00:00','2018-06-27 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(120,40,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-29 19:00:00','2018-06-29 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(121,41,'INTRO','First Meeting','PHOENIX','test2','','2018-06-29 20:00:00','2018-06-29 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(122,41,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-29 21:00:00','2018-06-29 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(123,41,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-06-29 22:00:00','2018-06-29 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(124,42,'INTRO','First Meeting','PHOENIX','test2','','2018-06-29 23:00:00','2018-06-30 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(125,42,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-06-30 00:00:00','2018-06-30 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(126,42,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-30 01:00:00','2018-06-30 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(127,43,'INTRO','First Meeting','PHOENIX','test2','','2018-06-30 02:00:00','2018-06-30 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(128,43,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-30 19:00:00','2018-06-30 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(129,43,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-30 20:00:00','2018-06-30 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(130,44,'INTRO','First Meeting','PHOENIX','test2','','2018-06-30 21:00:00','2018-06-30 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(131,44,'CONSULT','Stock Market','PHOENIX','test2','','2018-06-30 22:00:00','2018-06-30 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(132,44,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-06-30 23:00:00','2018-07-01 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(133,45,'INTRO','First Meeting','PHOENIX','test2','','2018-07-01 00:00:00','2018-07-01 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(134,45,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-01 01:00:00','2018-07-01 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(135,45,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-07-01 02:00:00','2018-07-01 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(136,46,'INTRO','First Meeting','PHOENIX','test2','','2018-07-01 19:00:00','2018-07-01 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(137,46,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-01 20:00:00','2018-07-01 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(138,46,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-01 21:00:00','2018-07-01 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(139,47,'INTRO','First Meeting','PHOENIX','test2','','2018-07-01 22:00:00','2018-07-01 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(140,47,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-01 23:00:00','2018-07-02 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(141,47,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-02 00:00:00','2018-07-02 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(142,48,'INTRO','First Meeting','PHOENIX','test2','','2018-07-02 01:00:00','2018-07-02 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(143,48,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-02 02:00:00','2018-07-02 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(144,48,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-02 19:00:00','2018-07-02 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(145,49,'INTRO','First Meeting','PHOENIX','test2','','2018-07-02 20:00:00','2018-07-02 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(146,49,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-02 21:00:00','2018-07-02 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(147,49,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-02 22:00:00','2018-07-02 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(148,50,'INTRO','First Meeting','PHOENIX','test2','','2018-07-02 23:00:00','2018-07-03 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(149,50,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-03 00:00:00','2018-07-03 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(150,50,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-07-03 01:00:00','2018-07-03 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(151,51,'INTRO','First Meeting','PHOENIX','test2','','2018-07-03 02:00:00','2018-07-03 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(152,51,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-03 19:00:00','2018-07-03 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(153,51,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-07-03 20:00:00','2018-07-03 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(154,52,'INTRO','First Meeting','PHOENIX','test2','','2018-07-03 21:00:00','2018-07-03 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(155,52,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-03 22:00:00','2018-07-03 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(156,52,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-03 23:00:00','2018-07-04 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(157,53,'INTRO','First Meeting','PHOENIX','test2','','2018-07-04 00:00:00','2018-07-04 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(158,53,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-07-04 01:00:00','2018-07-04 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(159,53,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-04 02:00:00','2018-07-04 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(160,54,'INTRO','First Meeting','PHOENIX','test2','','2018-07-06 19:00:00','2018-07-06 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(161,54,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-07-06 20:00:00','2018-07-06 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(162,54,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-07-06 21:00:00','2018-07-06 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(163,55,'INTRO','First Meeting','PHOENIX','test2','','2018-07-06 22:00:00','2018-07-06 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(164,55,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-06 23:00:00','2018-07-07 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(165,55,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-07 00:00:00','2018-07-07 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(166,56,'INTRO','First Meeting','PHOENIX','test2','','2018-07-07 01:00:00','2018-07-07 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(167,56,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-07-07 02:00:00','2018-07-07 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(168,56,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-07-07 19:00:00','2018-07-07 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(169,57,'INTRO','First Meeting','PHOENIX','test2','','2018-07-07 20:00:00','2018-07-07 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(170,57,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-07 21:00:00','2018-07-07 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(171,57,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-07 22:00:00','2018-07-07 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(172,58,'INTRO','First Meeting','PHOENIX','test2','','2018-07-07 23:00:00','2018-07-08 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(173,58,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-08 00:00:00','2018-07-08 01:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(174,58,'CONSULT','Retirement Plan','PHOENIX','test2','','2018-07-08 01:00:00','2018-07-08 02:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(175,59,'INTRO','First Meeting','PHOENIX','test2','','2018-07-08 02:00:00','2018-07-08 03:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(176,59,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-08 19:00:00','2018-07-08 20:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(177,59,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-08 20:00:00','2018-07-08 21:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(178,60,'INTRO','First Meeting','PHOENIX','test2','','2018-07-08 21:00:00','2018-07-08 22:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(179,60,'CONSULT','Tax Preparation','PHOENIX','test2','','2018-07-08 22:00:00','2018-07-08 23:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(180,60,'CONSULT','Stock Market','PHOENIX','test2','','2018-07-08 23:00:00','2018-07-09 00:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(181,61,'INTRO','First Meeting','LONDON','test3','','2018-06-23 16:00:00','2018-06-23 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(182,61,'CONSULT','Stock Market','LONDON','test3','','2018-06-23 17:00:00','2018-06-23 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(183,61,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-23 18:00:00','2018-06-23 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(184,62,'INTRO','First Meeting','LONDON','test3','','2018-06-24 11:00:00','2018-06-24 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(185,62,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-24 12:00:00','2018-06-24 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(186,62,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-24 13:00:00','2018-06-24 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(187,63,'INTRO','First Meeting','LONDON','test3','','2018-06-24 14:00:00','2018-06-24 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(188,63,'CONSULT','Stock Market','LONDON','test3','','2018-06-24 15:00:00','2018-06-24 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(189,63,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-24 16:00:00','2018-06-24 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(190,64,'INTRO','First Meeting','LONDON','test3','','2018-06-24 17:00:00','2018-06-24 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(191,64,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-24 18:00:00','2018-06-24 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(192,64,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-25 11:00:00','2018-06-25 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(193,65,'INTRO','First Meeting','LONDON','test3','','2018-06-25 12:00:00','2018-06-25 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(194,65,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-25 13:00:00','2018-06-25 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(195,65,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-25 14:00:00','2018-06-25 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(196,66,'INTRO','First Meeting','LONDON','test3','','2018-06-25 15:00:00','2018-06-25 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(197,66,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-25 16:00:00','2018-06-25 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(198,66,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-25 17:00:00','2018-06-25 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(199,67,'INTRO','First Meeting','LONDON','test3','','2018-06-25 18:00:00','2018-06-25 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(200,67,'CONSULT','Stock Market','LONDON','test3','','2018-06-26 11:00:00','2018-06-26 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(201,67,'CONSULT','Stock Market','LONDON','test3','','2018-06-26 12:00:00','2018-06-26 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(202,68,'INTRO','First Meeting','LONDON','test3','','2018-06-26 13:00:00','2018-06-26 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(203,68,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-26 14:00:00','2018-06-26 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(204,68,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-26 15:00:00','2018-06-26 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(205,69,'INTRO','First Meeting','LONDON','test3','','2018-06-26 16:00:00','2018-06-26 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(206,69,'CONSULT','Stock Market','LONDON','test3','','2018-06-26 17:00:00','2018-06-26 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(207,69,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-26 18:00:00','2018-06-26 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(208,70,'INTRO','First Meeting','LONDON','test3','','2018-06-29 11:00:00','2018-06-29 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(209,70,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-29 12:00:00','2018-06-29 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(210,70,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-29 13:00:00','2018-06-29 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(211,71,'INTRO','First Meeting','LONDON','test3','','2018-06-29 14:00:00','2018-06-29 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(212,71,'CONSULT','Stock Market','LONDON','test3','','2018-06-29 15:00:00','2018-06-29 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(213,71,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-29 16:00:00','2018-06-29 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(214,72,'INTRO','First Meeting','LONDON','test3','','2018-06-29 17:00:00','2018-06-29 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(215,72,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-29 18:00:00','2018-06-29 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(216,72,'CONSULT','Stock Market','LONDON','test3','','2018-06-30 11:00:00','2018-06-30 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(217,73,'INTRO','First Meeting','LONDON','test3','','2018-06-30 12:00:00','2018-06-30 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(218,73,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-30 13:00:00','2018-06-30 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(219,73,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-30 14:00:00','2018-06-30 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(220,74,'INTRO','First Meeting','LONDON','test3','','2018-06-30 15:00:00','2018-06-30 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(221,74,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-30 16:00:00','2018-06-30 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(222,74,'CONSULT','Stock Market','LONDON','test3','','2018-06-30 17:00:00','2018-06-30 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(223,75,'INTRO','First Meeting','LONDON','test3','','2018-06-30 18:00:00','2018-06-30 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(224,75,'CONSULT','Tax Preparation','LONDON','test3','','2018-07-01 11:00:00','2018-07-01 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(225,75,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-01 12:00:00','2018-07-01 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(226,76,'INTRO','First Meeting','LONDON','test3','','2018-07-01 13:00:00','2018-07-01 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(227,76,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-01 14:00:00','2018-07-01 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(228,76,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-01 15:00:00','2018-07-01 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(229,77,'INTRO','First Meeting','LONDON','test3','','2018-07-01 16:00:00','2018-07-01 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(230,77,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-01 17:00:00','2018-07-01 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(231,77,'CONSULT','Stock Market','LONDON','test3','','2018-07-01 18:00:00','2018-07-01 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(232,78,'INTRO','First Meeting','LONDON','test3','','2018-07-02 11:00:00','2018-07-02 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(233,78,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-02 12:00:00','2018-07-02 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(234,78,'CONSULT','Stock Market','LONDON','test3','','2018-07-02 13:00:00','2018-07-02 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(235,79,'INTRO','First Meeting','LONDON','test3','','2018-07-02 14:00:00','2018-07-02 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(236,79,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-02 15:00:00','2018-07-02 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(237,79,'CONSULT','Stock Market','LONDON','test3','','2018-07-02 16:00:00','2018-07-02 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(238,80,'INTRO','First Meeting','LONDON','test3','','2018-07-02 17:00:00','2018-07-02 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(239,80,'CONSULT','Stock Market','LONDON','test3','','2018-07-02 18:00:00','2018-07-02 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(240,80,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-03 11:00:00','2018-07-03 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(241,81,'INTRO','First Meeting','LONDON','test3','','2018-07-03 12:00:00','2018-07-03 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(242,81,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-03 13:00:00','2018-07-03 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(243,81,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-03 14:00:00','2018-07-03 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(244,82,'INTRO','First Meeting','LONDON','test3','','2018-07-03 15:00:00','2018-07-03 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(245,82,'CONSULT','Stock Market','LONDON','test3','','2018-07-03 16:00:00','2018-07-03 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(246,82,'CONSULT','Tax Preparation','LONDON','test3','','2018-07-03 17:00:00','2018-07-03 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(247,83,'INTRO','First Meeting','LONDON','test3','','2018-07-03 18:00:00','2018-07-03 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(248,83,'CONSULT','Tax Preparation','LONDON','test3','','2018-07-06 11:00:00','2018-07-06 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(249,83,'CONSULT','Tax Preparation','LONDON','test3','','2018-07-06 12:00:00','2018-07-06 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(250,84,'INTRO','First Meeting','LONDON','test3','','2018-07-06 13:00:00','2018-07-06 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(251,84,'CONSULT','Tax Preparation','LONDON','test3','','2018-07-06 14:00:00','2018-07-06 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(252,84,'CONSULT','Tax Preparation','LONDON','test3','','2018-07-06 15:00:00','2018-07-06 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(253,85,'INTRO','First Meeting','LONDON','test3','','2018-07-06 16:00:00','2018-07-06 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(254,85,'CONSULT','Stock Market','LONDON','test3','','2018-07-06 17:00:00','2018-07-06 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(255,85,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-06 18:00:00','2018-07-06 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(256,86,'INTRO','First Meeting','LONDON','test3','','2018-07-07 11:00:00','2018-07-07 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(257,86,'CONSULT','Tax Preparation','LONDON','test3','','2018-07-07 12:00:00','2018-07-07 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(258,86,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-07 13:00:00','2018-07-07 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(259,87,'INTRO','First Meeting','LONDON','test3','','2018-07-07 14:00:00','2018-07-07 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(260,87,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-07 15:00:00','2018-07-07 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(261,87,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-07 16:00:00','2018-07-07 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(262,88,'INTRO','First Meeting','LONDON','test3','','2018-07-07 17:00:00','2018-07-07 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(263,88,'CONSULT','Stock Market','LONDON','test3','','2018-07-07 18:00:00','2018-07-07 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(264,88,'CONSULT','Stock Market','LONDON','test3','','2018-07-08 11:00:00','2018-07-08 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(265,89,'INTRO','First Meeting','LONDON','test3','','2018-07-08 12:00:00','2018-07-08 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(266,89,'CONSULT','Stock Market','LONDON','test3','','2018-07-08 13:00:00','2018-07-08 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(267,89,'CONSULT','Tax Preparation','LONDON','test3','','2018-07-08 14:00:00','2018-07-08 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(268,90,'INTRO','First Meeting','LONDON','test3','','2018-07-08 15:00:00','2018-07-08 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(269,90,'CONSULT','Retirement Plan','LONDON','test3','','2018-07-08 16:00:00','2018-07-08 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(270,90,'CONSULT','Stock Market','LONDON','test3','','2018-07-08 17:00:00','2018-07-08 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(271,91,'INTRO','First Meeting','LONDON','test3','','2018-06-23 18:00:00','2018-06-23 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(272,91,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-24 11:00:00','2018-06-24 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(273,91,'CONSULT','Stock Market','LONDON','test3','','2018-06-24 12:00:00','2018-06-24 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(274,92,'INTRO','First Meeting','LONDON','test3','','2018-06-24 13:00:00','2018-06-24 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(275,92,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-24 14:00:00','2018-06-24 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(276,92,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-24 15:00:00','2018-06-24 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(277,93,'INTRO','First Meeting','LONDON','test3','','2018-06-24 16:00:00','2018-06-24 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(278,93,'CONSULT','Stock Market','LONDON','test3','','2018-06-24 17:00:00','2018-06-24 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(279,93,'CONSULT','Stock Market','LONDON','test3','','2018-06-24 18:00:00','2018-06-24 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(280,94,'INTRO','First Meeting','LONDON','test3','','2018-06-25 11:00:00','2018-06-25 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(281,94,'CONSULT','Stock Market','LONDON','test3','','2018-06-25 12:00:00','2018-06-25 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(282,94,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-25 13:00:00','2018-06-25 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(283,95,'INTRO','First Meeting','LONDON','test3','','2018-06-25 14:00:00','2018-06-25 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(284,95,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-25 15:00:00','2018-06-25 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(285,95,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-25 16:00:00','2018-06-25 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(286,96,'INTRO','First Meeting','LONDON','test3','','2018-06-25 17:00:00','2018-06-25 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(287,96,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-25 18:00:00','2018-06-25 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(288,96,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-26 11:00:00','2018-06-26 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(289,97,'INTRO','First Meeting','LONDON','test3','','2018-06-26 12:00:00','2018-06-26 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(290,97,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-26 13:00:00','2018-06-26 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(291,97,'CONSULT','Stock Market','LONDON','test3','','2018-06-26 14:00:00','2018-06-26 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(292,98,'INTRO','First Meeting','LONDON','test3','','2018-06-26 15:00:00','2018-06-26 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(293,98,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-26 16:00:00','2018-06-26 17:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(294,98,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-26 17:00:00','2018-06-26 18:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(295,99,'INTRO','First Meeting','LONDON','test3','','2018-06-26 18:00:00','2018-06-26 19:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(296,99,'CONSULT','Retirement Plan','LONDON','test3','','2018-06-29 11:00:00','2018-06-29 12:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(297,99,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-29 12:00:00','2018-06-29 13:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(298,100,'INTRO','First Meeting','LONDON','test3','','2018-06-29 13:00:00','2018-06-29 14:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(299,100,'CONSULT','Stock Market','LONDON','test3','','2018-06-29 14:00:00','2018-06-29 15:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57',''),(300,100,'CONSULT','Tax Preparation','LONDON','test3','','2018-06-29 15:00:00','2018-06-29 16:00:00','0000-00-00 00:00:00','','2018-06-24 19:38:57','');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'New York',1,'0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(2,'New York',2,'0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(3,'New York',3,'0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(4,'New York',4,'0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(5,'New York',5,'0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(6,'New York',6,'0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(7,'New York',7,'0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(8,'New York',8,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(9,'New York',9,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(10,'New York',10,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(11,'Washington DC',11,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(12,'Washington DC',12,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(13,'Washington DC',13,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(14,'Washington DC',14,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(15,'Washington DC',15,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(16,'Washington DC',16,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(17,'Washington DC',17,'0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(18,'Washington DC',18,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(19,'Washington DC',19,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(20,'Washington DC',20,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(21,'Miami',21,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(22,'Miami',22,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(23,'Miami',23,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(24,'Miami',24,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(25,'Miami',25,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(26,'Miami',26,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(27,'Miami',27,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(28,'Miami',28,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(29,'Miami',29,'0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(30,'Miami',30,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(31,'Dallas',31,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(32,'Dallas',32,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(33,'Dallas',33,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(34,'Dallas',34,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(35,'Dallas',35,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(36,'Dallas',36,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(37,'Dallas',37,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(38,'Dallas',38,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(39,'Dallas',39,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(40,'Dallas',40,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(41,'Phoenix',41,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(42,'Phoenix',42,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(43,'Phoenix',43,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(44,'Phoenix',44,'0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(45,'Phoenix',45,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(46,'Phoenix',46,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(47,'Phoenix',47,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(48,'Phoenix',48,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(49,'Phoenix',49,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(50,'Phoenix',50,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(51,'Los Angeles',51,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(52,'Los Angeles',52,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(53,'Los Angeles',53,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(54,'Los Angeles',54,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(55,'Los Angeles',55,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(56,'Los Angeles',56,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(57,'Los Angeles',57,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(58,'Los Angeles',58,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(59,'Los Angeles',59,'0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(60,'Los Angeles',60,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(61,'London',61,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(62,'London',62,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(63,'London',63,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(64,'London',64,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(65,'London',65,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(66,'London',66,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(67,'London',67,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(68,'London',68,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(69,'London',69,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(70,'London',70,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(71,'Liverpool',71,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(72,'Liverpool',72,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(73,'Liverpool',73,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(74,'Liverpool',74,'0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(75,'Liverpool',75,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(76,'Liverpool',76,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(77,'Liverpool',77,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(78,'Liverpool',78,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(79,'Liverpool',79,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(80,'Liverpool',80,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(81,'Manchester',81,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(82,'Manchester',82,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(83,'Manchester',83,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(84,'Manchester',84,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(85,'Manchester',85,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(86,'Manchester',86,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(87,'Manchester',87,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(88,'Manchester',88,'0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(89,'Manchester',89,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(90,'Manchester',90,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(91,'Oxford',91,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(92,'Oxford',92,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(93,'Oxford',93,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(94,'Oxford',94,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(95,'Oxford',95,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(96,'Oxford',96,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(97,'Oxford',97,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(98,'Oxford',98,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(99,'Oxford',99,'0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(100,'Oxford',100,'0000-00-00 00:00:00','','2018-06-25 03:27:40','');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `city_old`
--

LOCK TABLES `city_old` WRITE;
/*!40000 ALTER TABLE `city_old` DISABLE KEYS */;
INSERT INTO `city_old` VALUES (1,'New York',1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(2,'Washington DC',1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(3,'Miami',1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(4,'Dallas',1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(5,'Phoenix',1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(6,'Los Angeles',1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(7,'London',2,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(8,'Liverpool',2,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(9,'Manchester',2,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(10,'Oxford',2,'0000-00-00 00:00:00','','2018-06-05 13:59:25','');
/*!40000 ALTER TABLE `city_old` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(2,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(3,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(4,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(5,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(6,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(7,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(8,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:33',''),(9,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(10,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(11,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(12,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(13,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(14,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(15,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(16,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(17,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(18,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:34',''),(19,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(20,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(21,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(22,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(23,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(24,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(25,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(26,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(27,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(28,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(29,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(30,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:35',''),(31,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(32,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(33,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(34,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(35,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(36,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(37,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(38,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(39,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(40,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(41,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(42,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(43,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(44,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:36',''),(45,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(46,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(47,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(48,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(49,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(50,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(51,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(52,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(53,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(54,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(55,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(56,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(57,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(58,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(59,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:37',''),(60,'United States','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(61,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(62,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(63,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(64,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(65,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(66,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(67,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(68,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(69,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(70,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(71,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(72,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(73,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(74,'England','0000-00-00 00:00:00','','2018-06-25 03:27:38',''),(75,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(76,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(77,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(78,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(79,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(80,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(81,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(82,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(83,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(84,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(85,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(86,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(87,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(88,'England','0000-00-00 00:00:00','','2018-06-25 03:27:39',''),(89,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(90,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(91,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(92,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(93,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(94,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(95,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(96,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(97,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(98,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(99,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40',''),(100,'England','0000-00-00 00:00:00','','2018-06-25 03:27:40','');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `country_old`
--

LOCK TABLES `country_old` WRITE;
/*!40000 ALTER TABLE `country_old` DISABLE KEYS */;
INSERT INTO `country_old` VALUES (1,'United States','0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(2,'England','0000-00-00 00:00:00','','2018-06-05 13:59:25','');
/*!40000 ALTER TABLE `country_old` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Ollie Jones',1,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(2,'Louisa Clarke',2,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(3,'Allen Oliver',3,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(4,'Nelle Franklin',4,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(5,'Estelle Collier',5,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(6,'Mabelle Hamilton',6,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(7,'Evan Ruiz',7,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(8,'Caleb Griffin',8,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(9,'Luella Payne',9,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(10,'Ruth Burgess',10,1,'0000-00-00 00:00:00','','2018-06-05 13:59:25',''),(11,'Matthew Benson',11,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(12,'Lewis Marsh',12,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(13,'Jane Page',13,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(14,'Rena Romero',14,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(15,'Tyler Hart',15,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(16,'Georgia Colon',16,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(17,'Adelaide Kim',17,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(18,'Mittie Jennings',18,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(19,'Antonio Bates',19,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(20,'Duane Buchanan',20,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(21,'Garrett Lindsey',21,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(22,'Sam Conner',22,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(23,'Marc Goodwin',23,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(24,'James Farmer',24,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(25,'Lulu Castro',25,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(26,'Cynthia Morgan',26,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(27,'Floyd Nash',27,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(28,'George Clarke',28,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(29,'Landon George',29,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(30,'Cody Tucker',30,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(31,'Susan Abbott',31,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(32,'Travis Woods',32,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(33,'Floyd Beck',33,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(34,'Abbie Bates',34,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(35,'Harold Herrera',35,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(36,'Cameron Alvarez',36,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(37,'Zachary Fowler',37,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(38,'Clara Mason',38,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(39,'Helen Howard',39,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(40,'Gene Gross',40,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(41,'Ruby McDonald',41,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(42,'Lydia Warren',42,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(43,'Dustin Pearson',43,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(44,'Frank Elliott',44,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(45,'Katie Fuller',45,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(46,'Cecilia Beck',46,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(47,'Todd Berry',47,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(48,'George Walker',48,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(49,'Brett Burton',49,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(50,'Celia Palmer',50,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(51,'Vernon Conner',51,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(52,'Rhoda Weber',52,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(53,'Marvin Wright',53,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(54,'Abbie Phelps',54,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(55,'Lida Cain',55,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(56,'Kevin Davidson',56,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(57,'Ronald Paul',57,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(58,'Lida Fields',58,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(59,'Lillie Edwards',59,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(60,'Daniel Washington',60,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(61,'Cora Gross',61,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(62,'Harvey Reyes',62,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(63,'Alta Griffith',63,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(64,'Paul Fernandez',64,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(65,'Evan Foster',65,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(66,'Landon George',66,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(67,'Samuel Summers',67,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(68,'Derrick Snyder',68,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(69,'Elijah Stokes',69,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(70,'Genevieve Reed',70,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(71,'Alta Woods',71,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(72,'Gerald Figueroa',72,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(73,'Milton Silva',73,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(74,'Lela Fowler',74,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(75,'Dale Hansen',75,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(76,'Barry Summers',76,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(77,'Margaret Pearson',77,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(78,'Amy Herrera',78,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(79,'Jayden Moran',79,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(80,'Bettie Castillo',80,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(81,'Marguerite Turner',81,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(82,'Shawn Steele',82,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(83,'Barry Fernandez',83,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(84,'Dominic Roberts',84,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(85,'Florence Brown',85,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(86,'Gilbert Welch',86,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(87,'Victoria Stanley',87,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(88,'Austin Montgomery',88,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(89,'Vernon Moody',89,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(90,'Jerome Cobb',90,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(91,'Maurice Buchanan',91,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(92,'Samuel Stewart',92,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(93,'Danny Wallace',93,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(94,'Gavin Weaver',94,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(95,'Phillip Fernandez',95,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(96,'Jeremy Sandoval',96,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(97,'Lura Ferguson',97,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(98,'Eugene Walker',98,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(99,'Lizzie Flores',99,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26',''),(100,'Gavin Klein',100,1,'0000-00-00 00:00:00','','2018-06-05 13:59:26','');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `incrementtypes`
--

LOCK TABLES `incrementtypes` WRITE;
/*!40000 ALTER TABLE `incrementtypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `incrementtypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reminder`
--

LOCK TABLES `reminder` WRITE;
/*!40000 ALTER TABLE `reminder` DISABLE KEYS */;
/*!40000 ALTER TABLE `reminder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test','test',1,'','0000-00-00 00:00:00','2018-06-24 18:47:34',''),(2,'test2','test2',1,'','0000-00-00 00:00:00','2018-06-24 18:47:34',''),(3,'test3','test3',1,'','0000-00-00 00:00:00','2018-06-24 18:47:34','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-16 21:17:53


-- update dates so that they fall in the current year and month
delete from appointment
where DAY(start) > 28;

delete from appointment
where DAY(end) > 28;

update appointment
set
    start = CONCAT(YEAR(CURDATE()), '-', MONTH(CURDATE()), '-', DATE_FORMAT(start, '%d %T')),
    end = CONCAT(YEAR(CURDATE()), '-', MONTH(CURDATE()), '-', DATE_FORMAT(end, '%d %T'));


