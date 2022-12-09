package com.RainbowSea.mhl.view;


import com.RainbowSea.mhl.Service.EmployeeService;
import com.RainbowSea.mhl.javaBean.Employee;
import com.RainbowSea.mhl.utils.Utility;

public class MhlView {
    private boolean loop = true;

    // 创建 EmployeeService 实例，调用其中的方法
    private EmployeeService employeeService = new EmployeeService();  // 作为类属性存在
    private DiningView diningView = new DiningView();
    private MenuView menuView = new MenuView();
    private BillView billView = new BillView();

    public static void main(String[] args) {
        MhlView mhlView = new MhlView();  // 实例化对象，静态方法调用非静态的方法
        mhlView.mainMenu();

    }



    public void mainMenu() {
        String key = "";  // 用户输入的选择，

        while(loop) {
            System.out.println("=================  【满汉楼】  ================= ");
            System.out.println("              1. 登录满汉楼");
            System.out.println("              0. 退出满汉楼");
            System.out.print("请输入你的选择: ");

            key = Utility.readString(1);

            switch(key) {  // 注意是:字符串
                case "1" :
                    System.out.print("请输入用户名: ");
                    String name = Utility.readString(30);
                    System.out.print("请输入密码: ");
                    String pwd = Utility.readString(32);
                    Employee employee = employeeService.getEmployeeNameByPwd(name, pwd);
//
                    if(employee != null) {
                        System.out.println("【登录成功】");
                        while(loop) {
                            System.out.println("=====================   满汉楼【"+employee.getEmployeeName()+"】   " +
                                    "===================== ");
                            System.out.println("                 1.显示餐桌状态");
                            System.out.println("                 2.预定餐桌");
                            System.out.println("                 3.显示所有的菜品");
                            System.out.println("                 4.点餐服务");
                            System.out.println("                 5.查看所有账单");
                            System.out.println("                 6.结账");
                            System.out.println("                 7.取消预定");
                            System.out.println("                 0.退出满汉楼");
                            System.out.print("请输入您的选择: ");
                            key = Utility.readString(1);
                            switch(key) {  // 注意是字符串
                                case "1" :
                                    diningView.showDining();
                                    break;
                                case "2" :
                                    diningView.tableDining();
                                    break;
                                case "3" :
                                    menuView.showMenu();
                                    break;
                                case "4":
                                    billView.orderMenu();
                                    break;
                                case "5":
                                    billView.allBill();
                                    break;
                                case "6" :
                                    billView.payBill();
                                    break;
                                case "7":
                                    diningView.cancelReserve();
                                    break;
                                case "0":
                                    System.out.println("退出满汉楼");
                                    loop = false;  // 修改循环的结束条件
                                    break;
                                default:
                                    System.out.println("你的选择有误，请重新输入");
                                    break;
                            }
                        }

                    } else {
                        System.out.println("【用户名/密码错误/该用户不存在,请重新登录】!!!");
                    }
                    break;
                case "0":
                    System.out.println("【退出满汉楼】");
                    loop = false;  // 修改循环条件，结束死循环
                    break;
                default :
                    System.out.println("您的选择错误，请重新选择！！！");
                    break;
            }
        }

    }
}
