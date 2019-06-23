package menujpa.dao;

import menujpa.entity.Dish;

import java.util.List;

public interface DishDao {
    boolean addDish(Dish dish);
    boolean updateDish(Dish dish);
    Dish findDish(Integer id);
    List<Dish> findAllDishes();
    List<Dish> findByPriceRange(double minPrice, double maxPrice);
    List<Dish> findAllDiscounted();
    boolean deleteDish(Dish dish);
    boolean isDishExist(String name);
    List<Integer> findAllIds();

}
