package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Food;
import ltd.linqiu.entity.FoodType;
import ltd.linqiu.front.OneOfMenu;
import ltd.linqiu.mapper.FoodMapper;
import ltd.linqiu.mapper.FoodTypeMapper;
import ltd.linqiu.mapper.StoreInfoMapper;
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
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    @Override
    public List<Food> getAll() {
        List<Food> ret = new ArrayList<>();
        List<Food> foodList = foodMapper.selectAll();
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

    @Override
    public Integer modify(Food food) {
        Food old = foodMapper.selectById(food.getId());
        if (old == null) {
            return 0;
        } else {
            food = completeFoodImage(food);
            return foodMapper.update(food);
        }
    }

    @Override
    public Integer switchState(Integer id, Integer state) {
        return foodMapper.updateState(id, state);
    }

    @Override
    public Integer add(Food food) {
        food = completeFoodImage(food);
        return foodMapper.insert(food);
    }

    @Override
    public Integer delete(Food food) {
        return foodMapper.delete(food);
    }

    /** 前端接口：提供菜单
     */
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


    /** 工具函数：补全餐品默认图片，写入数据库时执行该检查
     */
    private Food completeFoodImage(Food food) {
        if (food == null) {
            return null;
        }
        if (!(food.getImage() != null && food.getImage().trim().length() != 0)) {
            food.setImage(storeInfoMapper.selectByName("餐品默认图片").get(0).getValue());
        }
        return food;
    }

    /** 工具函数：补全餐品种类名称，传给前端页面时执行该检查
     */
    private Food completeFoodTypeName(Food food) {
        if (food == null) {
            return null;
        }
        FoodType foodType = foodTypeMapper.selectById(food.getTypeId());
        food.setTypeName(foodType.getName());
        return food;
    }

}
