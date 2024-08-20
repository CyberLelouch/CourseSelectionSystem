/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : css

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 01/04/2023 14:28:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phonenumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', '8888888', '888888');
INSERT INTO `admin` VALUES (3, '202001', '图书馆长', '55555555', '35345@qq.com');
INSERT INTO `admin` VALUES (4, '202002', '教导主任', '253453', '28917667@qq.com');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `className` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id（该班的班主任）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, '软件工程', 5);
INSERT INTO `class` VALUES (2, '人工智能', 7);
INSERT INTO `class` VALUES (3, '网络工程', 10);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `courseName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名',
  `courseDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (2, '软件工程导论', '未来的秃头');
INSERT INTO `course` VALUES (3, '数据结构', '老难了');
INSERT INTO `course` VALUES (4, '算法分析', '难死个人');
INSERT INTO `course` VALUES (5, '操作系统', '咋玩意');
INSERT INTO `course` VALUES (6, '计算机网络', '了解ip，才怪');
INSERT INTO `course` VALUES (7, 'linux编程', '虚拟机');
INSERT INTO `course` VALUES (8, '高等数学', '大学数学');
INSERT INTO `course` VALUES (9, 'web前端', '前端基础');
INSERT INTO `course` VALUES (10, '大学英语', 'English');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '信息管理', '/message');
INSERT INTO `permission` VALUES (2, '开课', '/openCourse');
INSERT INTO `permission` VALUES (3, '选课', '/selectCourse');
INSERT INTO `permission` VALUES (4, '课程信息管理', '/course/all');
INSERT INTO `permission` VALUES (5, '教师信息管理', '/teacher/all');
INSERT INTO `permission` VALUES (6, '学生信息管理', '/student/all');
INSERT INTO `permission` VALUES (9, '系统管理', '/system');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `roleDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '系统管理员');
INSERT INTO `role` VALUES (2, '教师', '学校老师');
INSERT INTO `role` VALUES (3, '学生', '学校学生');
INSERT INTO `role` VALUES (4, '课程管理员', '管理学校课程库信息');
INSERT INTO `role` VALUES (5, '教师管理员', '管理学校教师信息');
INSERT INTO `role` VALUES (6, '学生管理员', '管理学校学生信息');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (2, 2, 2);
INSERT INTO `role_permission` VALUES (3, 3, 3);
INSERT INTO `role_permission` VALUES (28, 6, 1);
INSERT INTO `role_permission` VALUES (29, 6, 6);
INSERT INTO `role_permission` VALUES (30, 5, 1);
INSERT INTO `role_permission` VALUES (31, 5, 5);
INSERT INTO `role_permission` VALUES (32, 4, 1);
INSERT INTO `role_permission` VALUES (33, 4, 4);
INSERT INTO `role_permission` VALUES (34, 1, 1);
INSERT INTO `role_permission` VALUES (35, 1, 4);
INSERT INTO `role_permission` VALUES (36, 1, 5);
INSERT INTO `role_permission` VALUES (37, 1, 6);
INSERT INTO `role_permission` VALUES (38, 1, 9);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `users_ame`(`username`) USING BTREE,
  INDEX `class_id`(`class_id`) USING BTREE,
  CONSTRAINT `class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_ame` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '20200001', '张三', '男', '2000-10-18', '55345', 1);
INSERT INTO `student` VALUES (2, '20200002', '李四', '男', '2023-03-19', '55555555', 1);
INSERT INTO `student` VALUES (3, '20200003', '王五', '男', '2023-03-06', '5234654', 3);
INSERT INTO `student` VALUES (4, '20200004', '六六', '女', '2023-03-04', '3424', 2);
INSERT INTO `student` VALUES (6, '20200005', '耗子', '女', '2023-03-01', '253453', 2);
INSERT INTO `student` VALUES (7, '20200006', '光头强', '男', '2023-03-16', '55555555', 2);

-- ----------------------------
-- Table structure for student_syllabus
-- ----------------------------
DROP TABLE IF EXISTS `student_syllabus`;
CREATE TABLE `student_syllabus`  (
  `studentId` int(11) NOT NULL,
  `syllabusId` int(11) NOT NULL,
  PRIMARY KEY (`studentId`, `syllabusId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_syllabus
