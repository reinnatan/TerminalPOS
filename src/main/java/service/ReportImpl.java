package service;

import entity.BillOrder;
import entity.BillOrders;
import entity.Orders;
import entity.ReportOrder;
import utils.DatabaseUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportImpl implements ReportActions{
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
    public void monthlyReport(int month, int year) throws Exception{
        Map<String, ReportOrder> reportOrders = new HashMap<String, ReportOrder>();
        BillOrders billOrders = getBillOrders();
        for(BillOrder billOrder: billOrders.getListBillOrder()){
            LocalDate localDate = billOrder.getHistoryLog().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.getMonthValue()==month &&localDate.getYear()==year && billOrder.isPay()){
                for(Orders order: billOrder.getListMenuOrder()){
                    if (reportOrders.containsKey(order.getOrderMenus().getId())){
                        reportOrders.get(order.getOrderMenus().getId()).countOrder += order.getCountOrder();
                    }else{
                        ReportOrder reportOrder = new ReportOrder();
                        reportOrder.menuID = order.getOrderMenus().getId();
                        reportOrder.menuName = order.getOrderMenus().getMenuNames();
                        reportOrder.countOrder = 1;
                        reportOrder.priceOrder = order.getOrderMenus().getPrice();
                        reportOrders.put(order.getOrderMenus().getId(), reportOrder);
                    }
                }
            }
        }

        if(reportOrders.size()>0) {
            int totalSold = 0;
            System.out.println("==============================================");
            System.out.println("=====    LIST MENU TERJUAL BULAN INI     =====");
            System.out.println("==============================================");
            for (Map.Entry<String, ReportOrder> report : reportOrders.entrySet()) {
                System.out.println("Menu Id : " + report.getKey());
                System.out.println("Menu Name : " + report.getValue().menuName);
                System.out.println("Jumlah item terjual : " + report.getValue().countOrder);
                totalSold += report.getValue().countOrder;
            }
            System.out.println("==============================================");
            System.out.println("Jumlah barang terjual bulan ini : " + totalSold);
            System.out.println("==============================================");
        }else{
            System.out.println("Tidak ada item terjual di bulan "+month+" di tahun "+year);
        }
    }

    @Override
    public void yearReport(int year) throws Exception{
        Map<String, ReportOrder> reportOrders = new HashMap<String, ReportOrder>();
        BillOrders billOrders = getBillOrders();
        for(BillOrder billOrder: billOrders.getListBillOrder()){
            LocalDate localDate = billOrder.getHistoryLog().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.getYear()==year && billOrder.isPay()){
                for(Orders order: billOrder.getListMenuOrder()){
                    if (reportOrders.containsKey(order.getOrderMenus().getId())){
                        reportOrders.get(order.getOrderMenus().getId()).countOrder += order.getCountOrder();
                    }else{
                        ReportOrder reportOrder = new ReportOrder();
                        reportOrder.menuID = order.getOrderMenus().getId();
                        reportOrder.menuName = order.getOrderMenus().getMenuNames();
                        reportOrder.countOrder = 1;
                        reportOrder.priceOrder = order.getOrderMenus().getPrice();
                        reportOrders.put(order.getOrderMenus().getId(), reportOrder);
                    }
                }
            }
        }

        if(reportOrders.size()>0) {
            int totalSold = 0;
            System.out.println("==============================================");
            System.out.println("=====    LIST MENU TERJUAL TAHUN INI     =====");
            System.out.println("==============================================");
            for (Map.Entry<String, ReportOrder> report : reportOrders.entrySet()) {
                System.out.println("Menu Id : " + report.getKey());
                System.out.println("Menu Name : " + report.getValue().menuName);
                System.out.println("Jumlah item terjual :  " + report.getValue().countOrder);
                totalSold += report.getValue().countOrder;
            }
            System.out.println("==============================================");
            System.out.println("Jumlah item terjual tahun ini : " + totalSold);
            System.out.println("==============================================");
        }else{
            System.out.println("Tidak ada item terjual di tahun "+year);
        }
    }

    @Override
    public void monthlyProfit(int month, int year) throws Exception{
        Map<String, ReportOrder> reportOrders = new HashMap<String, ReportOrder>();
        BillOrders billOrders = getBillOrders();
        for(BillOrder billOrder: billOrders.getListBillOrder()){
            LocalDate localDate = billOrder.getHistoryLog().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.getMonthValue()==month &&localDate.getYear()==year && billOrder.isPay()){
                for(Orders order: billOrder.getListMenuOrder()){
                    if (reportOrders.containsKey(order.getOrderMenus().getId())){
                        reportOrders.get(order.getOrderMenus().getId()).countOrder += order.getCountOrder();
                    }else{
                        ReportOrder reportOrder = new ReportOrder();
                        reportOrder.menuID = order.getOrderMenus().getId();
                        reportOrder.menuName = order.getOrderMenus().getMenuNames();
                        reportOrder.countOrder = 1;
                        reportOrder.priceOrder = order.getOrderMenus().getPrice();
                        reportOrders.put(order.getOrderMenus().getId(), reportOrder);
                    }
                }
            }
        }

        if(reportOrders.size()>0) {
            int totalSold = 0;
            int profitSold = 0;
            System.out.println("==============================================");
            System.out.println("=====    LIST MENU TERJUAL BULAN INI     =====");
            System.out.println("==============================================");
            for (Map.Entry<String, ReportOrder> report : reportOrders.entrySet()) {
                System.out.println("Menu Id : " + report.getKey());
                System.out.println("Menu Name : " + report.getValue().menuName);
                System.out.println("Total Item Terjual : " + report.getValue().countOrder);
                System.out.println("Total Harga Terjual :  " + report.getValue().countOrder * report.getValue().priceOrder);
                totalSold += report.getValue().countOrder;
                profitSold += report.getValue().countOrder * report.getValue().priceOrder;
            }
            System.out.println("==============================================");
            System.out.println("Jumlah item terjual bulan ini : " + totalSold);
            System.out.println("Total harga terjual bulan ini : " + profitSold);
            System.out.println("==============================================");
        }else{
            System.out.println("Tidak ada item terjual di bulan "+month+" di tahun "+year);
        }
    }

    @Override
    public void yearProfit(int year) throws Exception{
        Map<String, ReportOrder> reportOrders = new HashMap<String, ReportOrder>();
        BillOrders billOrders = getBillOrders();
        for(BillOrder billOrder: billOrders.getListBillOrder()){
            LocalDate localDate = billOrder.getHistoryLog().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (localDate.getYear()==year && billOrder.isPay()){
                for(Orders order: billOrder.getListMenuOrder()){
                    if (reportOrders.containsKey(order.getOrderMenus().getId())){
                        reportOrders.get(order.getOrderMenus().getId()).countOrder += order.getCountOrder();
                    }else{
                        ReportOrder reportOrder = new ReportOrder();
                        reportOrder.menuID = order.getOrderMenus().getId();
                        reportOrder.menuName = order.getOrderMenus().getMenuNames();
                        reportOrder.countOrder = 1;
                        reportOrder.priceOrder = order.getOrderMenus().getPrice();
                        reportOrders.put(order.getOrderMenus().getId(), reportOrder);
                    }
                }
            }
        }

        if(reportOrders.size()>0) {
            int totalSold = 0;
            int profitSold = 0;
            System.out.println("==============================================");
            System.out.println("=====    LIST MENU TERJUAL TAHUN INI     =====");
            System.out.println("==============================================");
            for (Map.Entry<String, ReportOrder> report : reportOrders.entrySet()) {
                System.out.println("Menu Id : " + report.getKey());
                System.out.println("Menu Name : " + report.getValue().menuName);
                System.out.println("Total Item Terjual : " + report.getValue().countOrder);
                System.out.println("Total Harga Terjual :  " + report.getValue().countOrder * report.getValue().priceOrder);
                totalSold += report.getValue().countOrder;
                profitSold += report.getValue().countOrder * report.getValue().priceOrder;
            }
            System.out.println("==============================================");
            System.out.println("Total item terjual tahun ini : " + totalSold);
            System.out.println("Total harga terjual tahun ini : " + profitSold);
            System.out.println("==============================================");
        }else{
            System.out.println("Tidak ada item terjual di tahun "+year);
        }
    }
}
