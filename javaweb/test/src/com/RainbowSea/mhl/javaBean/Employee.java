package com.RainbowSea.mhl.javaBean;


/**
 * 对应 employee 员工数据表
 */
public class Employee {
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


}
