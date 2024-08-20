# Host: localhost  (Version: 5.7.26)
# Date: 2024-06-10 14:14:54
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "course"
#

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `courseName` varchar(255) NOT NULL COMMENT '课程名',
  `courseDesc` varchar(255) DEFAULT NULL COMMENT '课程描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "course"
#

INSERT INTO `course` VALUES (2,'软件工程导论','未来的秃头'),(3,'数据结构','老难了'),(4,'算法分析','难死个人'),(5,'操作系统','咋玩意'),(6,'计算机网络','了解ip，才怪'),(7,'linux编程','虚拟机'),(8,'高等数学','大学数学'),(9,'web前端','前端基础'),(10,'大学英语','English'),(11,'大数据','暂无');

#
# Structure for table "permission"
#

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限',
  `url` varchar(255) DEFAULT NULL COMMENT '权限路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "permission"
#

INSERT INTO `permission` VALUES (1,'信息管理','/message'),(2,'开课','/openCourse'),(3,'选课','/selectCourse'),(4,'课程信息管理','/course/all'),(5,'教师信息管理','/teacher/all'),(6,'学生信息管理','/student/all'),(9,'系统管理','/system');

#
# Structure for table "role"
#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名',
  `roleDesc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "role"
#

INSERT INTO `role` VALUES (1,'超级管理员','系统管理员'),(2,'教师','学校老师'),(3,'学生','学校学生'),(4,'课程管理员','管理学校课程库信息'),(5,'教师管理员','管理学校教师信息'),(6,'学生管理员','管理学校学生信息');

#
# Structure for table "role_permission"
#

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "role_permission"
#

INSERT INTO `role_permission` VALUES (3,3,3),(28,6,1),(29,6,6),(30,5,1),(31,5,5),(32,4,1),(33,4,4),(46,1,1),(47,1,4),(48,1,5),(49,1,6),(50,1,9),(51,2,2);

#
# Structure for table "student_syllabus"
#

DROP TABLE IF EXISTS `student_syllabus`;
CREATE TABLE `student_syllabus` (
  `studentId` int(11) NOT NULL,
  `syllabusId` int(11) NOT NULL,
  PRIMARY KEY (`studentId`,`syllabusId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "student_syllabus"
#

