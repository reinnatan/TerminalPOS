import entity.BillOrder;
import entity.Menu;
import entity.Orders;
import service.*;
import utils.DatabaseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SimplePOS {
    protected int firstChoose;
    protected int secondChoose;
    protected boolean isFirst = true;

    public static void main(String[] args) {
        new SimplePOS().runner(new Scanner(System.in));
    }

    void runner(Scanner sc){

        while(firstChoose!=4){
            if (isFirst) {
                showMenus();
                System.out.print("Pilihan Anda : ");
                firstChoose = sc.nextInt();
            }

            try {
                if (firstChoose ==1 || firstChoose ==2 || firstChoose==3) {
                    secondThirdMenus(firstChoose, sc);
                }
            }catch (Exception e){
                System.out.println("Terjadi Error "+e.getMessage());
            }
        }
        System.out.println();
        System.out.println("=========================================");
        System.out.println("====        GOOD BYE...              ====");
        System.out.println("=========================================");
    }

    public void showMenus(){
        System.out.println("=========================================");
        System.out.println("= 1.        Manage Menus                =");
        System.out.println("= 2.        Manage Order                =");
        System.out.println("= 3.        Reporting                   =");
        System.out.println("= 4.        Exit                        =");
        System.out.println("=========================================");
    }

    public void secondThirdMenus(int firstMenus, Scanner sc) throws Exception {
        isFirst = false;
        switch (firstMenus){
            case 1:
                System.out.println("=========================================");
                System.out.println("= 1.        Dispay Menu                 =");
                System.out.println("= 2.        Add Menu                    =");
                System.out.println("= 3.        Update Menu                 =");
                System.out.println("= 4.        Delete Menu                 =");
                System.out.println("= 5.        Exit                        =");
                System.out.println("=========================================");
                System.out.print("Pilihan Anda : ");
                secondChoose = sc.nextInt();
                secondChooseMenu(sc, secondChoose);
                break;
            case 2:
                System.out.println("==========================================");
                System.out.println("= 1.        Display Order                =");
                System.out.println("= 2.        Add Order                    =");
                System.out.println("= 3.        Update Menu Order            =");
                System.out.println("= 4.        Delete Order                 =");
                System.out.println("= 5.        Update payment Order         =");
                System.out.println("= 6.        Exit                         =");
                System.out.println("==========================================");
                System.out.print("Pilihan Anda : ");
                secondChoose = sc.nextInt();
                thirdChooseOrder(sc, secondChoose);
                break;
            case 3:
                System.out.println("==========================================");
                System.out.println("= 1.        Monthly Reporting            =");
                System.out.println("= 2.        Yearly Reporting             =");
                System.out.println("= 3.        Monthly Profit               =");
                System.out.println("= 4.        Yearly Profit                =");
                System.out.println("= 5.        Exit                         =");
                System.out.println("==========================================");
                System.out.print("Pilihan Anda : ");
                secondChoose = sc.nextInt();
                fourthChooseOrder(sc, secondChoose);
                break;
        }
    }

    //function to process
    public void secondChooseMenu(Scanner sc, int secondMenu) throws Exception {
        MenuActions impl = new MenuImpl();
        String menuId;
        switch (secondMenu){
            case 1:
                impl.displayMenus();
                break;
            case 2:
                //sc = new Scanner(System.in);
                sc.nextLine();
                System.out.print("Menu Name : ");
                String menuName = sc.nextLine();
                System.out.print("Description : ");
                String descriptionMenu = sc.nextLine();
                System.out.print("Price : ");
                double price = sc.nextDouble();
                impl.addMenus(menuName, descriptionMenu, price);
                break;
            case 3:
                //sc = new Scanner(System.in);
                impl.displayMenus();
                System.out.print("Menu Id : ");
                menuId = sc.next();
                System.out.println();
                impl.updateMenus(menuId, sc);
                break;
            case 4:
                impl.displayMenus();
                System.out.print("Menu id : ");
                //sc = new Scanner(System.in);
                menuId = sc.next();
                impl.deleteMenus(menuId);
                break;
            case 5:
                isFirst = true;
                break;
        }

    }

    //function to process the order
    public void thirdChooseOrder(Scanner sc, int secondMenu) throws Exception {
        OrderActions orderImpl = new OrderImpl();
        MenuActions menuImpl = new MenuImpl();
        switch (secondMenu){
            case 1:
                orderImpl.displayOrder();
                break;
            case 2:
                boolean addOrder = true;
                List<Orders> listOrders = new ArrayList<Orders>();
                while(addOrder){
                    menuImpl.displayMenus();
                    System.out.print("Menu id yang ingin di add : ");
                    //sc = new Scanner(System.in);
                    String menuID = sc.next();
                    System.out.print("Jumlah Orderan : ");
                    int totalOrder = sc.nextInt();

                    Menu menu = menuImpl.searchMenu(menuID);
                    Orders order = new Orders();
                    order.setCountOrder(totalOrder);
                    order.setOrderMenus(menu);
                    listOrders.add(order);

                    //sc = new Scanner(System.in);
                    System.out.print("Ingin tambah menu lagi : ");
                    String decission = sc.next();
                    if(decission.equalsIgnoreCase("n")){
                        break;
                    }
                }
                if(listOrders.size()>0){
                    orderImpl.addOrder(listOrders);
                }
                break;
            case 3:
                orderImpl.displayOrder();
                System.out.print("Order id yang ingin di rubah : ");
                //sc = new Scanner(System.in);
                String modifiedOrderId = sc.next();
                BillOrder tempOrder =  orderImpl.findOrder(modifiedOrderId);
                if(tempOrder != null) {
                    System.out.print("Modifikasi order menu ? (y/n) : ");
                    //sc = new Scanner(System.in);
                    String modifiedOrderMenu = sc.next();
                    if(modifiedOrderMenu.equalsIgnoreCase("y")) {
                        for (Orders order : tempOrder.getListMenuOrder()) {
                            System.out.println("Menu name : " + order.getOrderMenus().getMenuNames());
                            //sc = new Scanner(System.in);
                            System.out.print("Modifikasi jumlah menu ini ? (y/n) : ");
                            String updateOrder = sc.next();
                            if (updateOrder.equalsIgnoreCase("y")) {
                                System.out.print("Update jumlah order : ");
                                //sc = new Scanner(System.in);
                                order.setCountOrder(sc.nextInt());
                            }else {
                                System.out.print("Hapus order ini ? (y/n) : ");
                                //sc = new Scanner(System.in);
                                String deleteOrderId = sc.next();
                                if (deleteOrderId.equalsIgnoreCase("y")) {
                                    tempOrder.getListMenuOrder().remove(order);
                                    break;
                                }
                            }
                        }
                    }

                    System.out.print("Add order menu ? (y/n) : ");
                    //sc = new Scanner(System.in);
                    String addMenu = sc.next();
                    if(addMenu.equalsIgnoreCase("y")){
                        menuImpl.displayMenus();
                        System.out.print("Menu id yang ingin di tambah : ");
                        //sc = new Scanner(System.in);
                        String menuID = sc.next();
                        System.out.print("Jumlah Orderan : ");
                        int totalOrder = sc.nextInt();

                        Menu menu = menuImpl.searchMenu(menuID);
                        Orders order = new Orders();
                        order.setCountOrder(totalOrder);
                        order.setOrderMenus(menu);
                        tempOrder.getListMenuOrder().add(order);
                    }

                    orderImpl.updateOrder(tempOrder);
                }else{
                    System.out.println("Tidak ada order id yang dicari...");
                }
                break;
            case 4:
                orderImpl.displayOrder();
                System.out.print("Order id yang ingin dihapus : ");
                //sc = new Scanner(System.in);
                String deleteOrderId = sc.next();
                orderImpl.deleteOrder(deleteOrderId);
                break;
            case 5:
                //sc = new Scanner(System.in);
                orderImpl.displayOrder();
                System.out.print("Order id yang ingin di bayar : ");
                String orderIdStatus = sc.next();
                System.out.print("Bayar order ini ? (y/n) : ");
                String isPayment = sc.next();
                boolean finalIsPayment = isPayment.equalsIgnoreCase("y");
                orderImpl.payOrder(orderIdStatus, finalIsPayment);
                break;
            case 6:
                isFirst = true;
                break;
        }

    }

    public void fourthChooseOrder(Scanner sc, int thirdMenu) throws Exception {
        ReportActions reportActions = new ReportImpl();
        switch (thirdMenu){
            case 1:
                System.out.print("Bulan : ");
                int month =  sc.nextInt();
                System.out.print("Tahun : ");
                int tahun =  sc.nextInt();
                reportActions.monthlyReport(month, tahun);
                break;
            case 2:
                System.out.print("Tahun : ");
                tahun =  sc.nextInt();
                reportActions.yearReport(tahun);
                break;
            case 3:
                System.out.print("Bulan : ");
                month =  sc.nextInt();
                System.out.print("Tahun : ");
                tahun =  sc.nextInt();
                reportActions.monthlyProfit(month, tahun);
                break;
            case 4:
                System.out.print("Tahun : ");
                tahun =  sc.nextInt();
                reportActions.yearProfit(tahun);
                break;
            case 5:
                isFirst = true;
                break;
        }
    }


}
