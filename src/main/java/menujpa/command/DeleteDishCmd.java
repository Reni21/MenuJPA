package menujpa.command;

import menujpa.service.DishService;

import java.util.Scanner;

public class DeleteDishCmd extends MenuCmd {
    private Scanner scanner;

    public DeleteDishCmd(DishService dishService, Scanner scanner) {
        super(dishService);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(">>.....Input dish id for delete:");
        String idStr = scanner.nextLine();
        if (idStr.isEmpty()) {
            System.out.println(">>.....Empty field!");
            return;
        }
        try {
            Integer id = Integer.parseInt(idStr);
            String resp = dishService.deleteDishById(id);
            System.out.println(resp);
        } catch (NumberFormatException ex) {
            System.out.println(">>.....Incorrect data format!");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
}
