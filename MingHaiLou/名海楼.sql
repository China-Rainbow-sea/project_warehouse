
/* 创建mhl数据库*/
CREATE DATABASE mhl;

USE mhl;


/* 创建 employee 员工信息表(id为主键,emp_id,name,pwd,jod)*/

CREATE TABLE employee(
    id INT PRIMARY KEY AUTO_INCREMENT,      # primary key 主键,auot_increment 自增
    emp_id VARCHAR(30) NOT NULL DEFAULT '', # not null 非空约束,default默认值,''空字符串,员工号
    pwd CHAR(32) NOT NULL DEFAULT '',    # 密码  这里使用char(32) 是为了后面插入 md5()加密的格式
    `name` VARCHAR(50) NOT NULL DEFAULT '', # 用户名
    jod VARCHAR(50) NOT NULL DEFAULT ''     # 职务，最后一个不要加逗号分隔,不然报错

);


DESC employee;

SELECT *
FROM employee;
# 为 employee 表添加数据
/* 插入的密码使用: md5()加密变成了:e44f8cf63970db5c2df0a18153bcdf49
当使用了md5()加密插入数据，同样需要使用md5()解密才可以查询到相应的数据信息
*/
INSERT INTO employee(`name`,emp_id,pwd,jod)  
VALUES('路明非','20221101',MD5('112358'),'经理'),
('路明泽','20221102',MD5('985'),'副总经理'),
('楚子航','20221103',MD5('211'),'厨师'),  
('凯撒','20221104',MD5('666'),'收银员'),
('芬格尔','20221105',MD5('123'),'服务员');

SELECT *
FROM employee
WHERE pwd = '112358'; # 使用了md5()加密插入数据，
#如果不使用同样的md5()查询解密数据,是无法查询到结果的

SELECT *
FROM employee
WHERE pwd = MD5('112358');

#########################################################

# 菜单
CREATE TABLE dining(
    id INT PRIMARY KEY AUTO_INCREMENT,  # 主键,表示餐桌的编号
    state VARCHAR(20) NOT NULL DEFAULT '', # 表示餐桌的状态
    order_name VARCHAR(50) NOT NULL DEFAULT '', # 预定人的姓名
    order_tel VARCHAR(20) NOT NULL DEFAULT ''  # 预定人的电话，最后一个不要加逗号分隔，不然报错
)

DESC dining;

# 插入数据
INSERT INTO dining(state) 
VALUES('空'),
      ('空'),
      ('空'),
      ('空'),
      ('空'),
      ('空');

SELECT *
FROM dining;
                               
SELECT id,state,order_name AS orderName,order_tel AS orderTel FROM dining

SELECT id,state FROM dining WHERE id = 1;

UPDATE dining 
SET state = '空',order_name = '',order_tel = ''
WHERE id = 1;

#########################################################
# 菜谱
CREATE TABLE menu (
    id INT PRIMARY KEY AUTO_INCREMENT, # 菜名编号 唯一
    `name` VARCHAR(30) NOT NULL DEFAULT '', # 菜名
    `type` VARCHAR(30) NOT NULL DEFAULT '', # 菜品种类
    price DOUBLE NOT NULL DEFAULT 0  # 菜的价格, 最后一个不要加逗号分隔(不然报错)
);

DESC menu;

SELECT *
FROM menu;

INSERT INTO menu(`name`,`type`,price) 
VALUES('八宝饭','主食',10),
      ('叉烧包','主食',20),
      ('宫保鸡丁','热菜',30),
      ('山药鱼','凉菜',14),
      ('银丝卷','甜食',9),
      ('水煮鱼','热菜',26),
      ('甲鱼汤','汤菜',100);

SELECT * FROM menu;

#########################################################

# 餐桌账单，
CREATE TABLE bill(
    id INT PRIMARY KEY AUTO_INCREMENT, # 主键
    bill_id VARCHAR(50) NOT NULL UNIQUE DEFAULT '',   # 账单号，可以按照自己的规则生成 uuid
    bill_date DATETIME NOT NULL,   # 订单日期 
    dining_id INT NOT NULL DEFAULT 0,   # 餐桌编号
    menu_id INT NOT NULL DEFAULT 0,    # 菜的编号
    menu_name VARCHAR(30) NOT NULL DEFAULT '', # 菜名
    nums INT NOT NULL DEFAULT 0,  # 对应菜的份数
    money DOUBLE NOT NULL DEFAULT 0, # 当前菜的总价
    
    state VARCHAR(30) NOT NULL DEFAULT '' # 该订单的状态: 未结账，已经结账了，现金，支付宝，微信  
  
);

# 多表连接,查询账单信息
SELECT b.id,bill_id AS billId,bill_date AS billDate,dining_id AS diningId,
       menu_id AS menuId,menu_name AS menuName,nums,money,state,m.`price` AS menuPrice
       FROM bill b
       JOIN menu m
       ON b.`menu_id` = m.`id`;
       
       
SELECT *
FROM bill;

DESC bill;


SELECT b.id,bill_id AS billId,bill_date AS billDate,dining_id AS diningId, 
                     menu_id AS menuId,menu_name AS menuName,nums,money,state,m.`price` AS menuPrice
                     FROM bill b
                     JOIN menu m 
                     ON b.`menu_id` = m.`id`

# 计算对应餐桌未结账的总金额
SELECT SUM(money) FROM bill WHERE dining_id = 1 AND state = '未结账'; 

SELECT * FROM bill WHERE dining_id = 1 AND state = '未结账';     
UPDATE bill SET state = ? WHERE dining_id = ? AND state = '未结账'
UPDATE dining SET state = ? WHERE id =
SELECT id,state FROM dining WHERE id = 1 AND state = '已经预定'
                                         