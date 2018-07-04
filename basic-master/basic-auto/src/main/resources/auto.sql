CREATE DATABASE `forte` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `f_interface` (
  `idf_interface` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(200) DEFAULT NULL,
  `f_desc` varchar(200) DEFAULT NULL,
  `f_url` varchar(200) DEFAULT NULL,
  `f_method` varchar(45) DEFAULT NULL,
  `f_parameters` varchar(1000) DEFAULT NULL,
  `f_status` varchar(45) DEFAULT '0',
  `f_source` varchar(45) DEFAULT NULL,
  `f_createtime` datetime DEFAULT NULL,
  `f_updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`idf_interface`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

CREATE TABLE `f_running` (
  `idf_running` int(11) NOT NULL AUTO_INCREMENT,
  `f_interface_id` int(11) DEFAULT NULL,
  `f_parameters` varchar(1000) DEFAULT NULL,
  `f_response` varchar(1000) DEFAULT NULL,
  `f_status` varchar(45) DEFAULT '0',
  `f_patch` varchar(100) DEFAULT NULL,
  `f_createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`idf_running`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;


