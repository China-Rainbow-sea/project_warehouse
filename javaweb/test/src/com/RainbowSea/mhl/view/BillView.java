package com.RainbowSea.mhl.view;


import com.RainbowSea.mhl.Service.BillService;
import com.RainbowSea.mhl.Service.DiningService;
import com.RainbowSea.mhl.Service.MenuService;
import com.RainbowSea.mhl.javaBean.Bill;
import com.RainbowSea.mhl.utils.Utility;

import java.util.List;

public class BillView {
    private DiningService diningService = new DiningService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();


    /**
     * 点餐服务
     */
    public void orderMenu() {
        // 1. 通过输入的餐桌位，进行点餐
        // 2. 判断输入的餐桌位是否存在，以及输入的菜编号是否存在
        // 中途输入-1 表示退出
        // 以上信息都没有问题，就修改 dining 对应餐桌位上的状态改为 “就餐中”
        // 以及 插入一条记录对应账单信息到 bill 中
        System.out.println("=================   点餐服务   ================= ");
        System.out.print("请输入点餐的餐桌号(-1 退出): ");
        int diningId = Utility.readInt();
        if(diningId == -1) {
            System.out.println("【退出点餐】");
            return;  // 退出该方法
        }
        // 判断餐桌号是否合理存在
        if(diningService.getDiningById(diningId) == null) {
            System.out.println("【该餐桌号不存在，请重新选择】");
            return ;
        }


        System.out.print("请输入点餐的菜品号(-1退出): ");
        int menuId = Utility.readInt();
        if(menuId == -1) {
            System.out.println("【退出点餐】");
            return;  // 退出该方法
        }
        // 判断菜品号是否合理存在
        if(menuService.getMenuById(menuId) == null) {
            System.out.println("该菜品号不存在，请重新选择");
            return ;
        }

        System.out.print("请输入菜品的数量(-1退出): ");
        int menuNums = Utility.readInt();
        if(menuNums == -1) {
            System.out.println("【退出点餐】");
            return;  // 退出该方法
        }


        // 走到这里，说明上述参数没有问题,更新数据表
        // 更新 dining 餐桌的状态改为 "就餐中",如果是直接就餐的，不用添加用户名和联系人''
        // 其他的则直接修改餐桌的状态,
        if(diningService.setStateById(diningId)) {
            // 添加一条账单记录在 bill 账单表中
            if(billService.billMenu(diningId,menuId,menuNums)) {
                System.out.println("【点餐成功】");
            } else {
                System.out.println("【点餐失败】");
            }

        } else {
            System.out.println("【点餐失败】");
            return;
        }



    }



    /**
     * 显示所有的账单信息
     */
    public void allBill() {
        List<Bill> billList = billService.allBill();
        System.out.println("\n编号\t\t日期\t\t\t\t\t餐桌号\t\t菜品号\t\t菜名\t\t数量\t\t单价\t\t总价\t\t状态");

        // 遍历链表的方式一:
        for(Bill b : billList) {
            System.out.println(b);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // 遍历链表的方式二
        // billList.forEach(System.out::println);
    }



    /**
     * 结账
     */
    public void payBill() {
        // 1.根据餐桌结账
        // 2.先判断所结账的餐桌是否存在，判断该餐桌是否是“未结账”，只有是未结账状态的才能结账
        // 3.显示对应餐桌 "未结账"的账单，并计算出总金额(未结账的不要计算)
        // 4.选择支付方式，
        // 5.最后确认是否支付: Y/N
        // 6.更新餐桌的状态为 "空，无人预定使用的状态",以及更新 bill 表单的状态为支付方式

        System.out.print("请选择要结账的餐桌号(-1退出): ");
        int diningId = Utility.readInt();
        // 判断餐桌是否存在
        if(diningService.getDiningById(diningId) == null) {
            System.out.println("【该餐桌不存在，请重新选择】");
            return ;
        }

        if(!billService.getBillState(diningId)) {
            System.out.println("【该餐位已经结账/无人就餐/已预定还未点餐！！！】");
            return;
        }

        // 走到这说明，餐位存在，且未结账，显示该餐位账单信息，以及总金额(未结账的总金额)
        System.out.println("\n编号\t\t日期\t\t\t\t\t餐桌号\t\t菜品号\t\t菜名\t\t数量\t\t单价\t\t总价\t\t状态");
        List<Bill> billList = billService.getBillByDiningId(diningId);
        // 遍历链表方式一:
        for(Bill b : billList) {
            System.out.println(b);
        }
        System.out.println("一共消费了: "+billService.sumMoney(diningId)+"¥");

        System.out.print("你选择支付方式(支付宝/微信/银行卡/现金,回车表示退出): ");
        String payMode = Utility.readString(20,""); // 说明如果回车，就返回

        if("".equals(payMode)) { // 说明如果回车，就返回
            System.out.println("【取消结账】");
            return;
        }
        // 最后的 Y/N 确定
        System.out.print("是否确认结账【Y/N】: ");
        char key = Utility.readConfirmSelection(); // 不区分大小写，会自动转换为大写
        if(key == 'N') {
            System.out.println("【取消结账】");
            return;
        }

        // 更新对应餐桌号的账单状态为: 未结账方式
        if(! billService.setBillStateBydiningId(diningId,payMode)) {
            System.out.println("【支付失败】");
            return;
        }

        // 更新结账后，餐桌的状态为 "初始化，无人就餐无人预定的状态"
        if(diningService.billInit(diningId)) {
            System.out.println("【支付成功】");
        } else {
            System.out.println("【支付失败】");
        }



    }



}
