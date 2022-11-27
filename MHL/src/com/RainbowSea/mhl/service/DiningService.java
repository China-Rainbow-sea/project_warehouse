package com.RainbowSea.mhl.service;

import com.RainbowSea.mhl.dao.BillDAO;
import com.RainbowSea.mhl.dao.DiningDAO;
import com.RainbowSea.mhl.javaBean.Dining;

import java.util.List;

/**
 * 对Dining (餐桌)数据表的业务操作，统一封装到这里
 */
public class DiningService {
    // 定义 DiningDAO 实例对象，用于调用其中的方法，处理业务
    private DiningDAO diningDAO = new DiningDAO(); // 作为类属性存在

    // 定义  BillDAO 实例对象，
    private BillDAO billDAO = new BillDAO();


    /**
     * 调用查询DiningDAO类中继承的方法：查询 dining 餐桌状态的所有信息
     * @return List<Dining> 返回dining 餐桌状态的所有信息
     */
    public List<Dining> allDining() {
        String sql = "select id,state,order_name as orderName,order_tel as orderTel from dining"; // 测一下
        List<Dining> list = diningDAO.selectMany(sql, Dining.class);
        return list;
    }



    /**
     * 通过餐桌编号，查询是否存在该餐桌，存在返回 Dining对象，不存在返回 null
     * @param id 餐桌编号
     * @return Dining 存在返回对象，不存在返回null
     */
    public Dining getDiningById(int id) {
        String sql = "select id,state from dining where id = ?";  // sql语句执行验证看看是否存在错误
        return diningDAO.selectSingle(sql,Dining.class,id);
    }



    /**
     * 根据 dining 中的id(餐桌编号),更新修改其 dining数据表中餐桌的状态state，改为“已经预定”
     * @param id 餐桌编号
     * @param orderName 预定人的姓名
     * @param orderTel 预定人的电话
     * @return boolean 更新成功返回 true,失败返回 fasle
     */
    public boolean setDiningState(int id,String orderName,String orderTel) {
        String sql = "update dining set state = '已经预定',order_name = ? " +  // 注意+字符串拼接的空格,防止成了一句话
                ",order_tel = ? where id = ?";  // 测试一下
        int update = diningDAO.update(sql, orderName, orderTel, id);

        return update == 1;  // 因为我们这里只是更新一条记录，所以是 1
    }



    /**
     * 通过餐桌编号id 更新餐桌的状态，比如：就餐中，用于这里的点餐服务
     * @param id 餐桌编号
     * @param state 餐桌状态
     * @return 修改成功返回 true,失败返回 false
     */
    public int updateDiningState(int id,String state) {
        String sql = "update dining set state = ? where id = ?";
        int update = diningDAO.update(sql, state, id);

        return update;
    }



    /**
     * 结账，格式化餐桌为“无人预定的状态”，通过餐桌编号
     * @param id 餐桌编号
     * @param state 餐
     * @return 成功返回true,失败返回 false
     */
    public boolean updateDiningInto(int id,String state) {
        String sql = "update dining set state = ?,order_name = '',order_tel = '' where id = ?";
        int update = diningDAO.update(sql, state, id);
        return update > 0;

    }


    /**
     *  通过餐桌编号，查询该餐桌的状态是否为 "已经预定"
     * @param id 餐桌编号
     * @return boolean 是返回true, 不是返回false
     */
    public boolean getDiningByIdAndState(int id) {
        String sql = "select id,state from dining where id = ? and state = ?"; // 测一测
        Dining dining = diningDAO.selectSingle(sql, Dining.class, id, "已经预定");

        return dining != null;
    }


}
