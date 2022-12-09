package com.RainbowSea.mhl.dao;


import com.RainbowSea.mhl.javaBean.Employee;
import com.RainbowSea.mhl.utils.JDBCUtilsByDruid;

/**
 * 通过继承  JDBCUtilsByDruid<T> 抽象类方法，调用其中的方法
 * 对 employee (员工)数据表进行一个基本的增删改查的操作，
 * 也可以附加其他子类独有的方法
 */
public class EmployeeDAO extends JDBCUtilsByDruid<Employee> {

}
