package service;

import entity.Menu;
import entity.Menus;
import utils.DatabaseUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class MenuImpl implements MenuActions{
    private Menus getMenus() throws Exception{
        Menus menus;
        if (new File(DatabaseUtils.DB_MENUS).exists()) {
            menus = (Menus) DatabaseUtils.readObject();
        }else{
            menus = new Menus();
            menus.setMenus(new ArrayList<>());
        }

        return menus;
    }

    @Override
    public void displayMenus() throws Exception {
        Menus menus = getMenus();
        System.out.println("=========================================");
        System.out.println("====           LIST MENUS            ====");
        System.out.println("=========================================");
        int countMenu =1;
        for(Menu menu: menus.getMenus()){
            System.out.println(countMenu +".  Id : "+menu.getId());
            System.out.println("    Name : "+menu.getMenuNames());
            System.out.println("    Description : "+menu.getDescription());
            System.out.println("    Price : "+menu.getPrice());
            countMenu+=1;
        }
        System.out.println("=========================================");
    }

    @Override
    public void addMenus(String menuName, String descriptionMenu, double price) throws Exception {
        Menus menus = getMenus();
        Menu menu = new Menu();
        menu.setId(UUID.randomUUID().toString());
        menu.setMenuNames(menuName);
        menu.setDescription(descriptionMenu);
        menu.setPrice(price);
        menus.getMenus().add(menu);
        DatabaseUtils.writeObject(menus, DatabaseUtils.DB_MENUS);
        System.out.println("Add menu success....");
    }

    @Override
    public void deleteMenus(String id) throws Exception {
        Menus menus = getMenus();
        for(Menu menu : menus.getMenus()){
            if(menu.getId().equalsIgnoreCase(id)){
                menus.getMenus().remove(menu);
                System.out.println("Delete menu is success...");
                DatabaseUtils.writeObject(menus, DatabaseUtils.DB_MENUS);
                break;
            }
        }
    }

    @Override
    public void updateMenus(String id) throws Exception {
        displayMenus();
        Menus menus = getMenus();
        Scanner sc = new Scanner(System.in);
        for(Menu menu : menus.getMenus()) {
            if (menu.getId().equalsIgnoreCase(id)) {
                System.out.print("Used existing name menu? (y/n) : ");
                sc = new Scanner(System.in);
                if(sc.nextLine().equalsIgnoreCase("n") ){
                    System.out.print("Name menu : ");
                    String menuName = sc.nextLine();
                    menu.setMenuNames(menuName);
                }

                System.out.print("Used existing description menu? (y/n) : ");
                sc = new Scanner(System.in);
                if(sc.nextLine().equalsIgnoreCase("n") ){
                    System.out.print("Description menu : ");
                    String menuName = sc.nextLine();
                    menu.setDescription(menuName);
                }

                System.out.print("Used existing price menu? (y/n) : ");
                sc = new Scanner(System.in);
                if(sc.nextLine().equalsIgnoreCase("n") ){
                    System.out.print("Price : ");
                    double price = sc.nextDouble();
                    menu.setPrice(price);
                }

                DatabaseUtils.writeObject(menus, DatabaseUtils.DB_MENUS);
                System.out.println("Update menu is successfully...");
                break;
            }
        }
    }
}
