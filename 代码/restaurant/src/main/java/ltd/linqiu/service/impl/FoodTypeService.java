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
    public List<FoodType> getById(Integer id) {
        return foodTypeMapper.selectById(id);
    }
}
