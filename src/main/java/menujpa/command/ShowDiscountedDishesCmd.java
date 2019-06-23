package menujpa.command;

import menujpa.entity.Dish;
import menujpa.service.DishService;

import java.util.List;

public class ShowDiscountedDishesCmd extends MenuCmd {
    public ShowDiscountedDishesCmd(DishService dishService) {
        super(dishService);
    }

    @Override
    public void execute() {
        List<Dish> dishes = dishService.findAllDiscountedDishes();
        if (dishes == null){
            System.out.println(">>.....Menu does not contains discounted dishes");
        } else {
            System.out.println();
            dishes.forEach(System.out::println);
        }
    }
}
