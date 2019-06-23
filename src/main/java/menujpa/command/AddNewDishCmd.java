package menujpa.command;

import menujpa.entity.Dish;
import menujpa.service.DishService;

import java.util.Scanner;

public class AddNewDishCmd extends MenuCmd {
    private Scanner scanner;

    public AddNewDishCmd(DishService dishService, Scanner scanner) {
        super(dishService);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        String[] dishData = getDishData(scanner);
        if (dishData == null || dishData.length != 3) {
            System.out.println(">>.....Incorrect data format!");
            return;
        }
        try {
            double price = Double.parseDouble(dishData[1]);
            double weight = Double.parseDouble(dishData[2]);
            Dish dish = new Dish(dishData[0], price, weight);
            String resp = dishService.addDish(dish);
            System.out.println(resp);
        } catch (NumberFormatException ex) {
            System.out.println(">>.....Incorrect data format!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private String[] getDishData(Scanner scanner){
        System.out.println(">>.....Input dish name:");
        String name = scanner.nextLine();
        System.out.println(">>.....Input dish price (in 0.0 format):");
        String priceStr = scanner.nextLine();
        System.out.println(">>.....Input dish weight (in 0.0 format):");
        String weightStr = scanner.nextLine();
        if (name.isEmpty() || priceStr.isEmpty() || weightStr.isEmpty()){
            System.out.println(">>.....Empty fields!");
            return null;
        }
        return new String[]{name, priceStr, weightStr};
    }
}
