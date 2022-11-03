package utils;

import entity.BillOrders;
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
    public static String DB_MENUS = "menus.xml";
    public static String DB_BILL_ORDERS = "bill_orders.xml";
    public static String PATH_DB =  DatabaseUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"/database";

    public static void writeObject(Object datas, String dbName) throws JAXBException {
        File folder = new File(PATH_DB);
        if(!folder.exists()){
            folder.mkdir();
        }
        JAXBContext context = null;
        if(datas.getClass().getName().contains("Menus")) {
            context = JAXBContext.newInstance(Menus.class);
        }else if(datas.getClass().getName().contains("BillOrders")){
            context = JAXBContext.newInstance(BillOrders.class);
        }

        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(datas, new File(dbName));
    }

    public static Object readObject(Class classType, String dbName) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(classType);
        return context.createUnmarshaller().unmarshal(new FileReader(dbName));
    }


}
