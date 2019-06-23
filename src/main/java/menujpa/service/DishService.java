package menujpa.service;

import menujpa.command.update.DishUpdateDto;
import menujpa.dao.DishDao;
import menujpa.entity.Dish;

import java.util.*;

public class DishService {
    private final DishDao dishDao;

    public DishService(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public String addDish(Dish src) {
        if (dishDao.isDishExist(src.getName())) {
            return ">> .....Dish with such name is already exist!";
        } else {
            if (dishDao.addDish(src)) {
                return ">> .....Dish \"" + src.getName() + "\" was successfully add";
            } else {
                return ">> .....Dish add process failed!";
            }
        }
    }

    public String updateDish(DishUpdateDto dishDto) {
        if (dishDao.isDishExist(dishDto.getName())) {
            Dish dish = dishDao.findDish(dishDto.getId());
            dish.setName(dishDto.getName());
            dish.setPrice(dishDto.getPrice());
            dish.setWeight(dishDto.getWeight());
            dish.setDiscount(dishDto.getDiscount());
            if (dishDao.updateDish(dish)) {
                return ">> .....Dish \"" + dish.getName() + "\" was successfully update";
            } else {
                return ">> .....Update failed!";
            }
        } else {
            return ">> .....Dish \"" + dishDto.getName() + "\" does not exist";
        }

    }

    public Dish findDishById(int id) {
        return dishDao.findDish(id);
    }

    public List<Dish> findAllDishes() {
        return dishDao.findAllDishes();
    }

    public List<Dish> findAllDiscountedDishes() {
        return dishDao.findAllDiscounted();
    }

    public List<Dish> findDishByPriceRange(double minPrice, double maxPrice) {
        return dishDao.findByPriceRange(minPrice, maxPrice);
    }

    public List<Dish> getComboOfDishes(double requiredWeight) {
        List<Integer> ids = dishDao.findAllIds();
        List<Dish> dishes = new ArrayList<>();
        if (ids == null){
            return null;
        }
        Collections.shuffle(ids);
        double totalWeight = 0;
        int loopCircle = 0;

        for (Integer id : ids) {
            loopCircle++;
            Dish dish = dishDao.findDish(id);
            totalWeight += dish.getWeight();
            if (totalWeight <= requiredWeight){
                dishes.add(dish);
            } else if (loopCircle < ids.size()){
                totalWeight -= dish.getWeight();
            } else {
                break;
            }
        }
        return dishes;
    }

    public String deleteDishById(int id) {
        Dish dish = findDishById(id);
        if (dish != null) {
            boolean result = dishDao.deleteDish(dish);
            if (result) {
                return ">> .....Dish \"" + dish.getName() + "\" was successfully deleted";
            } else {
                return ">> .....Delete dish \"" + dish.getName() + "\" failed!";
            }
        } else {
            return ">> .....Incorrect id!";
        }
    }
}
