package ltd.linqiu.entity;

import lombok.Data;

@Data
public class Food {
    private Integer id;
    private String name;
    private Double price;
    private Integer typeId;
    private String typeName;
    private String image;
    private Integer monthlySales;
    private String remark;
    private Integer state;


    public Food(Integer id, String name, Double price, Integer typeId, String image, Integer monthlySales, String remark, Integer state) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.typeId = typeId;
        this.image = image;
        this.monthlySales = monthlySales;
        this.remark = remark;
        this.state = state;
    }
}
