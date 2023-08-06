package com.rainbowsea.mhl.service;

import com.rainbowsea.mhl.dao.DiningDaoImpl;
import com.rainbowsea.mhl.dao.impl.DiningDao;
import com.rainbowsea.mhl.javaBean.Dining;
import com.rainbowsea.mhl.service.impl.DiningService;

import java.util.List;


/**
 * 处理有关 dining 餐桌表的业务，统一在这里处理
 */
public class DiningServiceImpl implements DiningService {
    private DiningDao<Dining> diningDAO = new DiningDaoImpl();  // 作为类属性存在。



    /**
     * 查询显示所有餐桌的餐桌位，以及餐桌状态
     * @return  List<Dining>
     */
    public List<Dining> allDining() {
        // 注意别名上的使用
        String sql = "select dining_id as diningId,dining_state as diningState, " +
                "order_name as orderName,order_tel as orderTel " +
                "from dining";  // 测试一下
        List<Dining> list = diningDAO.selectMany(sql, Dining.class);
        return list;
    }


    /**
     * 通过查询餐桌位，判断返回餐桌 javaBean
     * @param diningId 餐桌位
     * @return Dining 查询到返回javaBean实例对象,查询不到返回null
     */
    public Dining getDiningById(int diningId) {
        String sql = "select dining_id as diningId,dining_state as diningState, " +
                "order_name as orderName,order_tel as orderTel " +
                "from dining where dining_id = ?";  // 测试一下
        Dining dining = diningDAO.selectSingle(sql, Dining.class, diningId);

        return dining;

    }


    /**
     * 预定餐桌，更新餐桌的状态
     * @param diningId 餐桌位
     * @param orderName 预定餐桌的用户名
     * @param orderTel 预定餐桌的联系电话
     * @return boolean
     */
    public boolean updateDining(int diningId,String orderName,String orderTel) {
        String sql = "update dining set order_name = ?,order_tel = ?,dining_state = ? " +
                "where dining_id = ?";   // 测试一下
        int update = diningDAO.update(sql, orderName, orderTel, "已预定", diningId);

        return update > 0;

    }



    /**
     * 点餐服务，修改餐桌的状态,为“就餐中”
     * @param diningId
     * @return boolean
     */
    public boolean setStateById(int diningId) {
        String sql = "update dining set dining_state = ? where dining_id = ?";  // 测一测
        int update = diningDAO.update(sql, "就餐中", diningId);

        return update > 0;

    }



    /**
     * 通过餐桌编号，将对应餐桌初始化为，最初无人，预定/就餐的状态
     * @param diningId 餐桌编号
     * @return boolean 成功 true,失败false
     */
    public boolean billInit(int diningId) {
        String sql = "update dining set dining_state = ?,order_name = '',order_tel = '' where dining_id = ?";  // 测一测
        int update = diningDAO.update(sql, "空",diningId);

        return update > 0;
    }



    /**
     * 通过餐桌编号，查询该餐桌是否为 "已预定" 的状态
     * @param diningId 餐桌编号
     * @return 是返回 true,不是返回 false
     */
    public boolean getDiningByIdAndState(int diningId) {
        String sql = "select dining_state as diningState from dining where dining_id = ? and dining_state = ?";  // 测一测

        Dining dining = diningDAO.selectSingle(sql, Dining.class,diningId, "已预定");

        return dining != null;


    }
}
