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
        Food ret = foodService.getById(id);
        if (ret != null) {
            return new CommonResult<>(0, ret, "根据ID获得餐品");
        } else {
            return new CommonResult<>(400, "餐品不存在");
        }
    }

    @PostMapping("/gets")
    public CommonResult<List<Food>> gets(Integer[] ids) {
        List<Food> ret = foodService.gets(ids);
        if (ret != null) {
            return new CommonResult<>(0, ret, "批量根据id获取食物");
        }
        return new CommonResult<>(400, "获取食物失败");
    }

    @GetMapping("/type/{typeId}")
    public CommonResult<List<Food>> showByTypeId(@PathVariable("typeId") Integer typeId) {
        List<Food> ret = foodService.getByTypeId(typeId);
        if (ret != null) {
            return new CommonResult<>(0, ret, "根据分类获得餐品");
        } else {
            return new CommonResult<>(400, "分类不存在");
        }
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
            return new CommonResult<>(400, "修改失败！");
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
