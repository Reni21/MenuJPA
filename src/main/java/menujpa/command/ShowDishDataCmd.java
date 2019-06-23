package menujpa.command;

import menujpa.entity.Dish;
import menujpa.service.DishService;

import java.util.Scanner;

public class ShowDishDataCmd extends MenuCmd {
    private Scanner scanner;

    public ShowDishDataCmd(DishService dishService, Scanner scanner) {
        super(dishService);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(">>.....Input dish id:");
        String idStr = scanner.nextLine();
        if (idStr.isEmpty()){
            System.out.println(">>.....Empty field!");
            return;
        }
        try{
            int id = Integer.parseInt(idStr);
            Dish dish = dishService.findDishById(id);
            if (dish == null){
                System.out.println(">> .....Such dish does not exist");
            } else {
                System.out.println(dish);
            }
        } catch (NumberFormatException ex){
            System.err.println(">>.....Incorrect data format!");
        } catch (Exception ex){
            ex.printStackTrace();
            System.exit(1);
        }

    }
}