-- ----------------------------
INSERT INTO `student_syllabus` VALUES (1, 7);
INSERT INTO `student_syllabus` VALUES (2, 1);
INSERT INTO `student_syllabus` VALUES (2, 2);
INSERT INTO `student_syllabus` VALUES (2, 8);

-- ----------------------------
-- Table structure for syllabus
-- ----------------------------
DROP TABLE IF EXISTS `syllabus`;
CREATE TABLE `syllabus`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `class_hour` int(11) NULL DEFAULT NULL COMMENT '学时',
  `credit` int(11) NULL DEFAULT NULL COMMENT '学分',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地点',
  `number` int(11) NOT NULL DEFAULT 0 COMMENT '选课人数',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '任课教师',
  `course_id` int(11) NULL DEFAULT NULL COMMENT '课程名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `teacherid`(`teacher_id`) USING BTREE,
  INDEX `courseid`(`course_id`) USING BTREE,
  CONSTRAINT `courseid` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacherid` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of syllabus
-- ----------------------------
INSERT INTO `syllabus` VALUES (1, 64, 3, 'A-401', 1, 1, 2);
INSERT INTO `syllabus` VALUES (2, 32, 3, 'B-305', 1, 6, 3);
INSERT INTO `syllabus` VALUES (3, 54, 4, 'C-201', 0, 1, 4);
INSERT INTO `syllabus` VALUES (5, 24, 2, 'B-101', 0, 1, 5);
INSERT INTO `syllabus` VALUES (6, 54, 4, 'B-506', 0, 7, 2);
INSERT INTO `syllabus` VALUES (7, 32, 4, 'A-102', 1, 7, 3);
INSERT INTO `syllabus` VALUES (8, 24, 2, 'B-203', 1, 7, 6);
INSERT INTO `syllabus` VALUES (9, 32, 3, 'D-304', 0, 5, 4);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phoneNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_name`(`username`) USING BTREE,
  CONSTRAINT `user_name` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '2020001', '李老师', '女', '111111111', '111@qq.com', '讲师');
