import entity.Menu;
import entity.Menus;
import service.MenuActions;
import service.MenuImpl;
import utils.DatabaseUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class SimplePOS {
    protected int firstChoose;
    protected int secondChoose;
    protected boolean isFirst = true;

    public static void main(String[] args) {
        new SimplePOS().runner(new Scanner(System.in));
    }

    void runner(Scanner sc){
        while(firstChoose!=3){
            if (isFirst) {
                showMenus();
                System.out.print("Pilihan Anda : ");
                firstChoose = sc.nextInt();
            }

            try {
                secondMenus(firstChoose, sc);
            }catch (Exception e){
                System.out.println("Terjadi Error "+e.getMessage());
            }
        }
        System.out.println("=========================================");
        System.out.println("====        GOOD BYE...              ====");
        System.out.println("=========================================");
    }

    public void showMenus(){
        System.out.println("=========================================");
        System.out.println("= 1.        Manage Menus                =");
        System.out.println("= 2.        Manage Order                =");
        System.out.println("= 3.        Exit                        =");
        System.out.println("=========================================");
    }

    public void secondMenus(int firstMenus, Scanner sc) throws Exception {
        isFirst = false;
        switch (firstMenus){
            case 1:
                System.out.println("=========================================");
                System.out.println("= 1.        Dispay Menu                 =");
                System.out.println("= 2.        Add Menu                    =");
                System.out.println("= 3.        Update Menu                 =");
                System.out.println("= 4.        Delete Menu                 =");
                System.out.println("= 5.        Exit                        =");
                System.out.println("=========================================");
                System.out.print("Pilihan Anda : ");
                secondChoose = sc.nextInt();
                secondChooseMenu(sc, secondChoose);
                break;
            case 2:
                System.out.println("==========================================");
                System.out.println("= 1.        Display Order                =");
                System.out.println("= 2.        Add Order                    =");
                System.out.println("= 3.        Update Order                 =");
                System.out.println("= 4.        Delete Order                 =");
                System.out.println("= 5.        Exit                         =");
                System.out.println("==========================================");
                secondChoose = sc.nextInt();
                secondChooseMenu(sc, secondChoose);
                break;
            default:
                break;
        }
    }

    public void secondChooseMenu(Scanner sc, int secondMenu) throws Exception {
        MenuActions impl = new MenuImpl();
        String menuId;
        switch (secondMenu){
            case 1:
                impl.displayMenus();
                break;
            case 2:
                sc = new Scanner(System.in);
                System.out.print("Menu Name : ");
                String menuName = sc.nextLine();
                System.out.print("Description : ");
                String descriptionMenu = sc.nextLine();
                System.out.print("Price : ");
                double price = sc.nextDouble();
                impl.addMenus(menuName, descriptionMenu, price);
            case 3:
                sc = new Scanner(System.in);
                impl.displayMenus();
                System.out.print("Menu Id : ");
                menuId = sc.nextLine();
                System.out.println();
                impl.updateMenus(menuId);
                break;
            case 4:
                impl.displayMenus();
                System.out.print("Menu id : ");
                sc = new Scanner(System.in);
                menuId = sc.nextLine();
                impl.deleteMenus(menuId);
                break;
            case 5:
                isFirst = true;
                break;
        }

    }


}
