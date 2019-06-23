package menujpa.command.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishUpdateDto {
    private Integer id;
    private String name;
    private Double price;
    private Double weight;
    private Integer discount;
}


