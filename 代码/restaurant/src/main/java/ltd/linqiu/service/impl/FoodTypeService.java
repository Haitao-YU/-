package ltd.linqiu.service.impl;

import ltd.linqiu.entity.FoodType;
import ltd.linqiu.mapper.FoodTypeMapper;
import ltd.linqiu.service.IFoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodTypeService implements IFoodTypeService {
    @Autowired
    private FoodTypeMapper foodTypeMapper;

    @Override
    public List<FoodType> getAll() {
        return foodTypeMapper.selectAll();
    }

    @Override
    public FoodType getById(Integer id) {
        return foodTypeMapper.selectById(id);
    }

    @Override
    public Integer add(FoodType foodType) {
        return foodTypeMapper.insert(foodType);
    }

    @Override
    public Integer delete(Integer id) {
        return foodTypeMapper.delete(id);
    }

    @Override
    public Integer edit(FoodType foodType) {
        FoodType old = foodTypeMapper.selectById(foodType.getId());
        if (old == null) {
            return 0;
        } else {
            return foodTypeMapper.update(foodType);
        }
    }
}
