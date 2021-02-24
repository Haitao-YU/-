package ltd.linqiu.service;

import ltd.linqiu.entity.FoodType;

import java.util.List;

public interface IFoodTypeService {
    List<FoodType> getAll();

    FoodType getById(Integer id);

    FoodType getByName(String name);

    Integer add(FoodType foodType);

    Integer modify(FoodType foodType);

    Integer delete(FoodType foodType);
}
