package com.rainbowsea.mhl.service;

import com.rainbowsea.mhl.dao.BillDaoImpl;
import com.rainbowsea.mhl.dao.impl.BillDao;
import com.rainbowsea.mhl.javaBean.Bill;
import com.rainbowsea.mhl.javaBean.Menu;
import com.rainbowsea.mhl.service.impl.BillService;
import com.rainbowsea.mhl.service.impl.MenuService;

import java.util.List;
import java.util.UUID;


/**
 * 统一处理有关 bill 账单数据表的业务
 */
public class BillServiceImpl implements BillService {

    private BillDao<Bill> billDAO = new BillDaoImpl();

    private MenuService menuService = new MenuServiceImpl();


    /**
     * 点餐服务，生成对应账单，插入到 bill 数据表中
     * @param diningId  // 餐桌位
     * @param menuId    // 菜品号
     * @param menuNums  // 该菜的数量
     * @return boolean 成功返回true,否则 false
     */
    public boolean billMenu(int diningId,int menuId,int menuNums) {
        // 1. 首先 使用UUID 作为订单号
        String billId = UUID.randomUUID().toString();  // 随机生成一个“唯一”的数值，用作订单号
        // 通过菜品编号获取到菜的单价，以及菜名
        Menu menu = menuService.getMenuById(menuId);
        String menuName = menu.getMenuName();   // 菜名
        double menuPrice = menu.getMenuPrice(); // 菜的单价

        // 插入数据，生成账单
        String sql = "insert into bill(bill_id,bill_date,dining_id,menu_id,menu_name,menu_nums,menu_money," +
                "bill_state) " +
                "values(?,now(),?,?,?,?,?,'未结账')";   // 测一测
        int update = billDAO.update(sql, billId, diningId, menuId, menuName, menuNums, menuNums * menuPrice);


        return update > 0;
    }



    /**
     * 查询显示所有的账单信息
     * @return List<Bill>
     */
    public List<Bill> allBill() {
        String sql = "SELECT id,bill_id AS billId,bill_date AS billDate,dining_id AS diningId,b.`menu_id` AS menuId, " +
                "b.`menu_name` AS menuName, menu_nums AS menuNums,menu_money AS menuMoney,m.`menu_price` AS menuPrice, " +
                "bill_state AS billState " +
                "FROM bill b " +
                "JOIN menu m " +
                "ON b.`menu_id` = m.`menu_id`";   // 测一测
        List<Bill> billList = billDAO.selectMany(sql, Bill.class);

        return billList;
    }



    /**
     * 根据 餐桌编号,判断账单中对应的 状态是否为 "未结账"，是返回 true,不是返回 false
     * @param diningId 餐桌编号
     * @return boolean 是返回 true,不是返回 false
     */
    public boolean getBillState(int diningId) {
        String sql = "select bill_state as billState from bill where dining_id = ? and bill_state = ?";  // 测一测

        Bill bill = billDAO.selectSingle(sql, Bill.class, diningId, "未结账");

        return bill != null;
    }



    /**
     * 通过餐桌编号，显示对应餐桌 "未结账" 的账单
     * @param diningId 餐桌编号
     * @return List<Bill>
     */
    public List<Bill> getBillByDiningId(int diningId) {
        String sql = "SELECT id,bill_id AS billId,bill_date AS billDate,dining_id AS diningId,b.`menu_id` AS menuId, " +
                "b.`menu_name` AS menuName, menu_nums AS menuNums,menu_money AS menuMoney,m.`menu_price` AS menuPrice, " +
                "bill_state AS billState " +
                "FROM bill b " +
                "JOIN menu m " +
                "ON b.`menu_id` = m.`menu_id` " +
                "WHERE b.`bill_state` = '未结账' and b.`dining_id` = ?";  // 测一测
        List<Bill> billList = billDAO.selectMany(sql, Bill.class,diningId);

        return billList;
    }



    /**
     * 计算对应餐桌编号的 “未结账”的消费总金额
     * @param diningId 餐桌编号
     * @return double
     */
    public double sumMoney(int diningId) {
        String sql = "SELECT SUM(menu_money) FROM bill WHERE dining_id = ? AND bill_state = ?";  // 测一测
        double sum = (double)billDAO.selectSum(sql, diningId, "未结账");

        return sum;
    }



    /**
     * 通过餐桌编号更新，"未结账“的状态修改为 "支付方式"
     * @param diningId 餐桌编号
     * @param payMode 支付方式
     * @return boolean
     */
    public boolean setBillStateBydiningId(int diningId,String payMode) {
        String sql = "update bill set bill_state = ? where dining_id = ? and bill_state = ?";
        int update = billDAO.update(sql, payMode, diningId, "未结账");

        return update > 0;
    }
}
