package utils;

import entity.Menu;
import entity.Menus;
import entity.Orders;
import service.MenuActions;
import service.MenuImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class DatabaseUtils {
    public static String DB_ORDERS = "src/main/java/database/orders.xml";
    public static String DB_MENUS = "src/main/java/database/menus.xml";
    public static String DB_BILL_ORDERS = "src/main/java/database/orders.xml";

    public static void writeObject(Object datas, String dbName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Orders.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(datas, new File(dbName));
    }

    public static Object readObject() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Menus.class);
        return context.createUnmarshaller().unmarshal(new FileReader(DB_MENUS));
    }


}
