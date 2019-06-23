package menujpa.command.update;

import menujpa.command.MenuCmd;
import menujpa.service.DishService;

import java.util.Scanner;

public class UpdateDishCmd extends MenuCmd {
    private Scanner scanner;

    public UpdateDishCmd(DishService dishService, Scanner scanner) {
        super(dishService);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println(">>.....INPUT EXAMPLE: id_value&name_value&price_value&weight_value&discount_value");
        String dataForUpdate = scanner.nextLine();
        if (dataForUpdate.isEmpty()) {
            System.out.println(">>.....Empty field!");
            return;
        }
        DishUpdateDto dto = createDishDTO(dataForUpdate);
        if (dto == null){
            return;
        }
        String resp = dishService.updateDish(dto);
        System.out.println(resp);
    }

    private DishUpdateDto createDishDTO(String dataForUpdate){
        try {
            String[] params = dataForUpdate.split("&");
            if (params.length != 5){
                System.out.println(">>.....Incorrect parameters number!");
                return null;
            }
            Integer id = Integer.parseInt(params[0]);
            String name = params[1];
            Double price = Double.parseDouble(params[2]);
            Double weight = Double.parseDouble(params[3]);
            Integer discount = Integer.parseInt(params[4]);

            DishUpdateDto dto = new DishUpdateDto();
            dto.setId(id);
            dto.setName(name);
            dto.setPrice(price);
            dto.setWeight(weight);
            dto.setDiscount(discount);
            return dto;
        } catch (NumberFormatException ex) {
            System.out.println(">>.....Incorrect input for price or weight or discount!");
            return null;
        }catch (Exception ex) {
            System.out.println(">>.....Incorrect input!");
            return null;
        }
    }
}
