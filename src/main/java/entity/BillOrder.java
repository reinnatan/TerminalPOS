package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "billorder")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillOrder {

    private String orderId;
    private boolean isPay;
    private List<Orders>listMenuOrder= new ArrayList<Orders>();
    private Date historyLog;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public List<Orders> getListMenuOrder() {
        return listMenuOrder;
    }

    public void setListMenuOrder(List<Orders> listMenuOrder) {
        this.listMenuOrder = listMenuOrder;
    }

    public Date getHistoryLog() {
        return historyLog;
    }

    public void setHistoryLog(Date historyLog) {
        this.historyLog = historyLog;
    }


}
