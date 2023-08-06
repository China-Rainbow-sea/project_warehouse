package com.rainbowsea.mhl.service.impl;


import com.rainbowsea.mhl.javaBean.Bill;

import java.util.List;


/**
 * 统一处理有关 bill 账单数据表的业务
 */
public interface BillService {


    /**
     * 点餐服务，生成对应账单，插入到 bill 数据表中
     *
     * @param diningId // 餐桌位
     * @param menuId   // 菜品号
     * @param menuNums // 该菜的数量
     * @return boolean 成功返回true,否则 false
     */
    public boolean billMenu(int diningId, int menuId, int menuNums);


    /**
     * 查询显示所有的账单信息
     *
     * @return List<Bill>
     */
    public List<Bill> allBill();


    /**
     * 根据 餐桌编号,判断账单中对应的 状态是否为 "未结账"，是返回 true,不是返回 false
     *
     * @param diningId 餐桌编号
     * @return boolean 是返回 true,不是返回 false
     */
    public boolean getBillState(int diningId);


    /**
     * 通过餐桌编号，显示对应餐桌 "未结账" 的账单
     *
     * @param diningId 餐桌编号
     * @return List<Bill>
     */
    public List<Bill> getBillByDiningId(int diningId);


    /**
     * 计算对应餐桌编号的 “未结账”的消费总金额
     *
     * @param diningId 餐桌编号
     * @return double
     */
    public double sumMoney(int diningId);


    /**
     * 通过餐桌编号更新，"未结账“的状态修改为 "支付方式"
     *
     * @param diningId 餐桌编号
     * @param payMode  支付方式
     * @return boolean
     */
    public boolean setBillStateBydiningId(int diningId, String payMode);
}
