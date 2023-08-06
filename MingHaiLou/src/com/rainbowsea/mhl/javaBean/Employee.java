package com.rainbowsea.mhl.javaBean;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Objects;

/**
 * 对应 employee 员工数据表
 * 同时为该JavaBean绑定上 session 会话的监听器
 */
public class Employee implements HttpSessionBindingListener {
    private int id;                 // 主键编号
    private int employeeId;         // 员工ID
    private String employeeName;    // 员工姓名
    private String employeePwd;     // 员工密码
    private String employeeJod;     // 员工的职务


    public Employee() {
        // 无参构造器必须要有，用于 apche-dbutils 底层的调用
    }


    public Employee(int id, int employeeId, String employeeName, String employeePwd, String employeeJod) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePwd = employeePwd;
        this.employeeJod = employeeJod;
    }

    // set/get 同样必须要有用于apche-dbtuils底层的反射的调用赋值，
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePwd() {
        return employeePwd;
    }

    public void setEmployeePwd(String employeePwd) {
        this.employeePwd = employeePwd;
    }

    public String getEmployeeJod() {
        return employeeJod;
    }

    public void setEmployeeJod(String employeeJod) {
        this.employeeJod = employeeJod;
    }


    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        // 用户登录了
        // Dept 类型的对象向session 中存放了
        // 获取到ServletContext 应用域对象，一个应用只要一个
        ServletContext application = httpSessionBindingEvent.getSession().getServletContext();

        Object onlinecount = application.getAttribute("onlinecount");

        // 用户第一次登录，一定是 onlinecout 是不存在的
        if(onlinecount == null) {
            // 向ServletContext 应用域对象，添加该用户，第一个用户的登录
            application.setAttribute("onlinecount", 1);
        } else {
            // 第二个用户的登录，就存在这个 onlinecount 应用域的对象信息了
            int count = (Integer) onlinecount;  // 将从 ServletContext 应用存储的信息用户的个数取出

            count++; // 增加用户 + 1
            application.setAttribute("onlinecount",count); // 修改，增加用户个数信息。
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        // 用户退出了
        // Dept 类型的对象从 session 对象中删除了。
        // 存储在 ServletContext中的 onlinecount key 对应的 value 用户个数 --

        // 1. 获取到存储 ServletContext中的用户个数
        ServletContext servletContext = httpSessionBindingEvent.getSession().getServletContext();
        Object onlinecount = servletContext.getAttribute("onlinecount");

        int count = (Integer) onlinecount;
        count --;
        servletContext.setAttribute("onlinecount",count);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId() && getEmployeeId() == employee.getEmployeeId() && Objects.equals(getEmployeeName(), employee.getEmployeeName()) && Objects.equals(getEmployeePwd(), employee.getEmployeePwd()) && Objects.equals(getEmployeeJod(), employee.getEmployeeJod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmployeeId(), getEmployeeName(), getEmployeePwd(), getEmployeeJod());
    }
}
