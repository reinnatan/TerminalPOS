package service;

import entity.Menus;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface MenuActions {
    public void displayMenus() throws Exception;
    public void addMenus(String menuNames, String description, double price) throws Exception;
    public void deleteMenus(String id) throws Exception;
    public void updateMenus(String id) throws Exception;
}
