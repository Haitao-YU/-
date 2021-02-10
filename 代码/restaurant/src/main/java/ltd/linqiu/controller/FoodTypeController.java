package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.FoodType;
import ltd.linqiu.service.IFoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foodType")
public class FoodTypeController {
    @Autowired
    private IFoodTypeService foodTypeService;

    @GetMapping("/all")
    public CommonResult<List<FoodType>> all() {
        CommonResult<List<FoodType>> ret = new CommonResult<>();
        ret.setCode(200);
        ret.setMessage("获得所有餐品种类");
        ret.setData(foodTypeService.getAll());
        return ret;
    }

    @GetMapping("/{id}")
    public CommonResult<List<FoodType>> showByTypeId(@PathVariable("id") Integer id) {
        CommonResult<List<FoodType>> ret = new CommonResult<>();
        ret.setCode(200);
        ret.setMessage("根据id获得餐品种类名称");
        ret.setData(foodTypeService.getById(id));
        return ret;
    }
}
