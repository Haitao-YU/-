package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.Food;
import ltd.linqiu.entity.TableResult;
import ltd.linqiu.front.OneOfMenu;
import ltd.linqiu.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private IFoodService foodService;

    @GetMapping("/all")
    public TableResult<Food> all() {
        List<Food> foodList = foodService.getAll();
        return new TableResult<>(0, "", foodList.size(), foodList);
    }

    @GetMapping("/{id}")
    public CommonResult<Food> showById(@PathVariable("id") Integer id) {
        CommonResult<Food> ret = new CommonResult<>();
        ret.setCode(200);
        ret.setMsg("根据ID获得餐品");
        ret.setData(foodService.getById(id));
        return ret;
    }

    @GetMapping("/type/{typeId}")
    public CommonResult<List<Food>> showByTypeId(@PathVariable("typeId") Integer typeId) {
        CommonResult<List<Food>> ret = new CommonResult<>();
        ret.setCode(200);
        ret.setMsg("根据分类获得餐品");
        ret.setData(foodService.getByTypeId(typeId));
        return ret;
    }

    @PostMapping("/edit")
    public CommonResult<Integer> edit(Food data) {
        if (foodService.modify(data) == 1) {
            return new CommonResult<>(0, "修改成功！");
        } else {
            return new CommonResult<>(400, "修改失败！");
        }
    }


    @PostMapping("/switchState")
    public CommonResult<Integer> switchState(@RequestParam Map<String, String> data) {
        Integer id = Integer.parseInt(data.get("id"));
        Integer state = Integer.parseInt(data.get("state"));
        if (foodService.switchState(id, state) == 1) {
            return new CommonResult<>(0, "修改成功！");
        } else {
            return new CommonResult<>(400, "修改成功！");
        }

    }


    @PostMapping("/add")
    public CommonResult<Integer> add(Food data) {
        if (foodService.add(data) == 1) {
            return new CommonResult<>(0, "添加成功！");
        } else {
            return new CommonResult<>(400, "添加失败！");
        }
    }

    @PostMapping("/delete")
    public CommonResult<Integer> delete(Food data) {
        if (foodService.delete(data) == 1) {
            return new CommonResult<>(0, "删除成功！");
        } else {
            return new CommonResult<>(400, "删除失败！");
        }
    }


    // 小程序接口
    @GetMapping("/front/menu")
    public CommonResult<List<OneOfMenu>> menu() {
        return new CommonResult<>(0, foodService.getMenu(), "给前端提供整个菜单");
    }
}
