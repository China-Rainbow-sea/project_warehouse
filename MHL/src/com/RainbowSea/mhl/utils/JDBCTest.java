package com.RainbowSea.mhl.utils;

import java.util.List;

public class JDBCTest extends JDBCUtilsByDruid<Actor> {
    public static void main(String[] artgs) {
        JDBCTest jdbcTest = new JDBCTest();  // 静态方法调用非静态方法
        jdbcTest.test();
    }

    /**
     * update() 增删改测试
     */
    public void test1() {
        // 测试update方法的增删改
        String sql = "insert into actor(name,sex,birth,phone) values(?,?,?,?)"; // 占位符不要加单引号,Java当中的sql结尾不要加;分号
        // int update1 = super.update(sql, "李华", "男", "2020-1-1", "985");

        String sql2 = "update actor set phone = ? where id = ?";
        // int update2 = super.update(sql2, "211", 6);

        String sql3 = "delete from actor where id = ?";
        int update = super.update(sql3, 6);

        System.out.println( update > 0 ? "成功" : "失败");

    }


    /**
     * select查询测试
     */
    public void test() {
        // 测试单个对象的特殊查询
        String sql = "select sum(id) from actor";
        Object sum = super.selectSum(sql);
        // System.out.println(sum);


        // 测试一条记录的查询操作
        String sql2 = "select * from actor where id = ?";
        Actor actor = super.selectSingle(sql2, Actor.class, 1);
        // System.out.println(actor);

        // 测试多条记录的处理
        String sql3 = "select * from actor where id ";
        List<Actor> list = super.selectMany(sql3, Actor.class);

        for(Actor actor1 : list) {
            System.out.println(actor1);
        }
        System.out.println("*****************************************");
        list.forEach(System.out::println);
    }
}
