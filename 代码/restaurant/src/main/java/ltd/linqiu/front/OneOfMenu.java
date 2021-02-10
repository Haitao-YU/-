package ltd.linqiu.front;

import lombok.Data;
import ltd.linqiu.entity.Food;
import ltd.linqiu.entity.FoodType;

import java.util.List;

@Data
public class OneOfMenu {
    private Integer typeId;
    private String name;
    private List<Food> foodList;

    public OneOfMenu(FoodType foodType, List<Food> foodList) {
        this.typeId = foodType.getId();
        this.name = foodType.getName();
        this.foodList = foodList;
    }
}
