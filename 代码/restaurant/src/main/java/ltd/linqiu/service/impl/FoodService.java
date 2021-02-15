package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Food;
import ltd.linqiu.entity.FoodType;
import ltd.linqiu.front.OneOfMenu;
import ltd.linqiu.mapper.FoodMapper;
import ltd.linqiu.mapper.FoodTypeMapper;
import ltd.linqiu.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService implements IFoodService {
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private FoodTypeMapper foodTypeMapper;

    @Override
    public List<Food> getAll() {
        List<Food> ret = new ArrayList<>();
        List<Food> foodList  = foodMapper.selectAll();
        for (Food food : foodList) {
            ret.add(completeFoodTypeName(food));
        }
        return ret;
    }

    @Override
    public Food getById(Integer id) {
        return foodMapper.selectById(id);
    }

    @Override
    public List<Food> getByTypeId(Integer typeId) {
        return foodMapper.selectByTypeId(typeId);
    }

    // 补全食物类型名称
    @Override
    public Food completeFoodTypeName(Food food) {
        FoodType foodType =  foodTypeMapper.selectById(food.getTypeId());
        food.setTypeName(foodType.getName());
        return food;
    }

    // 前端接口：提供菜单
    @Override
    public List<OneOfMenu> getMenu() {
        List<OneOfMenu> menu = new ArrayList<>();
        List<FoodType> foodTypeList = foodTypeMapper.selectAll();
        for (FoodType foodType : foodTypeList) {
            List<Food> foodListByFoodType = foodMapper.selectByTypeId(foodType.getId());
            OneOfMenu one = new OneOfMenu(foodType, foodListByFoodType);
            menu.add(one);
        }
        return menu;
    }
}
