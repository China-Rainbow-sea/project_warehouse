package com.RainbowSea.mhl.view;


import com.RainbowSea.mhl.javaBean.Bill;
import com.RainbowSea.mhl.javaBean.Dining;
import com.RainbowSea.mhl.javaBean.Employee;
import com.RainbowSea.mhl.javaBean.Menu;
import com.RainbowSea.mhl.service.BillService;
import com.RainbowSea.mhl.service.DiningService;
import com.RainbowSea.mhl.service.EmployeeService;
import com.RainbowSea.mhl.service.MenuService;
import com.RainbowSea.mhl.utils.Utility;

import java.util.List;

public class MhlView {
    private boolean loop = true;   // 控制菜单中的循环，退出满汉楼

    private String key = "";   // 用于接受用户的选择，这里使用字符串

    // 定义EmployeeService实例对象，调用其中的方法对 employee数据表的业务操作
    private EmployeeService employeeService = new EmployeeService();

    // 定义DiningService实例对象，调用其中的方法对 dining数据表的业务进行操作
    private DiningService diningService = new DiningService();

    // 定义MenuService实例对象，调用其中的方法对 menu数据表的业务进行操作
    private MenuService menuService = new MenuService();

    // 定义BillService实例对象，调用其中的方法对 dill 数据表的业务进行操作
    private BillService billService = new BillService();

    public static void main(String[] args) {
        MhlView mhlView = new MhlView();  // 实例化对象，静态方法调用非静态方法
        mhlView.mainMenu();
    }


