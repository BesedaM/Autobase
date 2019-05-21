-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: autobase
-- ------------------------------------------------------
-- Server version	5.5.62

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(15) NOT NULL DEFAULT 'Беларусь',
  `district` varchar(45) NOT NULL DEFAULT 'Минский',
  `city` varchar(15) NOT NULL,
  `street` varchar(20) DEFAULT '-',
  `houseNum` int(11) DEFAULT '0',
  `buiding` varchar(3) DEFAULT '-',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'Беларусь','Минский','Минск','Скорины',18,'-'),(2,'Беларусь','Минский','пос.Солнечный','-',NULL,'-'),(3,'Беларусь','Минский','Заславль','Уваровой',4,'-'),(4,'Беларусь','Минский','Минск','Орловская',39,'2'),(5,'Беларусь','Минский','Жодино','Землестроителей',18,'-'),(6,'Беларусь','Минский','Борисов','Победы пр.',34,'1'),(7,'Беларусь','Минский','Минск','Газеты Звезда пр.',41,'-'),(8,'Беларусь','Минский','Минск','Оранжерейная',10,'-'),(9,'Беларусь','Минский','Минск','Аэропорт Минск-2',NULL,'-'),(10,'Беларусь','Минский','rrr','rrr',0,'-');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_state`
--

DROP TABLE IF EXISTS `car_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_state`
--

LOCK TABLES `car_state` WRITE;
/*!40000 ALTER TABLE `car_state` DISABLE KEYS */;
INSERT INTO `car_state` VALUES (1,'нормальное'),(2,'требуется осмотр'),(3,'требуется ремонт'),(4,'аварийное');
/*!40000 ALTER TABLE `car_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_status`
--

DROP TABLE IF EXISTS `car_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_status`
--

LOCK TABLES `car_status` WRITE;
/*!40000 ALTER TABLE `car_status` DISABLE KEYS */;
INSERT INTO `car_status` VALUES (1,'готов к отправке'),(2,'в рейсе'),(3,'в ремонте');
/*!40000 ALTER TABLE `car_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_type`
--

DROP TABLE IF EXISTS `car_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_type`
--

LOCK TABLES `car_type` WRITE;
/*!40000 ALTER TABLE `car_type` DISABLE KEYS */;
INSERT INTO `car_type` VALUES (1,'автобус'),(2,'грузовик');
/*!40000 ALTER TABLE `car_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_number` varchar(15) NOT NULL,
  `type_id` int(11) NOT NULL,
  `model` varchar(20) NOT NULL,
  `seatsNumber` smallint(150) DEFAULT NULL,
  `capacity_id` int(11) DEFAULT NULL,
  `status_id` int(11) NOT NULL DEFAULT '1',
  `state_id` int(11) NOT NULL DEFAULT '1',
  `driver_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `car_number_UNIQUE` (`car_number`),
  KEY `fk_Car_Car_state1_idx` (`state_id`),
  KEY `fk_cars_drivers1_idx` (`driver_id`),
  KEY `fk_Car_Car_status1` (`status_id`),
  KEY `fk_Car_Car_type1` (`type_id`),
  KEY `fk_Car_Truck_capacity1` (`capacity_id`),
  CONSTRAINT `fk_cars_drivers1` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Car_Car_state1` FOREIGN KEY (`state_id`) REFERENCES `car_state` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Car_Car_status1` FOREIGN KEY (`status_id`) REFERENCES `car_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Car_Car_type1` FOREIGN KEY (`type_id`) REFERENCES `car_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Car_Truck_capacity1` FOREIGN KEY (`capacity_id`) REFERENCES `truck_capacity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'145 DD',1,'МАЗ-241',22,NULL,1,1,3),(2,'874 HH',1,'МАЗ-251',47,NULL,1,1,2),(3,'665 OG',1,'МАЗ-251',47,NULL,3,3,6),(4,'996 TY',2,'ЗИЛ-130',NULL,1,1,2,4),(5,'455 WW',2,'КАМАЗ 65206',NULL,4,1,1,5);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars_in_routes`
--

DROP TABLE IF EXISTS `cars_in_routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars_in_routes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cars_in_routes_routes1_idx` (`route_id`),
  KEY `fk_cars_in_routes_cars1_idx` (`car_id`),
  CONSTRAINT `fk_cars_in_routes_cars1` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cars_in_routes_routes1` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars_in_routes`
--

