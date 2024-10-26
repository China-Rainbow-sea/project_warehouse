

# 在 springboot_vue 项目, 进行前端代码开发 

# 在 springboot_furn 项目, 进行后端代码开发

# 对应数据库执行的SQL语句的脚本

```
sql

-- 创建  springboot_vue
DROP DATABASE IF EXISTS springboot_vue;
CREATE DATABASE springboot_vue;
USE springboot_vue;

-- 删除数据表
DROP TABLE furn

-- 创建家居表
CREATE TABLE furn(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,   ## id 
`name` VARCHAR(64) NOT NULL,        ##  家居名 
`maker` VARCHAR(64) NOT NULL,        ##  厂商 
`price` DECIMAL(11,2) NOT NULL,        ##  价格
`sales` INT(11) NOT NULL,        ##  销量
`stock` INT(11) NOT NULL      ##  库存
);


-- 初始化家居数据
INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock`)
VALUES(NULL , '北欧风格小桌子' , '熊猫家居' , 180 , 666 , 7);
INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock`)
VALUES(NULL , '简约风格小椅子' , '熊猫家居' , 180 , 666 , 7 );
INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` )
VALUES(NULL , '典雅风格小台灯' , '蚂蚁家居' , 180 , 666 , 7 );
INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` )
VALUES(NULL , '温馨风格盆景架' , '蚂蚁家居' , 180 , 666 , 7 );


SELECT * FROM furn;



```


# 注意: 前端校验通过了，同时也要防止后端的校验，防止用户利用 curl或者是 postman 等等
网络工具提交信息，进行了修改
> 如果没有前端校验（比如用户使用其它方式来提交postman）
> 我们需要加入后端校验机制，会出现灰色框提示，后台不真正入库数据

这里后端的校验，使用 hibernate-validator.jar 文件
JSR 303 验证框架

```
xml

<!-- JSR303 数据校验支持引入 hibernate-validator-->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>6.1.0.Final</version>
        </dependency>
```

## JSR 303 验证框架
1. JSR 303 是Java为 Bean 数据合法性校验提供的标准框架，它已经包含了在Java EE中
2. JSR 303 通过在Bean属性上标注类似于 @NotNull,@Max 等标准的注解指定，并通过标准的验证
接口对 Bean 进行验证
3. JSR 303 提供的基本验证注解有:

