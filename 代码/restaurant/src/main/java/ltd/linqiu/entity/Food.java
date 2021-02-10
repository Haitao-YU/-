package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Food {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Double price;
    @NonNull
    private Integer typeId;
    private String image;

    public Food(Integer id, String name, Double price, Integer typeId) {
        this(id, name, price, typeId,
                // 餐品的默认图片地址
                "https://vkceyugu.cdn.bspapp.com/VKCEYUGU-b1ebbd3c-ca49-405b-957b-effe60782276/f3808bf7-e294-4136-a8c1-ca90baf919e3.png");
    }
}