LOCK TABLES `cars_in_routes` WRITE;
/*!40000 ALTER TABLE `cars_in_routes` DISABLE KEYS */;
INSERT INTO `cars_in_routes` VALUES (1,1,2),(2,2,5),(3,3,4),(4,4,4),(5,5,1);
/*!40000 ALTER TABLE `cars_in_routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_type`
--

DROP TABLE IF EXISTS `customer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_type`
--

LOCK TABLES `customer_type` WRITE;
/*!40000 ALTER TABLE `customer_type` DISABLE KEYS */;
INSERT INTO `customer_type` VALUES (1,'юр.лицо'),(2,'физ.лицо');
/*!40000 ALTER TABLE `customer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  `surname` varchar(15) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(25) NOT NULL,
  `company_name` varchar(45) DEFAULT '-',
  PRIMARY KEY (`id`),
  KEY `fk_Customer_Customer_type1` (`type_id`),
  KEY `fk_customers_users1_idx` (`id`),
  CONSTRAINT `fk_customers_users1` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Customer_Customer_type1` FOREIGN KEY (`type_id`) REFERENCES `customer_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (7,1,'Андрей','Баденков','1234567','a.badenkov_1@gmail.com','ОАО \"Сантехгарант\"'),(8,1,'Алексей','Дерюгин','6587421','alex_der@yahoo.com','ЗАО \"Внешдомстрой\"'),(9,2,'Борис','Сергейчук','8745961','sergey_W_sergey@yandex.ru','-'),(10,1,'Татьяна','Андреенко','6974512','smalltalk39@gmail.com','ОАО \"Белмедпрепараты\"');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drivers`
--

DROP TABLE IF EXISTS `drivers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drivers` (
  `id` int(11) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_drivers_users1_idx` (`id`),
  CONSTRAINT `fk_drivers_users1` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drivers`
--

LOCK TABLES `drivers` WRITE;
/*!40000 ALTER TABLE `drivers` DISABLE KEYS */;
INSERT INTO `drivers` VALUES (2,'Михайлов','Денис','2547964'),(3,'Петров','Сергей','4258963'),(4,'Врубель','Евгений','1257852'),(5,'Панасюк','Михаил','2571541'),(6,'Мерешко','Дмитрий','2475488');
/*!40000 ALTER TABLE `drivers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_status`
--

DROP TABLE IF EXISTS `request_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_status`
--

LOCK TABLES `request_status` WRITE;
/*!40000 ALTER TABLE `request_status` DISABLE KEYS */;
INSERT INTO `request_status` VALUES (1,'рассматривается'),(2,'принята'),(3,'отклонена');
/*!40000 ALTER TABLE `request_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `route_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_requests_request_status1_idx` (`status_id`),
  KEY `fk_requests_customers1_idx` (`customer_id`),
  KEY `fk_requests_routes1_idx` (`route_id`),
  CONSTRAINT `fk_requests_customers1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_requests_request_status1` FOREIGN KEY (`status_id`) REFERENCES `request_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_requests_routes1` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES (1,7,1,2,'-'),(2,8,2,2,'-'),(3,7,3,2,'-'),(4,9,4,2,'-');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'driver'),(3,'customer');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route_status`
--

DROP TABLE IF EXISTS `route_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route_status`
--

LOCK TABLES `route_status` WRITE;
/*!40000 ALTER TABLE `route_status` DISABLE KEYS */;
INSERT INTO `route_status` VALUES (1,'новый'),(2,'запланирован'),(3,'на выполнении'),(4,'выполнен');
/*!40000 ALTER TABLE `route_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `routes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `status_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Route_Route_status1` (`status_id`),
  CONSTRAINT `fk_Route_Route_status1` FOREIGN KEY (`status_id`) REFERENCES `route_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (1,'Пассажироперевозки БО Солнечный',4),(2,'Грузоперевозки стройматериалы',4),(3,'Грузоперевозки сантехника',4),(4,'Перевозка мебели',4),(5,'Перевозка пассажиров',4);
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `details` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Task_Adress1` (`address_id`),
  KEY `fk_Task_Route1` (`route_id`),
  CONSTRAINT `fk_Task_Adress1` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_Route1` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,1,1,'2018-09-18 10:30:00','Посадка пассажиров, ожидание'),(2,1,2,'2018-09-18 12:00:00','Прибытие на БО, высадка пассажиров'),(3,1,2,'2018-09-18 22:00:00','Посадка пассажиров'),(4,1,1,'2018-09-18 23:00:00','Прибытие в место отправления, высадка пассажиров'),(7,2,3,'2018-10-15 10:00:00','Погрузка товара, регистрация'),(8,2,4,'2018-10-15 13:00:00','Выгрузка товара, регистрация'),(9,3,5,'2018-11-08 10:00:00','Погрузка товара, получение накладной'),(10,3,1,'2018-11-08 13:00:00','Передача товара, передача накладной'),(11,4,6,'2019-01-12 10:00:00','Погрузка мебели, ожидание'),(12,4,7,'2019-01-12 14:00:00','Выгрузка мебели'),(17,5,8,'2019-02-03 10:00:00','Посадка пассажиров'),(18,5,9,'2019-02-03 12:00:00','Прибытие в аэропорт, выгрузка пассажиров'),(19,5,9,'2019-02-03 16:00:00','Посадка пассажиров'),(20,5,8,'2019-02-03 18:00:00','Прибытие в место отправления');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `truck_capacity`
--

DROP TABLE IF EXISTS `truck_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `truck_capacity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity, ton` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truck_capacity`
--

LOCK TABLES `truck_capacity` WRITE;
/*!40000 ALTER TABLE `truck_capacity` DISABLE KEYS */;
INSERT INTO `truck_capacity` VALUES (1,6),(2,10),(3,15),(4,0);
/*!40000 ALTER TABLE `truck_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `password` blob NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_role1` (`role_id`),
  CONSTRAINT `fk_users_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','\\b@K��\0��\ngo�\�',1),(2,'d.mihailov','Z�N�-�A���\�!��',2),(3,'s.petrov','�8^ߍ\�/Ǹn�=|�',2),(4,'e.vrubel','�L�j�Vi]��\�r��~',2),(5,'m.panasiuk','TP�{z;��i��ub',2),(6,'d.mereshko','\�ڣ[�*`\�{��;&��',2),(7,'santexgarant',']\�;\�y?C��֏�Ǳ',3),(8,'outerHouse','�gݸ\ZG��(\nʇ\�',3),(9,'sergey009','\�\�\�?��]��\�\�+��',3),(10,'tatianaA','Ћ\��2\�O�t^\�\�\�',3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-17  0:07:01
