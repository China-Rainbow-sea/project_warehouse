package com.RainbowSea.mhl.service;

import com.RainbowSea.mhl.dao.EmployeeDAO;
import com.RainbowSea.mhl.javaBean.Employee;

/**
 * 对 employee员工表的业务处理，统一在这里处理
 */
public class EmployeeService {
    // 定义一个 EmployeeDAO 实例化对象，调用其中的方法，进行一个业务的处理
    private EmployeeDAO employeeDAO = new EmployeeDAO();  // 作为类属性存在


    /**
     * 通过用户名和密码查询，验证用户是否存在，用于登录
     * @param name 用户名
     * @param pwd 密码(在数据表中被 md5()加密了，查询的话需要使用上md5()解密)
     * @return Employee 实例对象javaBean(ORM)
     */
    public Employee getEmployeeNameAndPwd(String name,String pwd) {
        // 需要注意的是 employee数据表中的密码是经过 md5()函数加密了，所以我们也需要使用md5()解密,不然是查询不到数据的
        String sql = "select * from employee where name = ? and pwd = md5(?)";  // 占位符不要加单引号,Java当中sql不要;o分号结尾

        // 调用其 employeeDAO类中的方法，查询通过用户名和密码查询一条记录
        Employee employee = employeeDAO.selectSingle(sql, Employee.class, name, pwd);

        return employee;  // 没有找到返回null;
    }
}
