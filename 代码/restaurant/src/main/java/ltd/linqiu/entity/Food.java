package ltd.linqiu.entity;

import lombok.Data;

@Data
public class Food {
    private Integer id;
    private String name;
    private Integer state;
    private Double price;
    private Integer typeId;
    private String typeName;
    private String image;
    private Integer monthlySales;
    private String remark;


    public Food(Integer id, String name, Integer state, Double price, Integer typeId, String image, Integer monthlySales, String remark) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.price = price;
        this.typeId = typeId;
        this.image = image;
        this.monthlySales = monthlySales;
        this.remark = remark;
    }
}
