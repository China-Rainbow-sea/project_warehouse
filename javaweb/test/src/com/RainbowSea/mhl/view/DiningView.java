package com.RainbowSea.mhl.view;


import com.RainbowSea.mhl.Service.DiningService;
import com.RainbowSea.mhl.javaBean.Dining;
import com.RainbowSea.mhl.utils.Utility;

import java.util.List;

public class DiningView {
    private DiningService diningService = new DiningService();


    /**
     * 显示餐桌状态
     */
    public void showDining() {
        System.out.println("餐桌位\t\t餐桌状态\t\t预定人\t\t联系电话");
        List<Dining> list = diningService.allDining();

        // 遍历链表方式一:
        for(Dining d : list) {
            System.out.println(d);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // 遍历链表方式二:
        // list.forEach(System.out::println);

    }



    /**
     * 预定餐桌
     */
    public void tableDining(){
        /*
        根据输入的餐桌号,进行一个预定
        1. 首先需要判断该餐桌位是否存在,存在才可以预定,以及只有当“餐桌状态为"空"才可以预定
        2. 最后一个Y/N的确认预定
        3. 输入预定人的名字，以及预定人的电话 信息进行预定
        4. 预定修改 dining 餐桌数据表中对应的餐桌号中的信息(餐桌的状态改为'已预定')
         */
        System.out.print("请输入你要预定的餐桌位(-1退出): ");
        int diningId = Utility.readInt();
        if(diningId == -1) {
            System.out.println("【退出餐桌位】");
            return ;  // 退出该方法
        }

        // 判断选择的餐桌位是否存在
        Dining dining = diningService.getDiningById(diningId);
        if(dining == null) {
            System.out.println("【您选择的餐桌位不存在，请重新选择】");
            return ;
        }

        // 判断该餐桌位是否为 "空"的状态
        if(!"空".equals(dining.getDiningState())) {
            System.out.println("【该餐桌位已经被预定了/已经有人正在就餐了，暂时无法预定】");
            return ; // 提出该方法
        }

        System.out.print("是否确认预定Y/N: ");
        char key = Utility.readChar();
        if(key == 'N') {
            System.out.println("【退出预定】");
            return ;
        }


        // 走到这里说明，真的可以预定，输入信息，更新餐桌状态
        System.out.print("预定人的名字：");
        String orderName = Utility.readString(50);  // 该方法让其输入的必须是规定长度的字符

        System.out.print("预定人的电话: ");
        String orderTel = Utility.readString(12);  // 规定12 个字符

        // 更新餐桌状态，以及填充信息
        if(diningService.updateDining(diningId,orderName,orderTel)) {
            System.out.println("【预定成功】");
        } else {
            System.out.println("【预定失败】");
        }


    }



    /*
    取消餐桌的预定
     */
    public void cancelReserve() {
        // 根据餐桌编号，取消预定的餐桌
        // 1.先判断餐桌编号是否存在
        // 2. 只有为已预定的餐桌可以取消，正在就餐中/还未预定，无法取消预定
        // 最后的确认是否Y/N 取消预定，更新 dining餐桌为初始状态

        System.out.print("请输入你要取消预定的餐桌位(-1表示退出): ");
        int diningId = Utility.readInt();
        if(diningId == -1) {
            System.out.println("【退出】");
            return ;
        }

        // 判断餐桌编号是否存在
        if(diningService.getDiningById(diningId) == null) {
            System.out.println("【该餐桌编号不存在，请重新选择】");
            return ;
        }

        // 只有为 '已预定的餐桌才可以取消预定'
        if(!diningService.getDiningByIdAndState(diningId)) {
            System.out.println("【该餐桌为：正在就餐中/还未预定，无法取消预定】");
            return;
        }

        System.out.print("确认是否取消预定【Y/N】: ");
        char key = Utility.readConfirmSelection();
        if(key == 'N') {
            System.out.println("退出");
            return;
        }

        // 走到这说明可以取消预定了，更新初始化餐桌 dining 为无人的状态
        if(diningService.billInit(diningId)) {
            System.out.println("【取消预定成功】");
        } else {
            System.out.println("【取消预定失败】");
        }


    }
}
