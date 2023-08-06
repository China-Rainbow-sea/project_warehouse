package com.rainbowsea.mhl.service.impl;


import com.rainbowsea.mhl.javaBean.Dining;

import java.util.List;

/**
 * 处理有关 dining 餐桌表的业务，统一在这里处理
 */
public interface DiningService {


    /**
     * 查询显示所有餐桌的餐桌位，以及餐桌状态
     * @return  List<Dining>
     */
    public List<Dining> allDining();


    /**
     * 通过查询餐桌位，判断返回餐桌 javaBean
     * @param diningId 餐桌位
     * @return Dining 查询到返回javaBean实例对象,查询不到返回null
     */
    public Dining getDiningById(int diningId);


    /**
     * 预定餐桌，更新餐桌的状态
     * @param diningId 餐桌位
     * @param orderName 预定餐桌的用户名
     * @param orderTel 预定餐桌的联系电话
     * @return boolean
     */
    public boolean updateDining(int diningId,String orderName,String orderTel);



    /**
     * 点餐服务，修改餐桌的状态,为“就餐中”
     * @param diningId
     * @return boolean
     */
    public boolean setStateById(int diningId);



    /**
     * 通过餐桌编号，将对应餐桌初始化为，最初无人，预定/就餐的状态
     * @param diningId 餐桌编号
     * @return boolean 成功 true,失败false
     */
    public boolean billInit(int diningId);


    /**
     * 通过餐桌编号，查询该餐桌是否为 "已预定" 的状态
     * @param diningId 餐桌编号
     * @return 是返回 true,不是返回 false
     */
    public boolean getDiningByIdAndState(int diningId);
}
