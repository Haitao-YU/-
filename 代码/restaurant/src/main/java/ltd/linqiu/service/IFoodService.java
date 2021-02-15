package ltd.linqiu.service;

import ltd.linqiu.entity.Food;
import ltd.linqiu.front.OneOfMenu;

import java.util.List;

public interface IFoodService {
    List<Food> getAll();

    Food getById(Integer id);

    List<Food> getByTypeId(Integer typeId);

    List<OneOfMenu> getMenu();

    Food completeFoodTypeName(Food food);
}
