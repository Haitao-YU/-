package ltd.linqiu.service;

import ltd.linqiu.entity.FoodType;

import java.util.List;

public interface IFoodTypeService {
    List<FoodType> getAll();

    List<FoodType> getById(Integer id);
}