INSERT INTO `student_syllabus` VALUES (1,7),(2,1),(2,2),(2,8);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(255) NOT NULL COMMENT '用户账号',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'admin','$2a$10$t3SM6XrOVkavWhQaij4skuQb/lUX7rINv9SH55te7oLk4ISwk.tNm',2),(2,'2020001','$2a$10$9YMeuj6qgLk43xzdc8iaZ.81055vouUEro6KKJxSgTx2G7pftogT2',0),(3,'2020002','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(4,'2020003','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(5,'2020004','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(6,'2020005','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(7,'2020006','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(8,'2020007','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(9,'2020008','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(10,'2020009','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(11,'2020010','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(12,'2020011','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(14,'2020013','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(15,'2020014','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(16,'2020015','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(20,'2020019','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(25,'2020024','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',0),(26,'20200001','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',1),(27,'20200002','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',1),(28,'20200003','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',1),(29,'20200004','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',1),(31,'20200005','$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2',1),(32,'2020025','$2a$10$qspf7GpTN0ghwdAJmhrPxeRt6uB5qPzdDwHqY6FobqtveqxPoYcia',0),(34,'202001','$2a$10$Z8Gbip2kOLK3t5fPmu4Yb.ZUpz/iVdFNimwBE2Uf7dEOl5X1gFpOi',2),(35,'202002','$2a$10$vnzGpj1eYiAJpEH09NnwUOTUYeBtJCRsPxUu0heTt1gd.yCzFX1P2',2),(42,'20200006','$2a$10$Hk5HNLNwIKbm7V545ro9tuV.zYXfZpo1W4t0Yw1ielpgNHjbljXFC',1);

#
# Structure for table "teacher"
#

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '工号',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `phoneNumber` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮件',
  `position` varchar(255) DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_name` (`username`) USING BTREE,
  CONSTRAINT `user_name` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "teacher"
#

INSERT INTO `teacher` VALUES (1,'2020001','李老师','男','111111111','111@qq.com','讲师'),(2,'2020002','张老师','男','1111111111','222@qq.com','讲师'),(3,'2020003','何老师','男','1111111','333@qq.com','教授'),(5,'2020004','王老师','女','22222','44@qq.com','副教授'),(6,'2020005','刘老师','男','333333','555@qq.com','讲师'),(7,'2020006','罗老师','男','44444','666@qq.com','校长'),(8,'2020007','孙老师','女','233233','7777@qq.com','讲师'),(9,'2020008','黄老师','男','1111','77477@qq.com','讲师'),(10,'2020009','潘老师','女','23732343','774677@qq.com','讲师'),(11,'2020010','彭老师','女','23323943','7747657@qq.com','讲师'),(12,'2020011','唐老师','女','23328343','77476557@qq.com','讲师'),(14,'2020013','朱老师','男','42424','28234187@qq.com','讲师'),(15,'2020014','吴老师','男','253453','35345@qq.com','讲师'),(16,'2020015','赵老师','女','253453','3335345@qq.com','讲师'),(20,'2020019','青老师','男','42424','1111187@qq.com','讲师'),(25,'2020024','璇老师','女','253453','1111732187@qq.com','教授'),(26,'2020025','周老师','女','55555555','35345@qq.com','讲师');

#
# Structure for table "class"
#

DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `className` varchar(255) DEFAULT NULL COMMENT '班级',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id（该班的班主任）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `teacher_id` (`teacher_id`) USING BTREE,
  CONSTRAINT `teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "class"
#

INSERT INTO `class` VALUES (1,'软件工程',5),(2,'人工智能',7),(3,'网络工程',10);

#
# Structure for table "syllabus"
#

DROP TABLE IF EXISTS `syllabus`;
CREATE TABLE `syllabus` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `class_hour` int(11) DEFAULT NULL COMMENT '学时',
  `credit` int(11) DEFAULT NULL COMMENT '学分',
  `address` varchar(255) DEFAULT NULL COMMENT '地点',
  `number` int(11) NOT NULL DEFAULT '0' COMMENT '选课人数',
  `teacher_id` int(11) DEFAULT NULL COMMENT '任课教师',
  `course_id` int(11) DEFAULT NULL COMMENT '课程名',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `teacherid` (`teacher_id`) USING BTREE,
  KEY `courseid` (`course_id`) USING BTREE,
  CONSTRAINT `courseid` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacherid` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "syllabus"
#

INSERT INTO `syllabus` VALUES (1,64,3,'A-401',1,1,2),(2,32,3,'B-305',1,6,3),(3,54,4,'C-201',0,1,4),(5,24,2,'B-101',0,1,5),(6,54,4,'B-506',0,7,2),(7,32,4,'A-102',1,7,3),(8,24,2,'B-203',1,7,6),(9,32,3,'D-304',0,5,4);

#
# Structure for table "student"
#

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '学号',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `phoneNumber` varchar(255) DEFAULT NULL COMMENT '电话',
  `class_id` int(11) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `users_ame` (`username`) USING BTREE,
  KEY `class_id` (`class_id`) USING BTREE,
  CONSTRAINT `class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_ame` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "student"
#

INSERT INTO `student` VALUES (1,'20200001','张三','男','2000-10-18','55345',1),(2,'20200002','李四','男','2023-03-19','55555555',1),(3,'20200003','王五','男','2023-03-06','5234654',3),(4,'20200004','六六','男','2023-03-04','3424',1),(6,'20200005','耗子','女','2023-03-01','253453',2),(7,'20200006','光头强','男','2023-03-16','55555555',2);

#
# Structure for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '账号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `phonenumber` varchar(255) DEFAULT NULL COMMENT '电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮件',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `username` (`username`) USING BTREE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "admin"
#

INSERT INTO `admin` VALUES (1,'admin','admin','8888888','888888'),(3,'202001','图书馆长','55555555','35345@qq.com'),(4,'202002','教导主任','253453','28917667@qq.com');

#
# Structure for table "user_role"
#

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "user_role"
#

INSERT INTO `user_role` VALUES (1,1),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(20,2),(25,2),(26,3),(27,3),(28,3),(29,3),(30,3),(31,3),(32,2),(34,6),(35,5);
