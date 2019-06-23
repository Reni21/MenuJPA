package menujpa.command;

import menujpa.entity.Dish;
import menujpa.service.DishService;

import java.util.List;
import java.util.Scanner;

public class CollectComboMenuCmd extends MenuCmd {
    private Scanner scanner;

    public CollectComboMenuCmd(DishService dishService, Scanner scanner) {
        super(dishService);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(">>......Input weight for combo-menu (in 0.0 format):");
        String weightStr = scanner.nextLine();
        if (weightStr.isEmpty()) {
            System.out.println(">>.....Empty field!");
            return;
        }
        try {
            Double weight = Double.parseDouble(weightStr);
            List<Dish> dishes = dishService.getComboOfDishes(weight);
            if (dishes == null){
                System.out.println(">>.....No result for your request");
            } else {
                System.out.println();
                dishes.forEach(System.out::println);
            }
        } catch (NumberFormatException ex) {
            System.out.println(">>.....Incorrect data format!");
        } catch (Exception ex){
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
