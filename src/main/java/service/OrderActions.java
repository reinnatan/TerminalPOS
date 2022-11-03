package service;


import entity.Menus;
import entity.Orders;

import java.util.List;

public interface OrderActions {

    public void addOrder(List<Orders> listOrder);
    public void updateOrder(String id, List<Orders>listOrders);
    public void deleteOrder(String id);
    public void payOrder(String id);

}
