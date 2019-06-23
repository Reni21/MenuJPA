package menujpa.command;

import menujpa.entity.Dish;
import menujpa.service.DishService;

import java.util.List;
import java.util.Scanner;

public class ShowDishesInPriseRangeCmd extends MenuCmd {
    private Scanner scanner;

    public ShowDishesInPriseRangeCmd(DishService dishService, Scanner scanner) {
        super(dishService);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(">>.....Input min price (in 0.0 format):");
        String min = scanner.nextLine();
        System.out.println(">>.....Input max price (in 0.0 format):");
        String max = scanner.nextLine();
        if (min.isEmpty() || max.isEmpty()) {
            System.out.println(">>.....Empty fields!");
            return;
        }
        try {
            double minPrice = Double.parseDouble(min);
            double maxPrice = Double.parseDouble(max);
            List<Dish> dishes = dishService.findDishByPriceRange(minPrice, maxPrice);
            if (dishes == null) {
                System.out.println(">>.....Menu does not contains any dishes in such range");
            } else {
                System.out.println();
                dishes.forEach(System.out::println);
            }
        } catch (NumberFormatException ex) {
            System.out.println(">>.....Incorrect data format!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
}
