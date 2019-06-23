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

    private static String menuActions =
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
        System.out.println(menuActions);
        startMenuApp();
        JpaUtil.closeEntMngFactory();
        System.out.println(".....Finish app");
    }

    private static void startMenuApp() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String action = scanner.nextLine();
                MenuCmd menuCmd = null; // press button imitation
                switch (action) {
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
                        System.out.println(menuActions);
                        break;
                    case "q":
                        return;
                    default:
                        System.out.println(">>.....Unsupported command \"" + action + "\"");
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
