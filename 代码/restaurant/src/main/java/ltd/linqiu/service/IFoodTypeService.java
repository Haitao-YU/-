package ltd.linqiu.service;

import ltd.linqiu.entity.Food;
import ltd.linqiu.entity.FoodType;

import java.util.List;

public interface IFoodTypeService {
    List<FoodType> getAll();

    FoodType getById(Integer id);

    Integer add(FoodType foodType);

    Integer delete(Integer id);

    Integer edit(FoodType foodType);
}
