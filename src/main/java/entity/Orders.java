package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Orders {
    private Menu orderMenus;
    private int countOrder;

    public Menu getOrderMenus() {
        return orderMenus;
    }

    public void setOrderMenus(Menu orderMenus) {
        this.orderMenus = orderMenus;
    }

    public int getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(int countOrder) {
        this.countOrder = countOrder;
    }
}

