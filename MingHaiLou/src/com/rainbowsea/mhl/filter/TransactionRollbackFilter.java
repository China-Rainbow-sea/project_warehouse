package com.rainbowsea.mhl.filter;

import com.rainbowsea.mhl.utils.JDBCUtilsByDruid;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * 处理事务当中存在的异常，进行一个事务的回滚操作的过滤
 */
public class TransactionRollbackFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 1、获取数据库连接,从ThreadLocal 线程容器当中获取到的
        Connection connection = JDBCUtilsByDruid.getDruidConnection();

        try {
            // 重要操作：关闭自动提交功能
            connection.setAutoCommit(false);

            

            // 2、核心操作
            filterChain.doFilter(servletRequest, servletResponse);

            // 走到了这里，说明没有异常，可以正常提交事务
            // 3、提交事务
            connection.commit();
        } catch (Exception e) {
            // 存在异常事务回滚
            try {
                // 4、回滚事务
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            // 5、释放数据库连接 ，同时移除 ThreadLocal 当前线程容器存储的Connection连接对象
            // 防止内存泄漏
            JDBCUtilsByDruid.close(connection,null,null);
        }
    }

    @Override
    public void destroy() {

    }
}
