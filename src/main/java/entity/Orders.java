package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Orders {
    private Menus orderMenus;
    private int countOrder;

    public Menus getOrderMenus() {
        return orderMenus;
    }

    public void setOrderMenus(Menus orderMenus) {
        this.orderMenus = orderMenus;
    }

    public int getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(int countOrder) {
        this.countOrder = countOrder;
    }
}

