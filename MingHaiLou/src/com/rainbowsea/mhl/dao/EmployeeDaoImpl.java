package com.rainbowsea.mhl.dao;

import com.rainbowsea.mhl.dao.impl.EmployeeDao;
import com.rainbowsea.mhl.javaBean.Employee;
import com.rainbowsea.mhl.utils.JDBCUtilsByDruid;

import java.util.List;

public class EmployeeDaoImpl extends JDBCUtilsByDruid<Employee> implements EmployeeDao<Employee> {

    @Override
    public int update(String sql, Object... args) {
        return super.update(sql, args);
    }

    @Override
    public Employee selectSingle(String sql, Class<Employee> clazz, Object... args) {
        return super.selectSingle(sql, clazz, args);
    }

    @Override
    public List<Employee> selectMany(String sql, Class<Employee> clazz, Object... args) {
        return super.selectMany(sql, clazz, args);
    }

    @Override
    public Object selectSum(String sql, Object... args) {
        return super.selectSum(sql, args);
    }
}
