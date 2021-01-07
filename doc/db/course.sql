/*
 Navicat Premium Data Transfer

 Source Server         : local_db
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 127.0.0.1:3306
 Source Schema         : course

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 07/01/2021 13:56:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `parent` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('00000100', '00000000', '前端技术', 100);
INSERT INTO `category` VALUES ('00000101', '00000100', 'html/css', 101);
INSERT INTO `category` VALUES ('00000102', '00000100', 'javascript', 102);
INSERT INTO `category` VALUES ('00000103', '00000100', 'vue.js', 103);
INSERT INTO `category` VALUES ('00000104', '00000100', 'react.js', 104);
INSERT INTO `category` VALUES ('00000105', '00000100', 'angular', 105);
INSERT INTO `category` VALUES ('00000106', '00000100', 'node.js', 106);
INSERT INTO `category` VALUES ('00000107', '00000100', 'jquery', 107);
INSERT INTO `category` VALUES ('00000108', '00000100', '小程序', 108);
INSERT INTO `category` VALUES ('00000200', '00000000', '后端技术', 200);
INSERT INTO `category` VALUES ('00000201', '00000200', 'java', 201);
INSERT INTO `category` VALUES ('00000202', '00000200', 'springboot', 202);
INSERT INTO `category` VALUES ('00000203', '00000200', 'spring cloud', 203);
INSERT INTO `category` VALUES ('00000204', '00000200', 'ssm', 204);
INSERT INTO `category` VALUES ('00000205', '00000200', 'python', 205);
INSERT INTO `category` VALUES ('00000206', '00000200', '爬虫', 206);
INSERT INTO `category` VALUES ('00000300', '00000000', '移动开发', 300);
INSERT INTO `category` VALUES ('00000301', '00000300', 'android', 301);
INSERT INTO `category` VALUES ('00000302', '00000300', 'ios', 302);
INSERT INTO `category` VALUES ('00000303', '00000300', 'react native', 303);
INSERT INTO `category` VALUES ('00000304', '00000300', 'ionic', 304);
INSERT INTO `category` VALUES ('00000400', '00000000', '前沿技术', 400);
INSERT INTO `category` VALUES ('00000401', '00000400', '微服务', 401);
INSERT INTO `category` VALUES ('00000402', '00000400', '区块链', 402);
INSERT INTO `category` VALUES ('00000403', '00000400', '机器学习', 403);
INSERT INTO `category` VALUES ('00000404', '00000400', '深度学习', 404);
INSERT INTO `category` VALUES ('00000405', '00000400', '数据分析&挖掘', 405);
INSERT INTO `category` VALUES ('00000500', '00000000', '云计算&大数据', 500);
INSERT INTO `category` VALUES ('00000501', '00000500', '大数据', 501);
INSERT INTO `category` VALUES ('00000502', '00000500', 'hadoop', 502);
INSERT INTO `category` VALUES ('00000503', '00000500', 'spark', 503);
INSERT INTO `category` VALUES ('00000504', '00000500', 'hbase', 504);
INSERT INTO `category` VALUES ('00000505', '00000500', '阿里云', 505);
INSERT INTO `category` VALUES ('00000506', '00000500', 'docker', 506);
INSERT INTO `category` VALUES ('00000507', '00000500', 'kubernetes', 507);
INSERT INTO `category` VALUES ('00000600', '00000000', '运维&测试', 600);
INSERT INTO `category` VALUES ('00000601', '00000600', '运维', 601);
INSERT INTO `category` VALUES ('00000602', '00000600', '自动化运维', 602);
INSERT INTO `category` VALUES ('00000603', '00000600', '中间件', 603);
INSERT INTO `category` VALUES ('00000604', '00000600', 'linux', 604);
INSERT INTO `category` VALUES ('00000605', '00000600', '测试', 605);
INSERT INTO `category` VALUES ('00000606', '00000600', '自动化测试', 606);

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `course_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '大章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('00000001', '00000001', 'java入门');
INSERT INTO `chapter` VALUES ('00000002', '00000001', 'spring框架');
INSERT INTO `chapter` VALUES ('00000003', '00000001', 'springMVC框架');
INSERT INTO `chapter` VALUES ('00000004', '00000001', 'mybatis框架');
INSERT INTO `chapter` VALUES ('00000005', '00000001', 'springboot框架');
INSERT INTO `chapter` VALUES ('00000006', '00000002', 'MFC框架');
INSERT INTO `chapter` VALUES ('00000007', '00000002', 'QT框架');
INSERT INTO `chapter` VALUES ('00000008', '00000002', '逆向框架');
INSERT INTO `chapter` VALUES ('00000009', '00000000', '测试大章09');
INSERT INTO `chapter` VALUES ('00000010', '00000000', '测试大章10');
INSERT INTO `chapter` VALUES ('00000011', '00000000', '测试大章11');
INSERT INTO `chapter` VALUES ('00000012', '00000000', '测试大章12');
INSERT INTO `chapter` VALUES ('00000013', '00000000', '测试大章13');
INSERT INTO `chapter` VALUES ('00000014', 'pCi54ilt', '测试大章01');
INSERT INTO `chapter` VALUES ('00000015', 'pCi54ilt', '测试大章02');
INSERT INTO `chapter` VALUES ('00000016', 'pCi54ilt', '测试大章03');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `summary` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '概述',
  `time` int(11) NULL DEFAULT 0 COMMENT '时长|单位秒',
  `price` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '价格（元）',
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面',
  `level` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '级别|枚举[CourseLevelEnum]：ONE(\"1\", \"初级\"),TWO(\"2\", \"中级\"),THREE(\"3\", \"高级\")',
  `charge` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收费|枚举[CourseChargeEnum]：CHARGE(\"C\", \"收费\"),FREE(\"F\", \"免费\")',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态|枚举[CourseStatusEnum]：PUBLISH(\"P\", \"发布\"),DRAFT(\"D\", \"草稿\")',
  `enroll` int(11) NULL DEFAULT 0 COMMENT '报名数',
  `sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `created_at` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `teacher_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师|teacher.id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('00000001', 'java', '这是一门java课程', 15, 19.90, 'http://xeclass.oss-cn-beijing.aliyuncs.com/course/4MYfgGg63CaS0qQAqeYKoo.jpg', '1', 'C', 'P', 100, 4, '2020-11-18 22:19:58.000', '2020-12-13 00:52:52.981', '1');
INSERT INTO `course` VALUES ('00000002', 'c++', '这是一门c++课程', 4, 29.90, 'http://xeclass.oss-cn-beijing.aliyuncs.com/course/3xzByd6GCMOocGK0qIa0kI.jpg', '1', 'C', 'P', 80, 1, '2020-10-01 22:23:09.000', '2020-12-13 01:07:29.781', '1');
INSERT INTO `course` VALUES ('pCi54ilt', 'lua', 'lua概述', 50, 100.00, 'http://xeclass.oss-cn-beijing.aliyuncs.com/course/20ssDk0gqQGmo0q6YWI4i.jpg', '1', 'C', 'P', 35, 3, '2020-11-24 13:36:43.000', '2020-12-13 09:16:37.671', '1');
INSERT INTO `course` VALUES ('VWGvvEsI', 'C语言', 'C语言概述', 100, 123.00, 'http://xeclass.oss-cn-beijing.aliyuncs.com/course/50D9sDaCYgoKOOcikCSkGo.jpg', '1', 'C', 'P', 55, 2, '2020-11-23 14:11:33.000', '2020-12-13 09:16:01.360', '1');

-- ----------------------------
-- Table structure for course_category
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `course_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程|course.id',
  `category_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类|course.id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_category
-- ----------------------------
INSERT INTO `course_category` VALUES ('0GcY3vWf', '00000002', '00000205');
INSERT INTO `course_category` VALUES ('0rC0zzTO', 'VWGvvEsI', '00000304');
INSERT INTO `course_category` VALUES ('1ALLnHdC', 'VWGvvEsI', '00000301');
INSERT INTO `course_category` VALUES ('1erJSEhl', '00000002', '00000203');
INSERT INTO `course_category` VALUES ('28Z6ni4u', '00000001', '00000108');
INSERT INTO `course_category` VALUES ('29J5z2Ov', '00000002', '00000206');
INSERT INTO `course_category` VALUES ('389r8gtx', '00000001', '00000102');
INSERT INTO `course_category` VALUES ('4IswcWCc', 'VWGvvEsI', '00000300');
INSERT INTO `course_category` VALUES ('4UMMvS8K', '00000002', '00000204');
INSERT INTO `course_category` VALUES ('4Z3W7ZM5', '00000001', '00000107');
INSERT INTO `course_category` VALUES ('4ZOHnHAd', 'VWGvvEsI', '00000304');
INSERT INTO `course_category` VALUES ('51fHA9gM', 'pCi54ilt', '00000400');
INSERT INTO `course_category` VALUES ('56G9pkWd', 'VWGvvEsI', '00000303');
INSERT INTO `course_category` VALUES ('5ZceTIpM', '00000001', '00000105');
INSERT INTO `course_category` VALUES ('6x9BtU7v', 'pCi54ilt', '00000404');
INSERT INTO `course_category` VALUES ('7DBTdX3G', '00000001', '00000106');
INSERT INTO `course_category` VALUES ('7l9lCgmN', '00000002', '00000202');
INSERT INTO `course_category` VALUES ('7lLPpqdP', '00000002', '00000201');
INSERT INTO `course_category` VALUES ('8N4R5lDZ', 'VWGvvEsI', '00000301');
INSERT INTO `course_category` VALUES ('8UH9btte', '00000001', '00000102');
INSERT INTO `course_category` VALUES ('8UnbzYrC', '00000001', '00000104');
INSERT INTO `course_category` VALUES ('9xtW8GSD', '00000002', '00000206');
INSERT INTO `course_category` VALUES ('a1qQrAIH', '00000001', '00000107');
INSERT INTO `course_category` VALUES ('aEn7g8PZ', '00000002', '00000206');
INSERT INTO `course_category` VALUES ('AGI7ALJw', '00000001', '00000102');
INSERT INTO `course_category` VALUES ('APkFmEQS', '00000001', '00000104');
INSERT INTO `course_category` VALUES ('apXu3o7w', '00000002', '00000206');
INSERT INTO `course_category` VALUES ('bbVAVuqb', '00000002', '00000200');
INSERT INTO `course_category` VALUES ('be87OgBQ', 'pCi54ilt', '00000405');
INSERT INTO `course_category` VALUES ('Bw04Sgzj', '00000001', '00000107');
INSERT INTO `course_category` VALUES ('bXp7kwLg', '00000001', '00000104');
INSERT INTO `course_category` VALUES ('By4hjcUe', '00000001', '00000101');
INSERT INTO `course_category` VALUES ('C0VMqV7p', '00000002', '00000205');
INSERT INTO `course_category` VALUES ('c1InZGSt', '00000001', '00000105');
INSERT INTO `course_category` VALUES ('CBr8UCq5', 'VWGvvEsI', '00000301');
INSERT INTO `course_category` VALUES ('ClmJ7JpF', '00000001', '00000104');
INSERT INTO `course_category` VALUES ('crXYKrJ0', '00000002', '00000201');
INSERT INTO `course_category` VALUES ('CvlrCZNp', '00000002', '00000203');
INSERT INTO `course_category` VALUES ('cvoSKRIJ', '00000002', '00000202');
INSERT INTO `course_category` VALUES ('cWNOWwCg', '00000001', '00000103');
INSERT INTO `course_category` VALUES ('DsEvpfZ9', 'pCi54ilt', '00000401');
INSERT INTO `course_category` VALUES ('EG5Kg9lH', 'VWGvvEsI', '00000303');
INSERT INTO `course_category` VALUES ('Ewfj13P5', 'pCi54ilt', '00000400');
INSERT INTO `course_category` VALUES ('EzPmW0ID', '00000001', '00000101');
INSERT INTO `course_category` VALUES ('F3agKr1X', '00000001', '00000105');
INSERT INTO `course_category` VALUES ('FcqISOLo', '00000001', '00000103');
INSERT INTO `course_category` VALUES ('FcswSJG4', '00000001', '00000105');
INSERT INTO `course_category` VALUES ('FdTTm4yT', 'VWGvvEsI', '00000300');
INSERT INTO `course_category` VALUES ('fIZkh5Q4', '00000002', '00000206');
INSERT INTO `course_category` VALUES ('fNAtwAnV', '00000002', '00000205');
INSERT INTO `course_category` VALUES ('g7BUiGIK', 'pCi54ilt', '00000405');
INSERT INTO `course_category` VALUES ('gaagwFuw', 'pCi54ilt', '00000402');
INSERT INTO `course_category` VALUES ('gc2nu72i', 'pCi54ilt', '00000404');
INSERT INTO `course_category` VALUES ('gDMM6Y4G', 'VWGvvEsI', '00000302');
INSERT INTO `course_category` VALUES ('gg8SbUWv', 'VWGvvEsI', '00000304');
INSERT INTO `course_category` VALUES ('GRcT02Tu', '00000002', '00000204');
INSERT INTO `course_category` VALUES ('GTbyZvJ5', '00000001', '00000108');
INSERT INTO `course_category` VALUES ('gx7XuWPW', '00000002', '00000202');
INSERT INTO `course_category` VALUES ('GYlyctfb', 'VWGvvEsI', '00000301');
INSERT INTO `course_category` VALUES ('hk3q9goz', '00000002', '00000200');
INSERT INTO `course_category` VALUES ('hkM9dKdQ', '00000002', '00000201');
INSERT INTO `course_category` VALUES ('hOFTeNhc', '00000001', '00000101');
INSERT INTO `course_category` VALUES ('HU5AihNW', '00000001', '00000103');
INSERT INTO `course_category` VALUES ('HY77ueJT', '00000002', '00000201');
INSERT INTO `course_category` VALUES ('IN7V1HNf', 'VWGvvEsI', '00000303');
INSERT INTO `course_category` VALUES ('j0o6HKSo', 'pCi54ilt', '00000400');
INSERT INTO `course_category` VALUES ('J16LGYAt', 'VWGvvEsI', '00000300');
INSERT INTO `course_category` VALUES ('j6CH1Lib', 'pCi54ilt', '00000400');
INSERT INTO `course_category` VALUES ('jIl7YOVm', '00000002', '00000201');
INSERT INTO `course_category` VALUES ('JMe8frSG', 'VWGvvEsI', '00000303');
INSERT INTO `course_category` VALUES ('KDB4B4E2', 'pCi54ilt', '00000404');
INSERT INTO `course_category` VALUES ('KPf3LgBL', '00000002', '00000200');
INSERT INTO `course_category` VALUES ('KWDVSYpv', '00000001', '00000100');
INSERT INTO `course_category` VALUES ('LmUI91iM', '00000002', '00000202');
INSERT INTO `course_category` VALUES ('LX9BtxLF', 'pCi54ilt', '00000400');
INSERT INTO `course_category` VALUES ('MAIE5ZvE', 'pCi54ilt', '00000405');
INSERT INTO `course_category` VALUES ('Ml8JdSgs', '00000002', '00000202');
INSERT INTO `course_category` VALUES ('N5dAU3FE', 'pCi54ilt', '00000404');
INSERT INTO `course_category` VALUES ('nL5a8iVV', 'pCi54ilt', '00000403');
INSERT INTO `course_category` VALUES ('nLtQYimR', 'pCi54ilt', '00000401');
INSERT INTO `course_category` VALUES ('NTJI4RMk', 'pCi54ilt', '00000403');
INSERT INTO `course_category` VALUES ('O4Uedds0', 'pCi54ilt', '00000405');
INSERT INTO `course_category` VALUES ('o5IKYrmD', '00000002', '00000200');
INSERT INTO `course_category` VALUES ('O7G0SDgZ', '00000002', '00000205');
INSERT INTO `course_category` VALUES ('OBLZtRIU', 'pCi54ilt', '00000401');
INSERT INTO `course_category` VALUES ('OgPcQiOc', '00000001', '00000103');
INSERT INTO `course_category` VALUES ('oThFHgmx', 'pCi54ilt', '00000403');
INSERT INTO `course_category` VALUES ('P0jrs1Z0', '00000002', '00000203');
INSERT INTO `course_category` VALUES ('pbnmxpUR', '00000001', '00000106');
INSERT INTO `course_category` VALUES ('pk8JZcqm', '00000001', '00000100');
INSERT INTO `course_category` VALUES ('pq4PkrbS', 'VWGvvEsI', '00000301');
INSERT INTO `course_category` VALUES ('pqU8JcXp', '00000001', '00000106');
INSERT INTO `course_category` VALUES ('PzVBkV7o', '00000002', '00000205');
INSERT INTO `course_category` VALUES ('q0dGKhli', 'pCi54ilt', '00000402');
INSERT INTO `course_category` VALUES ('qfK1fmPx', 'pCi54ilt', '00000402');
INSERT INTO `course_category` VALUES ('Qhp14bob', 'pCi54ilt', '00000401');
INSERT INTO `course_category` VALUES ('qie8UOyO', '00000002', '00000205');
INSERT INTO `course_category` VALUES ('qj7OJkmI', '00000002', '00000203');
INSERT INTO `course_category` VALUES ('QNgTmRuJ', 'VWGvvEsI', '00000300');
INSERT INTO `course_category` VALUES ('QslXB4y3', '00000002', '00000204');
INSERT INTO `course_category` VALUES ('QZGvV9iu', 'pCi54ilt', '00000403');
INSERT INTO `course_category` VALUES ('R62B251R', '00000002', '00000200');
INSERT INTO `course_category` VALUES ('rLZaxrUv', 'VWGvvEsI', '00000300');
INSERT INTO `course_category` VALUES ('rt6oUkRN', 'VWGvvEsI', '00000302');
INSERT INTO `course_category` VALUES ('Rv9UJE4S', '00000002', '00000202');
INSERT INTO `course_category` VALUES ('rXeW2bb8', '00000001', '00000101');
INSERT INTO `course_category` VALUES ('rYAEgPHe', '00000001', '00000102');
INSERT INTO `course_category` VALUES ('ryRb8M62', '00000002', '00000206');
INSERT INTO `course_category` VALUES ('RzCri37C', 'VWGvvEsI', '00000304');
INSERT INTO `course_category` VALUES ('sp9dbDZA', 'VWGvvEsI', '00000303');
INSERT INTO `course_category` VALUES ('SpQMjkXB', '00000002', '00000204');
INSERT INTO `course_category` VALUES ('SpVwpOr3', 'VWGvvEsI', '00000300');
INSERT INTO `course_category` VALUES ('sq8l95Hk', '00000001', '00000100');
INSERT INTO `course_category` VALUES ('SSSwLKrj', '00000001', '00000100');
INSERT INTO `course_category` VALUES ('SsXp4GDm', '00000001', '00000107');
INSERT INTO `course_category` VALUES ('sZd0FtqY', 'pCi54ilt', '00000403');
INSERT INTO `course_category` VALUES ('thk0AfU4', 'pCi54ilt', '00000401');
INSERT INTO `course_category` VALUES ('TJLxavg0', '00000002', '00000203');
INSERT INTO `course_category` VALUES ('tL0G0fUb', 'pCi54ilt', '00000404');
INSERT INTO `course_category` VALUES ('TtCOuNRa', '00000002', '00000204');
INSERT INTO `course_category` VALUES ('UaclkYHq', 'VWGvvEsI', '00000302');
INSERT INTO `course_category` VALUES ('ujP6yUNN', 'pCi54ilt', '00000402');
INSERT INTO `course_category` VALUES ('uxr7pF6C', 'VWGvvEsI', '00000302');
INSERT INTO `course_category` VALUES ('V3hlHuQ6', '00000001', '00000108');
INSERT INTO `course_category` VALUES ('v8BilNwK', '00000001', '00000106');
INSERT INTO `course_category` VALUES ('VfPM2QIn', '00000001', '00000108');
INSERT INTO `course_category` VALUES ('Wkar6dJZ', 'pCi54ilt', '00000402');
INSERT INTO `course_category` VALUES ('wQWJkHXt', '00000002', '00000200');
INSERT INTO `course_category` VALUES ('wtoJ1taI', 'VWGvvEsI', '00000304');
INSERT INTO `course_category` VALUES ('wTW5o8eo', '00000002', '00000204');
INSERT INTO `course_category` VALUES ('x3OfHJOY', 'VWGvvEsI', '00000304');
INSERT INTO `course_category` VALUES ('xogUE8TU', 'VWGvvEsI', '00000302');
INSERT INTO `course_category` VALUES ('xYS5KH7L', 'VWGvvEsI', '00000301');
INSERT INTO `course_category` VALUES ('y500v0FZ', 'pCi54ilt', '00000405');
INSERT INTO `course_category` VALUES ('y6Oc8nNy', 'VWGvvEsI', '00000303');
INSERT INTO `course_category` VALUES ('YcdGwp8i', '00000002', '00000203');
INSERT INTO `course_category` VALUES ('Yj1IqHN6', '00000002', '00000201');
INSERT INTO `course_category` VALUES ('ZeQUDXQu', 'VWGvvEsI', '00000302');

-- ----------------------------
-- Table structure for course_content
-- ----------------------------
DROP TABLE IF EXISTS `course_content`;
CREATE TABLE `course_content`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_content
-- ----------------------------
INSERT INTO `course_content` VALUES ('00000001', '<p>嘿嘿123456777999567567</p>');
INSERT INTO `course_content` VALUES ('00000002', '<p><br></p>');
INSERT INTO `course_content` VALUES ('pCi54ilt', '这是内容');

-- ----------------------------
-- Table structure for course_content_file
-- ----------------------------
DROP TABLE IF EXISTS `course_content_file`;
CREATE TABLE `course_content_file`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `course_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `size` int(11) NULL DEFAULT NULL COMMENT '大小|字节b',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程内容文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_content_file
-- ----------------------------
INSERT INTO `course_content_file` VALUES ('bN8b8rAk', '00000002', 'http://127.0.0.1:9000/file/f/course\\uE5sSC6m.jpg', '课程封面1.jpg', 92975);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '相对路径',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后缀',
  `size` int(11) NULL DEFAULT NULL COMMENT '大小|字节B',
  `use` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用途|枚举[FileUseEnum]：COURSE(\"C\", \"讲师\"), TEACHER(\"T\", \"课程\")',
  `created_at` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `shard_index` int(11) NULL DEFAULT NULL COMMENT '已上传分片',
  `shard_size` int(11) NULL DEFAULT NULL COMMENT '分片大小|B',
  `shard_total` int(11) NULL DEFAULT NULL COMMENT '分片总数',
  `key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件标识',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `path_unique`(`path`) USING BTREE,
  UNIQUE INDEX `key_unique`(`key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1aSDU5as', 'course/3xzByd6GCMOocGK0qIa0kI.jpg', '课程封面1.jpg', 'jpg', 92975, 'C', '2020-12-13 09:13:08.999', '2020-12-13 09:13:09.136', 2, 51200, 2, '3xzByd6GCMOocGK0qIa0kI');
INSERT INTO `file` VALUES ('dXNLQxF5', 'course/20ssDk0gqQGmo0q6YWI4i.jpg', '课程封面3.jpg', 'jpg', 109121, 'C', '2020-12-13 09:16:24.655', '2020-12-13 09:16:24.945', 3, 51200, 3, '20ssDk0gqQGmo0q6YWI4i');
INSERT INTO `file` VALUES ('RhWFrriG', 'course/50D9sDaCYgoKOOcikCSkGo.jpg', '课程封面2.jpg', 'jpg', 121122, 'C', '2020-12-13 09:15:45.696', '2020-12-13 09:15:45.957', 3, 51200, 3, '50D9sDaCYgoKOOcikCSkGo');
INSERT INTO `file` VALUES ('U4abrwXu', 'teacher/2xqn6CdZoYKuGekqW4Qugc.jpg', '头像1.jpg', 'jpg', 21520, 'T', '2020-12-13 09:17:30.759', '2020-12-13 09:17:30.759', 1, 51200, 1, '2xqn6CdZoYKuGekqW4Qugc');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `photo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `register_time` datetime(3) NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `mobile_unique`(`mobile`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('00000000', '12345678901', 'e70e2222a9d67c4f2eae107533359aa4', '测试', NULL, '2021-01-06 18:02:21.000');

-- ----------------------------
-- Table structure for member_course
-- ----------------------------
DROP TABLE IF EXISTS `member_course`;
CREATE TABLE `member_course`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `member_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员id',
  `course_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `at` datetime(3) NOT NULL COMMENT '报名时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `member_course_unique`(`member_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员课程报名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_course
-- ----------------------------
INSERT INTO `member_course` VALUES ('ubW3qj3T', '5TH939YF', '00000002', '2021-01-07 01:25:29.289');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称|菜单或按钮',
  `page` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面|路由',
  `request` varchar(999) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求|接口',
  `parent` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('00', '欢迎', 'welcome', NULL, '');
INSERT INTO `resource` VALUES ('01', '系统管理', NULL, NULL, '');
INSERT INTO `resource` VALUES ('0101', '用户管理', 'system/user', NULL, '01');
INSERT INTO `resource` VALUES ('010101', '保存', NULL, '[\"/system/admin/user/list\",\"/system/admin/user/save\"]', '0101');
INSERT INTO `resource` VALUES ('010102', '删除', NULL, '[\"/system/admin/user/delete\"]', '0101');
INSERT INTO `resource` VALUES ('010103', '重置密码', NULL, '[\"/system/admin/user/save-password\"]', '0101');
INSERT INTO `resource` VALUES ('0102', '资源管理', 'system/resource', NULL, '01');
INSERT INTO `resource` VALUES ('010201', '保存/显示', NULL, '[\"/system/admin/resource\"]', '0102');
INSERT INTO `resource` VALUES ('0103', '角色管理', 'system/role', NULL, '01');
INSERT INTO `resource` VALUES ('010301', '角色/权限管理', NULL, '[\"/system/admin/role\"]', '0103');
INSERT INTO `resource` VALUES ('02', '业务管理', NULL, NULL, '');
INSERT INTO `resource` VALUES ('0201', '分类管理', 'business/category', NULL, '02');
INSERT INTO `resource` VALUES ('020101', '增删改查', NULL, '[\"/business/admin/category\"]', '0201');
INSERT INTO `resource` VALUES ('0202', '课程管理', 'business/course', NULL, '02');
INSERT INTO `resource` VALUES ('020201', '增删改查', NULL, '[\"/business/admin/course\",\"/business/admin/category/all\",\"business/chapter\",\"business/content\",\"/business/admin/course/list\",\"/business/admin/course/delete/\",\"/business/admin/course/save\",\"/business/admin/course/list-category/\",\"/business/admin/course/sort\",\"/business/admin/count/count-all\"]', '0202');
INSERT INTO `resource` VALUES ('0203', '大章管理', 'business/chapter', NULL, '02');
INSERT INTO `resource` VALUES ('020301', '增删改查', NULL, '[\"/business/admin/chapter/delete/\",\"/business/admin/chapter/list\",\"/business/admin/chapter/save\"]', '0203');
INSERT INTO `resource` VALUES ('0204', '小节管理', 'business/section', NULL, '02');
INSERT INTO `resource` VALUES ('020401', '增删改查', NULL, '[\"/business/admin/section/save\",\"/business/admin/section/list\",\"/business/admin/section/delete/\"]', '0204');
INSERT INTO `resource` VALUES ('0205', '内容管理', 'business/content', NULL, '02');
INSERT INTO `resource` VALUES ('020501', '增删改查', NULL, '[\"/business/admin/course/find-content/\",\"/business/admin/course/save-content\",\"/business/admin/course-content-file/list/\",\"/business/admin/course-content-file/save\",\"/business/admin/course-content-file/delete/\"]', '0205');
INSERT INTO `resource` VALUES ('0206', '讲师管理', 'business/teacher', NULL, '02');
INSERT INTO `resource` VALUES ('020601', '增删改查', NULL, '[\"/business/admin/teacher\",\"/business/admin/teacher/all\"]', '0206');
INSERT INTO `resource` VALUES ('0207', '会员管理', 'business/member', NULL, '02');
INSERT INTO `resource` VALUES ('020701', '增删改查', NULL, '[\"/business/admin/member\"]', '0207');
INSERT INTO `resource` VALUES ('0208', '短信管理', 'business/sms', NULL, '02');
INSERT INTO `resource` VALUES ('020801', '增删改查', NULL, '[\"/business/admin/sms\"]', '0208');
INSERT INTO `resource` VALUES ('0209', '订单管理', 'business/order', NULL, '02');
INSERT INTO `resource` VALUES ('020901', '增删改查', NULL, '[\"/business/admin/order\",\"/business/admin/order/list\"]', '0209');
INSERT INTO `resource` VALUES ('03', '文件管理', NULL, NULL, '');
INSERT INTO `resource` VALUES ('0301', '文件管理', 'file/file', NULL, '03');
INSERT INTO `resource` VALUES ('030101', '文件管理', NULL, '[\"/file/admin/file\",\"/file/admin/check\",\"/file/admin/oss-append\",\"/file/admin/oss-simple\",\"/file/admin/get-auth\",\"/file/admin/vod\"]', '0301');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色',
  `desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('00000000', '系统管理员', '管理用户、角色权限');
INSERT INTO `role` VALUES ('00000001', '开发', '维护资源');
INSERT INTO `role` VALUES ('00000002', '业务管理员', '负责业务管理');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色|id',
  `resource_id` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源|id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色资源关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES (1, '00000000', '00');
INSERT INTO `role_resource` VALUES (2, '00000000', '01');
INSERT INTO `role_resource` VALUES (3, '00000000', '0101');
INSERT INTO `role_resource` VALUES (4, '00000000', '010101');
INSERT INTO `role_resource` VALUES (5, '00000000', '010102');
INSERT INTO `role_resource` VALUES (6, '00000000', '010103');
INSERT INTO `role_resource` VALUES (7, '00000000', '0102');
INSERT INTO `role_resource` VALUES (8, '00000000', '010201');
INSERT INTO `role_resource` VALUES (9, '00000000', '0103');
INSERT INTO `role_resource` VALUES (10, '00000000', '010301');
INSERT INTO `role_resource` VALUES (11, '00000000', '02');
INSERT INTO `role_resource` VALUES (12, '00000000', '0201');
INSERT INTO `role_resource` VALUES (13, '00000000', '020101');
INSERT INTO `role_resource` VALUES (14, '00000000', '0202');
INSERT INTO `role_resource` VALUES (15, '00000000', '020201');
INSERT INTO `role_resource` VALUES (16, '00000000', '0203');
INSERT INTO `role_resource` VALUES (17, '00000000', '020301');
INSERT INTO `role_resource` VALUES (18, '00000000', '0204');
INSERT INTO `role_resource` VALUES (19, '00000000', '020401');
INSERT INTO `role_resource` VALUES (20, '00000000', '0205');
INSERT INTO `role_resource` VALUES (21, '00000000', '020501');
INSERT INTO `role_resource` VALUES (22, '00000000', '0206');
INSERT INTO `role_resource` VALUES (23, '00000000', '020601');
INSERT INTO `role_resource` VALUES (24, '00000000', '0207');
INSERT INTO `role_resource` VALUES (25, '00000000', '020701');
INSERT INTO `role_resource` VALUES (26, '00000000', '0208');
INSERT INTO `role_resource` VALUES (27, '00000000', '020801');
INSERT INTO `role_resource` VALUES (28, '00000000', '0209');
INSERT INTO `role_resource` VALUES (29, '00000000', '020901');
INSERT INTO `role_resource` VALUES (30, '00000000', '03');
INSERT INTO `role_resource` VALUES (31, '00000000', '0301');
INSERT INTO `role_resource` VALUES (32, '00000000', '030101');

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `role_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色|id',
  `user_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户|id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('uB7Jcm1P', '00000000', 'XaExYI9i');
INSERT INTO `role_user` VALUES ('ULJEnJRb', '00000000', 'hgXsq7JQ');

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `course_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程|course.id',
  `chapter_id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '大章|chapter.id',
  `video` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频',
  `time` int(11) NULL DEFAULT NULL COMMENT '时长|单位秒',
  `charge` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收费|C 收费；F 免费',
  `sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `created_at` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `vod` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vod|阿里云vod',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '小节' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('5Deqnily', '免费视频', '00000002', '00000006', '', 2, 'F', 2, '2021-01-06 16:38:25.000', '2021-01-06 18:15:02.808', '2bfee73f981342d1b10e86650fc52792');
INSERT INTO `section` VALUES ('qHASbQG5', '收费视频', '00000002', '00000006', '', 2, 'C', 1, '2021-01-06 16:37:48.000', '2021-01-06 18:14:49.175', '14f7b758890c4fa6a7e253aaaadaf3eb');

-- ----------------------------
-- Table structure for sms
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `use` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途|枚举[SmsUseEnum]：REGISTER(\"R\", \"注册\"), FORGET(\"F\", \"忘记密码\")',
  `at` datetime(3) NOT NULL COMMENT '生成时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途|枚举[SmsStatusEnum]：USED(\"U\", \"已使用\"), NOT_USED(\"N\", \"未使用\")',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms
-- ----------------------------
INSERT INTO `sms` VALUES ('00000000', '12345678901', '123456', 'R', '2021-01-06 21:57:51.000', 'N');
INSERT INTO `sms` VALUES ('b4lkdwG6', '15510050030', '539393', 'R', '2021-01-07 00:54:15.907', 'N');
INSERT INTO `sms` VALUES ('PSPnHRDj', '15510050030', '603982', 'R', '2021-01-07 01:22:51.559', 'N');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位',
  `motto` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座右铭',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '一号讲师', '一号讲师昵称', 'http://xeclass.oss-cn-beijing.aliyuncs.com/teacher/2xqn6CdZoYKuGekqW4Qugc.jpg', '0', '只要学不死就往死里学', '来自一线互联网大厂6666');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'zhangsan');
INSERT INTO `test` VALUES ('2', 'lisi');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登陆名',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name_unique`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0Oyg4P3x', 'test1', '123', '4db71c791e6a5f87027698565abc76c8');
INSERT INTO `user` VALUES ('hgXsq7JQ', 'test', '123', '4db71c791e6a5f87027698565abc76c8');
INSERT INTO `user` VALUES ('sL2wNS85', 'itwell123', 'xx123老师', 'dcbc6348fb1253e5778d0a885ffc83e1');
INSERT INTO `user` VALUES ('XaExYI9i', 'itwell', 'xx老师', '2ba7ebd7e86d6c595c44554d97c5f974');

SET FOREIGN_KEY_CHECKS = 1;
