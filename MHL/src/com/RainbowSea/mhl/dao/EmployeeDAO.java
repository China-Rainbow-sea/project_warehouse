package com.RainbowSea.mhl.dao;

import com.RainbowSea.mhl.javaBean.Employee;
import com.RainbowSea.mhl.utils.JDBCUtilsByDruid;

/**
 * 继承了 JDBCUtilsByDruid<Employee> 通用数据表的增删改查的封装工具类,
 * 从而调用其父类的方法，对数据表 employee(员工)表 进行增删改查操作
 */
public class EmployeeDAO extends JDBCUtilsByDruid<Employee> {

}
