package com.rainbowsea.mhl.dao.impl;


import java.util.List;

/**
 * 通过继承 父类 JDBCUtilsByDruid<T>  调用其中父类的方法
 * 对 menu 菜谱数据表进行一个“增删改查”的操作
 */
public interface MenuDao<T> {



    /**
     * 对数据表进行增删改操作
     *
     * @param sql  要执行是sql语句
     * @param args 可变参数(填充占位符)，可以不传参数，但不要传null,防止null引用
     * @return int 影响数据库的行数
     */
    public int update(String sql, Object... args);


    /**
     * 处理单条记录的查询结果集:
     * 使用 dbutils中的ResultSetHandler的BeanHandler(把当前select查询到的第一行的记录，存储到javabean实例中(ORM映射)对象)
     *
     * @param sql   执行的sql语句
     * @param clazz 泛型：javaBean实例对象(ORM映射)
     * @param args  可变参数(填充占位符)
     * @return 泛型：返回的javaBean实例对象(ORM映射)
     */
    public T selectSingle(String sql, Class<T> clazz, Object... args);


    /**
     * 查询数据表中多条记录，
     * 使用dbutils中的ResultSetHandler的BeanListHandler(查询所有记录，每一行记录存储到javabean实例对象(ORM映射)中，
     * 再将每个JavaBean实例对象存储到 List链表当中)
     *
     * @param sql   执行的sql语句
     * @param clazz 泛型，JavaBean类
     * @param args  可变参数(填充占位符)，可以不传参数，但不要传null
     * @return List<T> 泛型链表
     */
    public List<T> selectMany(String sql, Class<T> clazz, Object... args);


    /**
     * 查询特殊的结果 比如 count(),sum()
     * 使用dbutils中的ResultSetHandler的ScalarHandler(查询单个特殊的对象)
     *
     * @param sql  执行的sql语句
     * @param args 可变参数（填充占位符）,可以不传参数，但不要传null
     * @return Object 查询单个特殊的对象比如：count(),sum()
     */
    public Object selectSum(String sql, Object... args);

}
