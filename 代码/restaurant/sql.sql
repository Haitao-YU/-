-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: restaurant
-- ------------------------------------------------------
-- Server version	5.7.31-log

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
-- Table structure for table `carousel_figure`
--

DROP TABLE IF EXISTS `carousel_figure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carousel_figure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='轮播图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carousel_figure`
--

LOCK TABLES `carousel_figure` WRITE;
/*!40000 ALTER TABLE `carousel_figure` DISABLE KEYS */;
INSERT INTO `carousel_figure` VALUES (1,'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/64a105f5-2568-49a0-a2f0-02fe6b84a5ad.png'),(2,'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/57c982ca-1f8a-43c4-88e0-42accdf41869.png'),(3,'http://img.yunhuzx.com/files/uploads/2019/07-15/156317417714344d740881.jpg'),(4,'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/34e5ca9f-4c42-4f96-8b0d-33bb1bac9528.png'),(5,'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/1d8c7116-a68e-4ba8-a982-dedf10847e41.png');
/*!40000 ALTER TABLE `carousel_figure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '餐品的ID',
  `name` varchar(50) NOT NULL DEFAULT '未命名' COMMENT '餐品名称',
  `price` double NOT NULL COMMENT '餐品价格',
  `type_id` int(11) NOT NULL COMMENT '餐品所属种类',
  `image` varchar(500) NOT NULL DEFAULT '' COMMENT '餐品的图片在线链接',
  `monthlySales` int(11) NOT NULL DEFAULT '0' COMMENT '本月销量',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '商品备注',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '餐品状态: 0(在售), 1(售罄), 2(下架)',
  PRIMARY KEY (`id`),
  KEY `food_food_type_id_fk` (`type_id`),
  CONSTRAINT `food_food_type_id_fk` FOREIGN KEY (`type_id`) REFERENCES `food_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='餐品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (2,'油麦菜',6.5,1,'http://img.mp.itc.cn/upload/20170311/fdac484de662409c8fe20aebb69c20b0_th.jpg',3,'约200g',2),(3,'生菜',6.5,1,'http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg',4,'约400g',0),(4,'土豆片',8,1,'https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg',5,'约200g',0),(5,'肥牛卷',58,2,'https://img.alicdn.com/i2/873961133/O1CN01gs8mmt1KExDtuszDA_!!873961133.jpg',6,'约200g',0),(6,'羊肉片',68,2,'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/d62f0249-2805-4362-8bd6-b0b2d2e4c75a.png',2,'约200g',0),(7,'零度可乐(罐装)',4,3,'https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BElingdu.png',31,'约400g',1),(8,'可乐(瓶装)',5,3,'https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E5%8F%AF%E4%B9%90.png',33,'约200g',0),(9,'可乐(大瓶装)',10,3,'https://pic3.womai.com/upload/601/603/606/6700/6706/513089/513089_1_pic500_8680.jpg',22,'约200g',0),(10,'雪碧(罐装)',4,3,'https://img3.jiemian.com/101/original/20200316/158435149322046500_a580xH.jpg',3,'约400g',0),(11,'雪碧(瓶装)',5,3,'https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E9%9B%AA%E7%A2%A7.png',1,'约200g',0),(12,'雪碧(大瓶装)',10,3,'https://ss2.meipian.me/users/63660674/f46681eab9d64e3993c24726a43f8752.jpg-mobile',32,'约400g',0),(13,'热汤面',10,4,'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/6c6b9a65-dac6-43c5-a751-f18400acd715.png',42,'约200g',0),(14,'毛肚',88,2,'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png',12,'约200g',0),(15,'鸭血',25,2,'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png',12,'约400g',1),(16,'冰淇淋',12,64,'https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png',32,'1筒',0);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_type`
--

DROP TABLE IF EXISTS `food_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '种类名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `food_type_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COMMENT='餐品种类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_type`
--

LOCK TABLES `food_type` WRITE;
/*!40000 ALTER TABLE `food_type` DISABLE KEYS */;
INSERT INTO `food_type` VALUES (3,'?开怀畅饮'),(63,'?新鲜水果'),(4,'?特别推荐'),(64,'?特色甜品'),(2,'?精品荤菜'),(1,'?绿色蔬菜');
/*!40000 ALTER TABLE `food_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line`
--

DROP TABLE IF EXISTS `line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `line` (
  `phone` varchar(200) NOT NULL COMMENT '排队顾客的手机号码',
  `serialNumber` int(11) NOT NULL,
  `mealsNumber` int(11) NOT NULL COMMENT '就餐人数',
  `date` datetime NOT NULL COMMENT '开始排队的时间',
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line`
--

LOCK TABLES `line` WRITE;
/*!40000 ALTER TABLE `line` DISABLE KEYS */;
INSERT INTO `line` VALUES ('18804314893',2,4,'2021-04-27 21:00:02'),('18844114671',1,4,'2021-04-25 14:45:02');
/*!40000 ALTER TABLE `line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `date` datetime NOT NULL COMMENT '订单的创建时间',
  `state` int(11) NOT NULL COMMENT '订单状态：0（已支付），1（已完成），2（已评价）',
  `phone` varchar(20) NOT NULL COMMENT '用户的电话号码',
  `content` varchar(12000) NOT NULL COMMENT '点餐内容',
  `tableId` varchar(100) NOT NULL COMMENT '桌号',
  `nOfDiners` int(11) NOT NULL COMMENT '用餐人数',
  `sum` double NOT NULL COMMENT '总金额',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2021-03-23 23:51:50',1,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":5,\"name\":\"肥牛卷\",\"price\":58,\"image\":\"https://img.alicdn.com/i2/873961133/O1CN01gs8mmt1KExDtuszDA_!!873961133.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":6,\"name\":\"羊肉片\",\"price\":68,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/d62f0249-2805-4362-8bd6-b0b2d2e4c75a.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":14,\"name\":\"毛肚\",\"price\":88,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"约200g\",\"count\":1}]','008',4,100,''),(2,'2021-03-24 00:12:34',1,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":5,\"name\":\"肥牛卷\",\"price\":58,\"image\":\"https://img.alicdn.com/i2/873961133/O1CN01gs8mmt1KExDtuszDA_!!873961133.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":6,\"name\":\"羊肉片\",\"price\":68,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/d62f0249-2805-4362-8bd6-b0b2d2e4c75a.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":14,\"name\":\"毛肚\",\"price\":88,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"约200g\",\"count\":1}]','008',4,100,''),(3,'2021-04-20 17:49:01',2,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":5,\"name\":\"肥牛卷\",\"price\":58,\"image\":\"https://img.alicdn.com/i2/873961133/O1CN01gs8mmt1KExDtuszDA_!!873961133.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":6,\"name\":\"羊肉片\",\"price\":68,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/d62f0249-2805-4362-8bd6-b0b2d2e4c75a.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":14,\"name\":\"毛肚\",\"price\":88,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"约200g\",\"count\":1}]','008',3,228.5,''),(4,'2021-04-20 17:49:57',1,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":5,\"name\":\"肥牛卷\",\"price\":58,\"image\":\"https://img.alicdn.com/i2/873961133/O1CN01gs8mmt1KExDtuszDA_!!873961133.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":6,\"name\":\"羊肉片\",\"price\":68,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/d62f0249-2805-4362-8bd6-b0b2d2e4c75a.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":14,\"name\":\"毛肚\",\"price\":88,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"约200g\",\"count\":1}]','008',3,228.5,''),(5,'2021-04-20 18:04:33',2,'18844114670','[{\"id\":8,\"name\":\"可乐(瓶装)\",\"price\":5,\"image\":\"https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E5%8F%AF%E4%B9%90.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":9,\"name\":\"可乐(大瓶装)\",\"price\":10,\"image\":\"https://pic3.womai.com/upload/601/603/606/6700/6706/513089/513089_1_pic500_8680.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":10,\"name\":\"雪碧(罐装)\",\"price\":4,\"image\":\"https://img3.jiemian.com/101/original/20200316/158435149322046500_a580xH.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":11,\"name\":\"雪碧(瓶装)\",\"price\":5,\"image\":\"https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E9%9B%AA%E7%A2%A7.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":12,\"name\":\"雪碧(大瓶装)\",\"price\":10,\"image\":\"https://ss2.meipian.me/users/63660674/f46681eab9d64e3993c24726a43f8752.jpg-mobile\",\"remark\":\"约400g\",\"count\":1}]','006',5,34,''),(6,'2021-04-20 18:19:56',1,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1}]','008',4,14.5,''),(7,'2021-04-21 11:15:17',2,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1}]','008',2,14.5,''),(8,'2021-04-21 16:35:04',1,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":5,\"name\":\"肥牛卷\",\"price\":58,\"image\":\"https://img.alicdn.com/i2/873961133/O1CN01gs8mmt1KExDtuszDA_!!873961133.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":6,\"name\":\"羊肉片\",\"price\":68,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/d62f0249-2805-4362-8bd6-b0b2d2e4c75a.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":14,\"name\":\"毛肚\",\"price\":88,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":8,\"name\":\"可乐(瓶装)\",\"price\":5,\"image\":\"https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E5%8F%AF%E4%B9%90.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":9,\"name\":\"可乐(大瓶装)\",\"price\":10,\"image\":\"https://pic3.womai.com/upload/601/603/606/6700/6706/513089/513089_1_pic500_8680.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":10,\"name\":\"雪碧(罐装)\",\"price\":4,\"image\":\"https://img3.jiemian.com/101/original/20200316/158435149322046500_a580xH.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":11,\"name\":\"雪碧(瓶装)\",\"price\":5,\"image\":\"https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E9%9B%AA%E7%A2%A7.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":12,\"name\":\"雪碧(大瓶装)\",\"price\":10,\"image\":\"https://ss2.meipian.me/users/63660674/f46681eab9d64e3993c24726a43f8752.jpg-mobile\",\"remark\":\"约400g\",\"count\":1},{\"id\":13,\"name\":\"热汤面\",\"price\":10,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/6c6b9a65-dac6-43c5-a751-f18400acd715.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":16,\"name\":\"冰淇淋\",\"price\":12,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"1筒\",\"count\":1}]','008',2,284.5,''),(9,'2021-04-21 20:02:41',1,'18844114670','[{\"id\":13,\"name\":\"热汤面\",\"price\":10,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/6c6b9a65-dac6-43c5-a751-f18400acd715.png\",\"remark\":\"约200g\",\"count\":1}]','008',4,10,''),(10,'2021-04-21 20:03:19',1,'18844114670','[{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1}]','008',10,8,''),(11,'2021-04-21 20:05:20',1,'18844114670','[{\"id\":16,\"name\":\"冰淇淋\",\"price\":12,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"1筒\",\"count\":1}]','008',4,12,'多放巧克力'),(12,'2021-04-21 20:07:51',1,'18844114670','[{\"id\":16,\"name\":\"冰淇淋\",\"price\":12,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"1筒\",\"count\":1}]','007',4,12,''),(16,'2021-04-22 11:50:19',1,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":13,\"name\":\"热汤面\",\"price\":10,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/6c6b9a65-dac6-43c5-a751-f18400acd715.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":16,\"name\":\"冰淇淋\",\"price\":12,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"1筒\",\"count\":1}]','002',2,36.5,''),(17,'2021-04-22 12:38:42',1,'18844114670','[{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1}]','002',4,8,''),(18,'2021-04-22 13:17:57',1,'18844114670','[{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1}]','002',3,8,''),(19,'2021-04-22 14:13:25',1,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":5,\"name\":\"肥牛卷\",\"price\":58,\"image\":\"https://img.alicdn.com/i2/873961133/O1CN01gs8mmt1KExDtuszDA_!!873961133.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":6,\"name\":\"羊肉片\",\"price\":68,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/d62f0249-2805-4362-8bd6-b0b2d2e4c75a.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":14,\"name\":\"毛肚\",\"price\":88,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":8,\"name\":\"可乐(瓶装)\",\"price\":5,\"image\":\"https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E5%8F%AF%E4%B9%90.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":9,\"name\":\"可乐(大瓶装)\",\"price\":10,\"image\":\"https://pic3.womai.com/upload/601/603/606/6700/6706/513089/513089_1_pic500_8680.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":10,\"name\":\"雪碧(罐装)\",\"price\":4,\"image\":\"https://img3.jiemian.com/101/original/20200316/158435149322046500_a580xH.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":11,\"name\":\"雪碧(瓶装)\",\"price\":5,\"image\":\"https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E9%9B%AA%E7%A2%A7.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":12,\"name\":\"雪碧(大瓶装)\",\"price\":10,\"image\":\"https://ss2.meipian.me/users/63660674/f46681eab9d64e3993c24726a43f8752.jpg-mobile\",\"remark\":\"约400g\",\"count\":1}]','001',2,262.5,''),(20,'2021-04-22 14:14:41',1,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":2},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":5,\"name\":\"肥牛卷\",\"price\":58,\"image\":\"https://img.alicdn.com/i2/873961133/O1CN01gs8mmt1KExDtuszDA_!!873961133.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":6,\"name\":\"羊肉片\",\"price\":68,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/d62f0249-2805-4362-8bd6-b0b2d2e4c75a.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":14,\"name\":\"毛肚\",\"price\":88,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":8,\"name\":\"可乐(瓶装)\",\"price\":5,\"image\":\"https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E5%8F%AF%E4%B9%90.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":9,\"name\":\"可乐(大瓶装)\",\"price\":10,\"image\":\"https://pic3.womai.com/upload/601/603/606/6700/6706/513089/513089_1_pic500_8680.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":10,\"name\":\"雪碧(罐装)\",\"price\":4,\"image\":\"https://img3.jiemian.com/101/original/20200316/158435149322046500_a580xH.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":11,\"name\":\"雪碧(瓶装)\",\"price\":5,\"image\":\"https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E9%9B%AA%E7%A2%A7.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":12,\"name\":\"雪碧(大瓶装)\",\"price\":10,\"image\":\"https://ss2.meipian.me/users/63660674/f46681eab9d64e3993c24726a43f8752.jpg-mobile\",\"remark\":\"约400g\",\"count\":2},{\"id\":13,\"name\":\"热汤面\",\"price\":10,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/6c6b9a65-dac6-43c5-a751-f18400acd715.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":16,\"name\":\"冰淇淋\",\"price\":12,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png\",\"remark\":\"1筒\",\"count\":1}]','001',3,301,''),(21,'2021-04-22 14:22:38',1,'18844114670','[{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1},{\"id\":13,\"name\":\"热汤面\",\"price\":10,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/6c6b9a65-dac6-43c5-a751-f18400acd715.png\",\"remark\":\"约200g\",\"count\":1}]','008',3,18,''),(22,'2021-04-23 22:00:46',1,'18844114673','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1}]','003',4,6.5,''),(23,'2021-04-23 22:36:49',1,'18844114671','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1}]','006',4,14.5,''),(24,'2021-04-23 22:37:50',1,'18844114670','[{\"id\":3,\"name\":\"生菜\",\"price\":6.5,\"image\":\"http://pic.51yuansu.com/pic3/cover/01/38/60/5926d7ce6b1ac_610.jpg\",\"remark\":\"约400g\",\"count\":1},{\"id\":4,\"name\":\"土豆片\",\"price\":8,\"image\":\"https://img30.360buyimg.com/jgsq-productsoa/jfs/t316/353/609499262/71459/29ea33ec/54197813Ndb4a77e1.jpg\",\"remark\":\"约200g\",\"count\":1}]','005',4,14.5,''),(25,'2021-04-25 17:50:28',1,'18844114670','[{\"id\":8,\"name\":\"可乐(瓶装)\",\"price\":5,\"image\":\"https://www.coca-cola.com.cn/content/dam/journey/cn/zh/private/brand/%E4%BA%A7%E5%93%81%E5%9B%BE%E5%8F%AF%E4%B9%90.png\",\"remark\":\"约200g\",\"count\":1},{\"id\":13,\"name\":\"热汤面\",\"price\":10,\"image\":\"https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/6c6b9a65-dac6-43c5-a751-f18400acd715.png\",\"remark\":\"约200g\",\"count\":1}]','001',2,15,'');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_info`
--

DROP TABLE IF EXISTS `store_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL COMMENT '信息名称',
  `value` varchar(500) NOT NULL DEFAULT '' COMMENT '信息内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_info`
--

LOCK TABLES `store_info` WRITE;
/*!40000 ALTER TABLE `store_info` DISABLE KEYS */;
INSERT INTO `store_info` VALUES (1,'店铺名称','可以的店 、'),(2,'地址','火星球喀巴谷勒斯山富勒路349号'),(3,'联系电话','13948229070'),(4,'餐品默认图片','https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/80b73b6d-a892-4a5c-9618-e80628b054a3.png'),(5,'餐桌默认图片','https://z3.ax1x.com/2021/04/04/cK0ob6.png'),(6,'状态','营业中'),(7,'admin-name','55170237'),(8,'admin-password','123'),(9,'店铺头像','https://z3.ax1x.com/2021/04/12/cDqLI1.jpg');
/*!40000 ALTER TABLE `store_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table`
--

DROP TABLE IF EXISTS `table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table` (
  `id` varchar(100) NOT NULL,
  `seats` int(11) NOT NULL COMMENT '座位数',
  `position` varchar(500) NOT NULL COMMENT '餐桌所在位置',
  `state` int(11) NOT NULL DEFAULT '2' COMMENT '餐桌状态：0（空闲），1（被占用），2（未启用）',
  `image` varchar(500) DEFAULT NULL COMMENT '餐桌图片',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `orderId` int(11) DEFAULT NULL COMMENT '正在进行的订单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='餐桌表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table`
--

LOCK TABLES `table` WRITE;
/*!40000 ALTER TABLE `table` DISABLE KEYS */;
INSERT INTO `table` VALUES ('001',4,'红运堂',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','靠窗',NULL),('002',10,'红运堂',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','超大桌',NULL),('003',4,'红运堂',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','隔间，安静，不受打扰',NULL),('004',4,'红运堂',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','靠近入口',NULL),('005',2,'吉利苑',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','无',NULL),('006',4,'吉利苑',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('007',4,'吉利苑',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('008',4,'吉利苑',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('009',2,'秋菊阁',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('010',4,'秋菊阁',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('011',4,'秋菊阁',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('012',2,'秋菊阁',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('013',2,'花好厅',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('014',2,'花好厅',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('015',2,'花好厅',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('016',2,'月圆厅',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('017',2,'月圆厅',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL),('018',2,'月圆厅',0,'https://z3.ax1x.com/2021/04/04/cK0ob6.png','',NULL);
/*!40000 ALTER TABLE `table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(100) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_phone_uindex` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2021-04-29 12:19:06
