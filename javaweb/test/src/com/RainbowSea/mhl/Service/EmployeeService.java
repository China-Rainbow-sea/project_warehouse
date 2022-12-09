package com.RainbowSea.mhl.Service;

import com.RainbowSea.mhl.dao.EmployeeDAO;
import com.RainbowSea.mhl.javaBean.Employee;

/**
 * 处理有关 employee 员工数据表的业务，统一都在这里实现
 */
public class EmployeeService {
    // 创建EmployeeDAO 实例对象，用于调用其中的方法
    private EmployeeDAO employeeDAO = new EmployeeDAO();



    /**
     * 通过 employee_name 员工的姓名，employee_pwd 员工的密码
     * 查询 该员工是否存在
     * 注意 pwd密码被 md5()函数加密了，查询时同样需要使用 md5()解密
     * @param employeeName 员工姓名
     * @param employeePwd 员工密码（）
     * @return  存在返回 Employee javaBean 实例对象,不存在返回 null
     */
    public Employee getEmployeeNameByPwd(String employeeName, String employeePwd) {
        String sql = "select employee_name as employeeName, employee_id as employeeId " + //注意使用空格分隔,不要成一句话了
                " from employee where employee_name = ? and employee_pwd = md5(?)";  // 占位符不要加单引号,不然就成字符串了
        Employee employee = employeeDAO.selectSingle(sql, Employee.class, employeeName, employeePwd);
        return employee;
    }
}
