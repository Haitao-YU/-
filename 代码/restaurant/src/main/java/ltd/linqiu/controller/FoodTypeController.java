package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.FoodType;
import ltd.linqiu.entity.TableResult;
import ltd.linqiu.service.IFoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foodType")
public class FoodTypeController {
    @Autowired
    private IFoodTypeService foodTypeService;

    @GetMapping("/all")
    public TableResult<FoodType> all() {
        List<FoodType> foodTypeList = foodTypeService.getAll();
        return new TableResult<>(0, "", foodTypeList.size(), foodTypeList);
    }

    @GetMapping("/{id}")
    public CommonResult<FoodType> showByTypeId(@PathVariable("id") Integer id) {
        CommonResult<FoodType> ret = new CommonResult<>();
        ret.setCode(200);
        ret.setMsg("根据id获得餐品种类名称");
        ret.setData(foodTypeService.getById(id));
        return ret;
    }

    @PostMapping("/add")
    public CommonResult<Integer> add(FoodType data) {
        if (foodTypeService.add(data) == 1) {
            return new CommonResult<>(0, "添加成功！");
        } else {
            return new CommonResult<>(400, "添加失败！");
        }
    }


    @PostMapping("/edit")
    public CommonResult<Integer> edit(FoodType data) {
        if (foodTypeService.edit(data) == 1) {
            return new CommonResult<>(0, "修改成功！");
        } else {
            return new CommonResult<>(400, "修改失败！");
        }
    }

    @PostMapping("/delete")
    public CommonResult<Integer> delete(FoodType data) {
        Integer id = data.getId();
        if (foodTypeService.delete(id) == 1) {
            return new CommonResult<>(0, "删除成功！");
        } else {
            return new CommonResult<>(400, "删除失败！");
        }
    }
}
