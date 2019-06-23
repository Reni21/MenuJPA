package menujpa;

import menujpa.command.*;
import menujpa.command.update.UpdateDishCmd;
import menujpa.daoimpl.DishDaoImpl;
import menujpa.service.DishService;
import menujpa.util.JpaUtil;
import menujpa.util.LiquibaseHelper;

import java.util.Scanner;

public class MenuApp {
    private static DishService dishService = new DishService(new DishDaoImpl());

    private static String menuCmd =
            "<-------------------------- MENU -------------------------->" + "\n" +
                    "  1          - \"add new dish\"" + "\n" +
                    "  2          - \"show all dishes\"" + "\n" +
                    "  3          - \"show all discounted dishes\"" + "\n" +
                    "  4          - \"show dishes in prise range\"" + "\n" +
                    "  5          - \"show dish data\"" + "\n" +
                    "  6          - \"update dish\"" + "\n" +
                    "  7          - \"collect combo menu\"" + "\n" +
                    "  8          - \"delete dish\"" + "\n" +
                    "  help       - \"show menu\"" + "\n" +
                    "  q          - \"quite the app\"" + "\n" +
                    "<---------------------------------------------------------->" + "\n";

    public static void main(String[] args) {
        new LiquibaseHelper().runScripts(); // set precondition to the dish table
        startMenuApp();
        JpaUtil.closeEntMngFactory();
        System.out.println(">>.....Finish app");
    }

    private static void startMenuApp() {
        System.out.println(menuCmd);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String cmd = scanner.nextLine();
                MenuCmd menuCmd = null; // press button imitation
                switch (cmd) {
                    case "1":
                        menuCmd = new AddNewDishCmd(dishService, scanner);
                        break;
                    case "2":
                        menuCmd = new ShowAllDishesCmd(dishService);
                        break;
                    case "3":
                        menuCmd = new ShowDiscountedDishesCmd(dishService);
                        break;
                    case "4":
                        menuCmd = new ShowDishesInPriseRangeCmd(dishService, scanner);
                        break;
                    case "5":
                        menuCmd = new ShowDishDataCmd(dishService, scanner);
                        break;
                    case "6":
                        menuCmd = new UpdateDishCmd(dishService, scanner);
                        break;
                    case "7":
                        menuCmd = new CollectComboMenuCmd(dishService, scanner);
                        break;
                    case "8":
                        menuCmd = new DeleteDishCmd(dishService, scanner);
                        break;
                    case "help":
                        System.out.println(MenuApp.menuCmd);
                        break;
                    case "q":
                        return;
                    default:
                        System.out.println(">>.....Unsupported command \"" + cmd + "\"");
                        break;
                }
                if (menuCmd != null) {
                    menuCmd.execute();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
