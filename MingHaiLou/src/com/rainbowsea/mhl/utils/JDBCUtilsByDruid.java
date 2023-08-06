package com.rainbowsea.mhl.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;


/**
 * 通过数据表的增，删，改，差的封装处理
 * 使用 druid数据库连接池，dbutils工具类
 * 定义 为 abstract 抽象类，不可 new
 *
 * @param <T> 泛型
 */
public abstract class JDBCUtilsByDruid<T> {
    // 数据库连接池对象
    private static DataSource dataSource = null;
    // 定义 dbutils 工具类当中的 QueryRunner 对象执行sql语句
    private QueryRunner queryRunner = new QueryRunner();  // 作为类属性存在

    // 创建 ThreadLocal 容器存储绑定线程相关的 信息
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();


    // 一个应用一个数据库连接池(数据库连接池比作生产连接的工厂,)static 静态代码块和类一起加载到内存当中,
    // 仅仅执行一次,所有对象共用
    static {
        try {
            // 读取对应目录下的配置文件信息

            //正确方式三  javaWed 读取配置文件
            InputStream is = JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties");


//            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");  // 默认是src目录下的文件
//            FileInputStream is = new FileInputStream("src\\druid.properties");

//            InputStream inputStream = new FileInputStream("src/main/resources/config/application.properties");

//            InputStream is = new FileInputStream("src/com/druid.properties");

            Properties properties = new Properties();
//            properties.load(new FileInputStream("src\\druid.properties"));

            properties.load(is);  // 以简单的线性格式读取属性列表(关键字/元素对)

            // 传入读取到配置文件的对象，创建 druid 数据库连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);  // 将编译异常转换为运行异常抛出
        }

    }

    /**
     * 获取到所创建的druid数据库连接池,其中的一个连接对象
     * 并将 连接对象 Connection 存储到 ThreadLocal 容器当中
     *
     * @return Connection
     */
    public static  Connection getDruidConnection() {

        // 第二次就是从 ThreadLocal 容器当中获取到了
        Connection connection = threadLocal.get();  // 从ThreadLocal容器中获取
        try {
            // 第一次ThreadLocal 是为空的,从数据库连接池当中获取
            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;

    }


    /**
     * 对数据表进行增删改操作
     *
     * @param sql  要执行是sql语句
     * @param args 可变参数(填充占位符)，可以不传参数，但不要传null,防止null引用
     * @return int 影响数据库的行数
     */
    public int update(String sql, Object... args) {

        // 获取到 druid 数据库连接池其中的一个连接对象
        Connection connection = getDruidConnection();

        // 通过 dbutils工具类QueryRunner获取到操作数据库的对象,并执行sql语句
        try {
            int count = queryRunner.update(connection, sql, args);
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);  // 将编译异常转换为运行异常抛出
        } finally {
            // 关闭资源,归还连接
            close(connection, null, null);
        }

    }


    /**
     * 处理单条记录的查询结果集:
     * 使用 dbutils中的ResultSetHandler的BeanHandler(把当前select查询到的第一行的记录，存储到javabean实例中(ORM映射)对象)
     *
     * @param sql   执行的sql语句
     * @param clazz 泛型：javaBean实例对象(ORM映射)
     * @param args  可变参数(填充占位符)
     * @return 泛型：返回的javaBean实例对象(ORM映射)
     */
    public T selectSingle(String sql, Class<T> clazz, Object... args) {
        // 获取从 druid 数据库连接池中获取到其中的一个连接对象
        Connection connection = getDruidConnection();

        try {
            // 创建 javaBean 实例的存储方式,
            BeanHandler<T> beanHandler = new BeanHandler<T>(clazz);
            // 执行使用 dbutils中的QueryRunner类中封装的 query方法执行sql语句，以及处理select
            T t = queryRunner.query(connection, sql, beanHandler, args);
            return t;

            // 可以将 BeanHandler 存储方式和 QueryRunner.query执行sql语句一体化
            // T t2 = queryRunner.query(connection, sql, new BeanHandler<T>(clazz), args);

        } catch (SQLException e) {
            throw new RuntimeException(e);  // 将编译异常转换为运行异常抛出
        } finally {
            // 关闭资源，归还连接
            close(connection, null, null);
        }

    }


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
    public List<T> selectMany(String sql, Class<T> clazz, Object... args) {
        // 获取到druid 数据库连接池中的一个连接对象
        Connection connection = getDruidConnection();

        try {
            // 创建存储select结果集的形式
            BeanListHandler<T> beanListHandler = new BeanListHandler<T>(clazz);
            // 使用dbutils工具类的/QueryRunner.query()方法执行sql语句，并处理select
            List<T> list = queryRunner.query(connection, sql, beanListHandler, args);
            return list;
            // 可以将 BeanListHandler<T> 存储方式和 QueryRunner.query执行sql语句一体化
            // List<T> list1 = queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);  // 将编译异常转换为运行异常抛出
        } finally {
            // 关闭资源，归还连接
            close(connection, null, null);
        }

    }


    /**
     * 查询特殊的结果 比如 count(),sum()
     * 使用dbutils中的ResultSetHandler的ScalarHandler(查询单个特殊的对象)
     *
     * @param sql  执行的sql语句
     * @param args 可变参数（填充占位符）,可以不传参数，但不要传null
     * @return Object 查询单个特殊的对象比如：count(),sum()
     */
    public Object selectSum(String sql, Object... args) {
        // 获取druid数据库连接池的其中的一个连接对象
        Connection connection = getDruidConnection();

        try {
            // 定义存储方式为: ScalarHandler
            ScalarHandler scalarHandler = new ScalarHandler();
            // 直接使用 dbutils中的QueryRunner()中的query,执行sql并处理select
            Object o = queryRunner.query(connection, sql, scalarHandler, args);
            return o;

            // 可以将 ScalarHandler 存储方式和 QueryRunner.query执行sql语句一体化
            // Object O2 = queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);  // 将编译异常转换为运行异常抛出
        } finally {
            // 关闭资源，归还连接
            close(connection, null, null);
        }
    }


    /**
     * 关闭连接，关闭资源("数据库连接池归还连接")
     * 最晚使用的资源，最先关闭
     * 使用 DbUtils.closeQuietly() 封装的静态方法
     *同时把 ThreadLocal容器当中的Connection移除了
     * @param connection 连接
     * @param statement  操作
     * @param resultSet  查询
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        // 关闭处理 select 查询结果集资源
        DbUtils.closeQuietly(resultSet);
        // 关闭操作数据库对象资源
        DbUtils.closeQuietly(statement);
        // 关闭连接，归还连接
        DbUtils.closeQuietly(connection);
        threadLocal.remove();  // 注意关闭资源的时候需要将绑定在threadLocal移除

    }
}