    /**
     * 显示菜单
     */
    public void mainMenu() {
        while(loop) {
            System.out.println("============ 满汉楼 ============");
            System.out.println("\t\t 1.登录满汉楼");
            System.out.println("\t\t 2.退出满汉楼");
            System.out.print("请输入你的选择: ");
            key = Utility.readString(1);  // 工具类，读取字符串，不是整数
            switch(key) {  // 注意结果是 字符串
                case "1" :
                    System.out.println("登入满汉楼");
                    System.out.print("请输入用户名: ");
                    String name = Utility.readString(20);
                    System.out.print("请输入密码：");
                    String pwd = Utility.readString(20);

                    // 查询数据库表中的信息，判断正确,数据表中不存在返回null
                    Employee employee = employeeService.getEmployeeNameAndPwd(name,pwd);
                    if(employee!= null) {
                        while(loop) {
                            System.out.println("============ 满汉楼【"+employee.getName()+"(二级菜单)】 ============");
                            System.out.println("\t\t 1.显示餐桌状态 ");
                            System.out.println("\t\t 2.预定餐桌");
                            System.out.println("\t\t 3.显示所有菜品");
                            System.out.println("\t\t 4.点餐服务");
                            System.out.println("\t\t 5.查看账单");
                            System.out.println("\t\t 6.结账");
                            System.out.println("\t\t 7.取消预定");
                            System.out.println("\t\t 0.退出满汉楼");

                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);  // 读取选择，只能是长度为 1 的字符串

                            switch(key) {  // 注意是字符串
                                case "1" :
                                    showDining();
                                    break;
                                case "2" :
                                    tableDining();
                                    break;
                                case "3" :
                                    allListMenu();
                                    break;
                                case "4" :
                                    orderMenu();
                                    break;
                                case "5" :
                                    allBill();
                                    break;
                                case "6" :
                                    payBill();
                                    break;
                                case "7" :
                                    cancelReserve();
                                    break;
                                case "0" :
                                    System.out.println("退出满汉楼！！！");
                                    loop = false;  // 修改跳出循环条件
                                    break;
                                default :
                                    System.out.println("您的选择有误，请重新选择！！！");
                                    break;
                            }
                        }

                    } else {
                        System.out.println("用户名/密码错误！！！");
                    }
                    break;
                case "2" :
                    System.out.println("退出满汉楼");
                    loop = false;  // 将循环条件改为 false 结束循环
                    break;
                default :
                    System.out.println("你的选择有误，请重新输入");
                    break;
            }
        }
    }


    /**
     * 显示所有餐桌的状态:
     */
    public void showDining() {
        List<Dining> list = diningService.allDining();
        System.out.println("\n餐桌编号\t\t餐桌状态");
        // 遍历链表，方式一
        for(Dining d : list) {
            System.out.println(d);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // 或者，方式二：
        // list.forEach(System.out::println);
    }



    /**
     * 2. 预定餐桌
     */
    public void tableDining() {
        // 1.根据餐桌的编号预定，需要判断餐桌的编号是否存在
        // 2.需要判断餐桌是否为空，空才可以预定
        // 3.预定需要输入预定人的用户名，电话
        // 4.预定成功则改变对应 dining数据表中的餐桌状态改为 “已经预定”
        // 5.中途输入-1可以取消预定;
        // 6. 最后的y/n 确定是否真的预定
        System.out.println("============ 【预定餐桌】 ============ ");
        System.out.print("请选择要预定的餐桌编号(-1退出): ");
        int id = Utility.readInt();  // 输入整数,餐桌的编号
        if(id == -1) {
            System.out.println("【取消预定】");
            return ;  // 中断退出预定
        }

        System.out.print("是否确认预定【Y/N】: ");
        char key = Utility.readConfirmSelection();  // 读取一个字符只能是y/n 忽略大小写(小写的会自动转换为大写的)
        if(key == 'N') {  // 不预定，退出
            System.out.println("【取消预定】");
            return ;
        }

        // 走到这说明要预定:
        // 1. 首先判断所预定的餐桌编号是否合理存在。
        Dining dining = diningService.getDiningById(id);
        if(dining == null) {
            System.out.println("【您预定的餐桌不存在】");
            return ; // 终止，退出，防止影响后面的操作
        }

        // 2. 判断存在的餐桌状态是否为 “空”，为空才可以预定
        if(!"空".equals(dining.getState())) {  // 为空返回 true,!取反false
            System.out.println("【该餐桌已经被人预定了或正在就餐中】");
            return ; // 终止，退出
        }

        // 走到这里说明，真的可以预定，输入信息，更新餐桌状态
        System.out.print("预定人的名字：");
        String orderName = Utility.readString(50);  // 该方法让其输入的必须是规定长度的字符

        System.out.print("预定人的电话: ");
        String orderTel = Utility.readString(12);  // 规定12 个字符

        if(diningService.setDiningState(id,orderName,orderTel)) {
            System.out.println("【预定成功】");
        } else {
            System.out.println("【预定失败】");
        }

    }



    /**
     * 显示所有的菜品
     */
    public void allListMenu() {
        List<Menu> menus = menuService.allMenu();
        System.out.println("\n菜品编号\t\t菜名名\t\t类别\t\t价格");
        // 遍历链表方式一:
        menus.forEach(System.out::println);

        // 方式二:
//        for(Menu m : menus) {
//            System.out.println(m);
//        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }



    /**
     * 点餐服务
     */
    public void orderMenu() {
        // 1. 根据餐桌号点餐，需要判断餐桌号的合理性
        // 2. 根据菜的编号，点菜，需要判断该菜编号是否存在,存在通过查询插入菜名，以及菜的编号
        // 3. 选择菜的数量,插入 bill 表中
        // 4. 中途 -1 可以退出
        System.out.println("============ 【点餐服务】 ============ ");
        System.out.print("请输入点餐的餐桌号(-1退出): ");
        int diningId = Utility.readInt();  // 读取一个整数

        if(diningId == -1) {
            System.out.println("【取消点餐】");
            return; // 终止，
        }

        // 验证餐桌号是否合理,通过查询餐桌号是否存在，不存在返回null
        Dining dining = diningService.getDiningById(diningId);
        if(dining == null) {
            System.out.println("【该餐桌不存在】");
            return;  // 终止
        }

        System.out.print("请输入点餐的菜品号(-1退出): ");
        int orderMenuId = Utility.readInt();
        if(orderMenuId == -1) {
            System.out.println("【取消点餐】");
            return; // 终止，防止影响后续的操作继续执行
        }

        // 验证菜品编号，是否存在
        Menu menu = menuService.getMenuById(orderMenuId);
        if(menu == null) {
            System.out.println("【该菜品号不存在】");
            return;  // 终止
        }

        System.out.print("请输入该菜品的数量(-1退出): ");
        int nums = Utility.readInt();
        if(nums == -1) {
            System.out.println("【取消点餐】");
            return; // 终止，防止影响后续的操作继续执行
        }

        // 走到这里说明，可以点餐，更改数据了
        if(billService.billMenu(diningId,orderMenuId,nums)){
            System.out.println("【点餐成功】");
        } else {
            System.out.println("【点餐失败】");
        }

    }



    /**
     * 显示所有餐桌的账单信息;
     */
    public void allBill() {

        List<Bill> list = billService.allBill();
        System.out.println("\n编号\t\t日期\t\t\t\t\t餐桌号\t\t菜品号\t\t菜名\t\t数量\t\t单价\t\t总价\t\t状态");
        // 遍历链表方式一:
        for(Bill b : list) {
            System.out.println(b);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // 方式二:
        // list.forEach(System.out::println);
    }



    /**
     * 结账
     */
    public void payBill() {
        // 1.根据餐桌号结账
        // 2.判断餐桌号是否存在,以及该餐桌是否为 “未结账状态”，只有为未结账状态才可以结账
        // 3.显示对应餐桌的账单信息，以及总金额: (注意是：未结账的金额，已结账的不要算) bill表
        // 4.选择结账方式: 现金/支付宝/微信/银行卡 修改更新 bill数据表中的 state 状态
        // 5.最后确认结账
        // 6.结账，清空餐桌置为空 dining表餐桌信息
        System.out.println("============ 【结 账】 ============ ");
        System.out.print("请选择要结账的餐桌编号(-1退出): ");
        int diningId = Utility.readInt();  // 限制输入整数
        if(diningId == -1) {
            System.out.println("【取消结账】");
            return ;  // 终止
        }

        // 判断该餐桌是否存在
        Dining dining = diningService.getDiningById(diningId);
        if(dining == null) {
            System.out.println("【该餐桌不存在】");
            return;
        }

        // 验证该餐桌是否为 “未结账状态”，只有为未结账状态才可以结账
        if(!billService.getBillState(diningId)) {
            System.out.println("【该餐位已经结账/无人就餐/已预定还未点餐！！！】");
            return;
        }

        // 走到这说明，餐位存在，且未结账，显示该餐位账单信息，以及总金额(未结账的总金额)
        System.out.println("\n编号\t\t日期\t\t\t\t\t餐桌号\t\t菜品号\t\t菜名\t\t数量\t\t单价\t\t总价\t\t状态");
        List<Bill> billList = billService.getBillDiningId(diningId);
        billList.forEach(System.out::println);
        System.out.println("总共消费: "+billService.sumMoney(diningId)+"¥");

        System.out.print("请选择结账方式(现金/支付宝/微信/银行卡,回车表示退出): ");
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

        // 修改bining数据表餐桌的状态"空"，同时修改 bill账单为：支付方式
        if(billService.payBill(diningId,payMode)) {
            System.out.println("【支付成功】");
        } else {
            System.out.println("【支付失败】");
        }

    }


    /**
     * 取消预定的餐桌
     */
    public void cancelReserve() {
        // 1. 根据你输入的餐桌号，取消对应餐桌的 state 预定状态
        // 2. 判断该餐桌是否存在，以及该餐桌是否为"已预定的状态",只有为已预定的状态才可以取消预定
        // 已经点餐就餐了无法取消预定
        // 最后确认是否 y/n ，修改 dining 餐桌数据表 state 的为 "空" 无人预定

        System.out.print("请输入你要取消预定的餐桌位(-1表示退出): ");
        int diningId = Utility.readInt();
        if(diningId == -1) {
            System.out.println("【退出】");
            return ;
        }

        // 判断该餐桌编号是否存在
        if(diningService.getDiningById(diningId) == null) {
            System.out.println("【该餐桌编号不存在，请重新选择】");
            return ;
        }


        // 判断餐桌的状态是否为 已经预定了，
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

        // 恢复到，餐桌无人预定的状态
        if(diningService.updateDiningInto(diningId,"空")) {
            System.out.println("【取消预定成功】");
        }

    }

}
