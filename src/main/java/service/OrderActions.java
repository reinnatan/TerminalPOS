package service;


import entity.BillOrder;
import entity.BillOrders;
import entity.Menus;
import entity.Orders;

import java.util.List;

public interface OrderActions {

    void displayOrder() throws Exception;
    void addOrder(List<Orders> listOrder) throws Exception;
    void updateOrder(BillOrder billOrder) throws Exception;
    void deleteOrder(String id) throws Exception;
    void payOrder(String orderId, boolean isPay) throws Exception;
    BillOrder findOrder(String id) throws Exception;

}
