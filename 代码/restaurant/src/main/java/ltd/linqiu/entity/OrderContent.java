package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderContent {
    private Integer id;
    private String name;
    private Double price;
    private String typeName;
    private String image;
    private String remark;

    public OrderContent(Food food) {
        name = food.getName();
        price = food.getPrice();
        typeName = food.getTypeName();
        image = food.getImage();
        remark = food.getRemark();
    }
}
