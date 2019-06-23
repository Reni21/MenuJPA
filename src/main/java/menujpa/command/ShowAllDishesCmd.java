package menujpa.command;

import menujpa.entity.Dish;
import menujpa.service.DishService;

import java.util.List;

public class ShowAllDishesCmd extends MenuCmd {
    public ShowAllDishesCmd(DishService dishService) {
        super(dishService);
    }

    @Override
    public void execute() {
        List<Dish> dishes = dishService.findAllDishes();
        if (dishes == null){
            System.out.println(">>.....Menu does not contains any dishes");
        } else {
            System.out.println();
            dishes.forEach(System.out::println);
        }
    }
}