INSERT INTO `teacher` VALUES (2, '2020002', '张老师', '男', '1111111111', '222@qq.com', '讲师');
INSERT INTO `teacher` VALUES (3, '2020003', '何老师', '男', '1111111', '333@qq.com', '教授');
INSERT INTO `teacher` VALUES (5, '2020004', '王老师', '女', '22222', '44@qq.com', '副教授');
INSERT INTO `teacher` VALUES (6, '2020005', '刘老师', '男', '333333', '555@qq.com', '讲师');
INSERT INTO `teacher` VALUES (7, '2020006', '罗老师', '男', '44444', '666@qq.com', '校长');
INSERT INTO `teacher` VALUES (8, '2020007', '孙老师', '女', '233233', '7777@qq.com', '讲师');
INSERT INTO `teacher` VALUES (9, '2020008', '黄老师', '男', '1111', '77477@qq.com', '讲师');
INSERT INTO `teacher` VALUES (10, '2020009', '潘老师', '女', '23732343', '774677@qq.com', '讲师');
INSERT INTO `teacher` VALUES (11, '2020010', '彭老师', '女', '23323943', '7747657@qq.com', '讲师');
INSERT INTO `teacher` VALUES (12, '2020011', '唐老师', '女', '23328343', '77476557@qq.com', '讲师');
INSERT INTO `teacher` VALUES (14, '2020013', '朱老师', '男', '42424', '28234187@qq.com', '讲师');
INSERT INTO `teacher` VALUES (15, '2020014', '吴老师', '男', '253453', '35345@qq.com', '讲师');
INSERT INTO `teacher` VALUES (16, '2020015', '赵老师', '女', '253453', '3335345@qq.com', '讲师');
INSERT INTO `teacher` VALUES (20, '2020019', '青老师', '男', '42424', '28943532187@qq.com', '讲师');
INSERT INTO `teacher` VALUES (25, '2020024', '璇老师', '女', '253453', '2891732187@qq.com', '教授');
INSERT INTO `teacher` VALUES (26, '2020025', '周老师', '女', '55555555', '35345@qq.com', '讲师');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '用户状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$t3SM6XrOVkavWhQaij4skuQb/lUX7rINv9SH55te7oLk4ISwk.tNm', 2);
INSERT INTO `user` VALUES (2, '2020001', '$2a$10$9YMeuj6qgLk43xzdc8iaZ.81055vouUEro6KKJxSgTx2G7pftogT2', 0);
INSERT INTO `user` VALUES (3, '2020002', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (4, '2020003', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (5, '2020004', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (6, '2020005', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (7, '2020006', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (8, '2020007', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (9, '2020008', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (10, '2020009', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (11, '2020010', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (12, '2020011', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (14, '2020013', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (15, '2020014', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (16, '2020015', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (20, '2020019', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (25, '2020024', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 0);
INSERT INTO `user` VALUES (26, '20200001', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 1);
INSERT INTO `user` VALUES (27, '20200002', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 1);
INSERT INTO `user` VALUES (28, '20200003', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 1);
INSERT INTO `user` VALUES (29, '20200004', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 1);
INSERT INTO `user` VALUES (31, '20200005', '$2a$10$li2Sjq3ZmLOuTOJUUMwlw.MOXffBcD4uxR9Zpi66KVf0iP.sY6iX2', 1);
INSERT INTO `user` VALUES (32, '2020025', '$2a$10$qspf7GpTN0ghwdAJmhrPxeRt6uB5qPzdDwHqY6FobqtveqxPoYcia', 0);
INSERT INTO `user` VALUES (34, '202001', '$2a$10$Z8Gbip2kOLK3t5fPmu4Yb.ZUpz/iVdFNimwBE2Uf7dEOl5X1gFpOi', 2);
INSERT INTO `user` VALUES (35, '202002', '$2a$10$vnzGpj1eYiAJpEH09NnwUOTUYeBtJCRsPxUu0heTt1gd.yCzFX1P2', 2);
INSERT INTO `user` VALUES (42, '20200006', '$2a$10$Hk5HNLNwIKbm7V545ro9tuV.zYXfZpo1W4t0Yw1ielpgNHjbljXFC', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 2);
INSERT INTO `user_role` VALUES (3, 2);
INSERT INTO `user_role` VALUES (4, 2);
INSERT INTO `user_role` VALUES (5, 2);
INSERT INTO `user_role` VALUES (6, 2);
INSERT INTO `user_role` VALUES (7, 2);
INSERT INTO `user_role` VALUES (8, 2);
INSERT INTO `user_role` VALUES (9, 2);
INSERT INTO `user_role` VALUES (10, 2);
INSERT INTO `user_role` VALUES (11, 2);
INSERT INTO `user_role` VALUES (12, 2);
INSERT INTO `user_role` VALUES (13, 2);
INSERT INTO `user_role` VALUES (14, 2);
INSERT INTO `user_role` VALUES (15, 2);
INSERT INTO `user_role` VALUES (16, 2);
INSERT INTO `user_role` VALUES (20, 2);
INSERT INTO `user_role` VALUES (25, 2);
INSERT INTO `user_role` VALUES (26, 3);
INSERT INTO `user_role` VALUES (27, 3);
INSERT INTO `user_role` VALUES (28, 3);
INSERT INTO `user_role` VALUES (29, 3);
INSERT INTO `user_role` VALUES (30, 3);
INSERT INTO `user_role` VALUES (31, 3);
INSERT INTO `user_role` VALUES (32, 2);
INSERT INTO `user_role` VALUES (34, 6);
INSERT INTO `user_role` VALUES (35, 5);

SET FOREIGN_KEY_CHECKS = 1;
