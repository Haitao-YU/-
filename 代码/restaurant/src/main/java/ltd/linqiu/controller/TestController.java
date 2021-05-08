package ltd.linqiu.controller;

import ltd.linqiu.entity.FoodType;
import ltd.linqiu.response.TableResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TestController {

    @GetMapping("/test/1")
    public TableResult<FoodType> test1(String limit,String page) {
        List<FoodType> foodTypeList = new ArrayList<>();
        foodTypeList.add(new FoodType(4, "👍新鲜果蔬"));
        foodTypeList.add(new FoodType(1, "😂新鲜果蔬"));
        foodTypeList.add(new FoodType(3, "❌新鲜果蔬"));
        foodTypeList.add(new FoodType(2, "😀新鲜果蔬"));
        return new TableResult<FoodType>(0, "", foodTypeList.size(), foodTypeList);
    }
}

