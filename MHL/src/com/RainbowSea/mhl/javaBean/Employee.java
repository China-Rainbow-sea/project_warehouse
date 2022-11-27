package com.RainbowSea.mhl.javaBean;

/**
 * 对应 mhl数据库中的employee 数据表的JavaBean实例对象,ORM映射
 */
public class Employee {
    private int id;        // id
    private String empId;  // 用户ID这里注意数据表中的不同命名格式 emp_id,使用上别名
    private String pwd;    // 密码，注意使用了md5()加密
    private String name;   // 用户名
    private String jod;    // 职务

    public Employee() {
        // 无参构造器，必须定义用于 apache-dbutils底层的调用
    }

    public Employee(int id, String empId, String pwd, String name, String jod) {
        this.id = id;
        this.empId = empId;
        this.pwd = pwd;
        this.name = name;
        this.jod = jod;
    }

    // set/get 必须要有用于 apache-dbutils底层反射调用进行赋值，取值的操作
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJod() {
        return jod;
    }

    public void setJod(String jod) {
        this.jod = jod;
    }

}
