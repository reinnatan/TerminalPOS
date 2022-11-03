package service;

import entity.Menu;
import entity.Menus;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface MenuActions {
    void displayMenus() throws Exception;
    void addMenus(String menuNames, String description, double price) throws Exception;
    void deleteMenus(String id) throws Exception;
    void updateMenus(String id, Scanner sc) throws Exception;
    Menu searchMenu(String searchId) throws Exception;
}
