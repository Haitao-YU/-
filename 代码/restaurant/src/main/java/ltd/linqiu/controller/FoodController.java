package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.Food;
import ltd.linqiu.front.OneOfMenu;
import ltd.linqiu.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private IFoodService foodService;

    @GetMapping("/all")
    public CommonResult<List<Food>> all() {
        CommonResult<List<Food>> ret = new CommonResult<>();
        ret.setCode(200);
        ret.setMessage("获得所有餐品");
        ret.setData(foodService.getAll());
        return ret;
    }

    @GetMapping("/{id}")
    public CommonResult<Food> showById(@PathVariable("id") Integer id) {
        CommonResult<Food> ret = new CommonResult<>();
        ret.setCode(200);
        ret.setMessage("根据ID获得餐品");
        ret.setData(foodService.getById(id));
        return ret;
    }

    @GetMapping("/type/{typeId}")
    public CommonResult<List<Food>> showByTypeId(@PathVariable("typeId") Integer typeId) {
        CommonResult<List<Food>> ret = new CommonResult<>();
        ret.setCode(200);
        ret.setMessage("根据分类获得餐品");
        ret.setData(foodService.getByTypeId(typeId));
        return ret;
    }


    @GetMapping("/front/menu")
    public CommonResult<List<OneOfMenu>> menu() {
        return new CommonResult<>(200, foodService.getMenu(), "给前端提供整个菜单");
    }
}
