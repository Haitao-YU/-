package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Food;
import ltd.linqiu.entity.FoodType;
import ltd.linqiu.mapper.FoodMapper;
import ltd.linqiu.mapper.FoodTypeMapper;
import ltd.linqiu.service.IFoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTypeService implements IFoodTypeService {
    @Autowired
    private FoodTypeMapper foodTypeMapper;
    @Autowired
    private FoodMapper foodMapper;

    @Override
    public List<FoodType> getAll() {
        return foodTypeMapper.selectAll();
    }

    @Override
    public FoodType getById(Integer id) {
        return foodTypeMapper.selectById(id);
    }

    @Override
    public FoodType getByName(String name) {
        return foodTypeMapper.selectByName(name);
    }

    @Override
    public Integer add(FoodType foodType) {
        // 检查名称是否重复
        if (foodTypeMapper.selectByName(foodType.getName()) != null) {
            return 0;
        }
        return foodTypeMapper.insert(foodType);
    }

    @Override
    public Integer delete(FoodType foodType) {
        // 检查删除种类时,是否还存在属于该种类的餐品
        List<Food> foodList = foodMapper.selectByTypeId(foodType.getId());
        if (foodList.size() == 0) {
            return foodTypeMapper.delete(foodType);
        } else {
            return 0;
        }
    }

    @Override
    public Integer modify(FoodType foodType) {
        FoodType old = foodTypeMapper.selectById(foodType.getId());
        if (old == null) {
            return 0;
        } else {
            // 检查名称是否重复
            FoodType temp = foodTypeMapper.selectByName(foodType.getName());
            if (temp != null && !temp.getId().equals(foodType.getId())) {
                return 0;
            }
            return foodTypeMapper.update(foodType);
        }
    }
}
