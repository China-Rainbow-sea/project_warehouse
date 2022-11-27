package com.RainbowSea.mhl.service;


import com.RainbowSea.mhl.dao.BillDAO;
import com.RainbowSea.mhl.javaBean.Bill;
import com.RainbowSea.mhl.javaBean.Menu;

import java.util.List;
import java.util.UUID;

/**
 * Bill 账单表的业务操作，统一在里
 */
public class BillService {
    // 定义BillDAO对象调用其方法，
    private BillDAO billDAO = new BillDAO();  // 作为类属性存在

    // 定义 menuService 调用其中的方法，更加菜品编号，找到菜名
    MenuService menuService = new MenuService();

    // 创建 DiningService 调用其中的 初始化餐桌为，无人预定的状态的方法
    DiningService diningService = new DiningService();


    /**
     * 点餐，更新账单，记录信息，同时计算总额(数量*菜的单价)，
     * 以及更新 dining 餐桌的状态为 "就餐中"
     * @param diningId 餐桌编号
     * @param menuId   菜名编号
     * @param nums     对应菜的数量
     * @return  boolean 成功返回true,失败返回false
     */
    public boolean billMenu(int diningId,int menuId,int nums) {
        String billId = UUID.randomUUID().toString();  // 随机生成一个“唯一”的数值，用作订单号

        Menu menu = menuService.getMenuById(menuId);  // 根据菜名编号返回menu javaBean 实例对象
        String menuName = menu.getName();   // 找到菜名
        double money = menu.getPrice();     // 找到该菜的单价
        String sql = "insert into bill(bill_id,bill_date,dining_id,menu_id,menu_name,nums,state,money) " +
                "values(?,now(),?,?,?,?,'未结账',?)";

        int update = billDAO.update(sql, billId, diningId, menuId, menuName,nums,nums*money);

        // 修改
        update += diningService.updateDiningState(diningId, "就餐中");

        return update > 1;
    }



    /**
     * 显示账单的全部信息，注意别名上的使用
     * @return  List<Bill>
     */
    public List<Bill> allBill() {
        // 注意数据库的命格式，使用上别名: 注意字符串拼接时“加上空格”不要成一句话了
        String sql = "SELECT b.id,bill_id AS billId,bill_date AS billDate,dining_id AS diningId, " +
                     "menu_id AS menuId,menu_name AS menuName,nums,money,state,m.`price` as menuPrice " +
                     "FROM bill b " +
                     "JOIN menu m " +
                     "ON b.`menu_id` = m.`id`";
        List<Bill> billList = billDAO.selectMany(sql, Bill.class);

        return billList;
    }


    /**
     * 查询指定餐桌位的“ 未结账 ”的账单信息:
     * @param diningId 餐桌位
     * @return List<Bill>
     */
    public List<Bill> getBillDiningId(int diningId) {
        String sql = "SELECT b.id,bill_id AS billId,bill_date AS billDate,dining_id AS diningId, " +
                "menu_id AS menuId,menu_name AS menuName,nums,money,state,m.`price` as menuPrice " +
                "FROM bill b " +
                "JOIN menu m " +
                "ON b.`menu_id` = m.`id` " +
                "where dining_id = ? and state = ?";
        List<Bill> billList = billDAO.selectMany(sql, Bill.class, diningId,"未结账");
        return billList;
    }



    /**
     * 查询某个餐桌的状态是否为 "未结账"返回 是true ，不是返回 false
     * @param diningId 餐桌位号
     * @return boolean 是未结账返回 true,不是返回 false
     */
    public boolean getBillState(int diningId) {
        String sql ="select state from bill where dining_id = ? and state = ?";
        Bill bill = billDAO.selectSingle(sql, Bill.class, diningId, "未结账");

        return bill != null;

    }


    /**
     * 查询某个餐桌的总金额:(注意计算的是未结账的总金额)，用于结账
     * @param diningId 餐桌编号
     * @return double 总金额
     */
    public double sumMoney(int diningId) {
        String sql = "select sum(money) from bill where dining_id = ? and state = '未结账'";
        return (double)billDAO.selectSum(sql,diningId);
    }



    /**
     *  结账：1. 修改bill 表中未结账的状态改为 支付方式:
     *       2. 修改预定餐桌dining 的餐桌状态为 "空"
     * @param diningId 餐位编号
     * @param payMode 支付方式
     * @return 成功返回 true,失败返回 false
     */
    public boolean payBill(int diningId, String payMode) {
        // 1. 修改 bill (账单)中的state 为结算方式:
        String sql = "update bill set state = ? where dining_id = ? and state = '未结账'";
        int update = billDAO.update(sql, payMode, diningId);

        if(update <= 0) { // 0 条记录被修改，更新失败
            return false;
        }

        // 2. 修改 dining(餐桌数据表)state为 "无人预定的状态" 空
        if(!diningService.updateDiningInto(diningId,"空")) {
            return false;
        }

        return true;  // 走到这说明都成功了。

    }





}
