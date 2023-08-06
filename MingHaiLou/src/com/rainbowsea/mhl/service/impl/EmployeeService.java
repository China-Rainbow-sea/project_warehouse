package com.rainbowsea.mhl.service.impl;


import com.rainbowsea.mhl.javaBean.Employee;

/**
 * 处理有关 employee 员工数据表的业务，统一都在这里实现
 */
public interface EmployeeService<T> {

    /**
     * 通过 employee_name 员工的姓名，employee_pwd 员工的密码
     * 查询 该员工是否存在
     * 注意 pwd密码被 md5()函数加密了，查询时同样需要使用 md5()解密
     * @param employeeName 员工姓名
     * @param employeePwd 员工密码（）
     * @return  存在返回 Employee javaBean 实例对象,不存在返回 null
     */
    public Employee getEmployeeNameByPwd(String employeeName, String employeePwd);
}
