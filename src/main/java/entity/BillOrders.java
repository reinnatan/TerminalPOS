package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "billorders")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BillOrders {
    List<BillOrder> listBillOrder= new ArrayList<BillOrder>();

    public List<BillOrder> getListBillOrder() {
        return listBillOrder;
    }

    public void setListBillOrder(List<BillOrder> listBillOrder) {
        this.listBillOrder = listBillOrder;
    }
}
