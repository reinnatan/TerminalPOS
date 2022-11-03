package service;

import entity.BillOrder;
import entity.BillOrders;
import entity.Orders;
import utils.DatabaseUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderImpl implements OrderActions{

    private BillOrders getBillOrders() throws Exception {
        if (new File(DatabaseUtils.PATH_DB+File.separator+DatabaseUtils.DB_BILL_ORDERS).exists()) {
            return (BillOrders) DatabaseUtils.readObject(BillOrders.class, DatabaseUtils.PATH_DB+File.separator+DatabaseUtils.DB_BILL_ORDERS);
        } else {
            BillOrders billOrders =  new BillOrders();
            billOrders.setListBillOrder(new ArrayList<BillOrder>());
            return  billOrders;
        }
    }

    @Override
    public void displayOrder() throws Exception{
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        BillOrders listOrder = getBillOrders();
        System.out.println("=========================================");
        System.out.println("====           Order History         ====");
        System.out.println("=========================================");

        for(int a=0; a<listOrder.getListBillOrder().size(); a++) {
            BillOrder billOrder = listOrder.getListBillOrder().get(a);
            if (billOrder != null) {
                System.out.println("Order Id : " + billOrder.getOrderId());
                System.out.println("Date Order : " + sd.format(billOrder.getHistoryLog()));
                System.out.println("Status Payment order : " + billOrder.isPay());
                System.out.println("List menu order : ");
                int count = 1;

                for (int b=0; b<billOrder.getListMenuOrder().size(); b++) {
                    System.out.println("\t "+count + ". Name : " + billOrder.getListMenuOrder().get(b).getOrderMenus().getMenuNames());
                    System.out.println("\t  > Count : " + billOrder.getListMenuOrder().get(b).getCountOrder());
                    count += 1;
                }

            }else{
                System.out.println("No list orders...");
            }
            System.out.println("");
        }

        System.out.println("=========================================");

    }

    @Override
    public void addOrder(List<Orders> listOrder) throws Exception {
        BillOrders billOrders = getBillOrders();
        List<BillOrder> listBillOrder = billOrders.getListBillOrder();
        BillOrder billOrder = new BillOrder();
        billOrder.setOrderId(UUID.randomUUID().toString());
        billOrder.setListMenuOrder(listOrder);
        billOrder.setHistoryLog(new Date());
        billOrder.setPay(false);
        listBillOrder.add(billOrder);
        DatabaseUtils.writeObject(billOrders, DatabaseUtils.PATH_DB+File.separator+DatabaseUtils.DB_BILL_ORDERS);
        System.out.println("Add order success....");
    }

    @Override
    public void updateOrder(BillOrder billOrder) throws Exception {
        BillOrders billOrders = getBillOrders();
        List<BillOrder> listBillOrder = billOrders.getListBillOrder();

        int index = 0 ;
        for(BillOrder orderCreated : listBillOrder){
            if(orderCreated.getOrderId().equalsIgnoreCase(billOrder.getOrderId())){
                listBillOrder.set(index, billOrder);
                break;
            }
            index+=1;
        }
        DatabaseUtils.writeObject(billOrders, DatabaseUtils.PATH_DB+File.separator+DatabaseUtils.DB_BILL_ORDERS);
        System.out.println("Update order success....");
    }

    @Override
    public void deleteOrder(String id) throws Exception {
        BillOrders billOrders = getBillOrders();
        List<BillOrder> listBillOrder = billOrders.getListBillOrder();
        for(BillOrder orderCreated : listBillOrder) {
            if(orderCreated.getOrderId().equalsIgnoreCase(id)){
                listBillOrder.remove(orderCreated);
                break;
            }
        }
        DatabaseUtils.writeObject(billOrders, DatabaseUtils.PATH_DB+File.separator+DatabaseUtils.DB_BILL_ORDERS);
        System.out.println("Delete order success....");
    }

    @Override
    public void payOrder(String orderId, boolean isPay) throws Exception{
        BillOrders billOrders = getBillOrders();
        for(BillOrder billOrder:billOrders.getListBillOrder()){
            if(billOrder.getOrderId().equalsIgnoreCase(orderId)){
                billOrder.setPay(isPay);
            }
        }
        DatabaseUtils.writeObject(billOrders, DatabaseUtils.PATH_DB+File.separator+DatabaseUtils.DB_BILL_ORDERS);
        System.out.println("Update order pay success....");
    }

    @Override
    public BillOrder findOrder(String id) throws Exception {
        BillOrders billOrders =  getBillOrders();
        for(BillOrder billOrder: billOrders.getListBillOrder()){
            if(billOrder.getOrderId().equalsIgnoreCase(id)){
                return billOrder;
            }
        }
        return null;
    }
}
