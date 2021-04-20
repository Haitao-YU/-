package ltd.linqiu.front;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderFood {
    private Integer id;
    private String name;
    private Double price;
    private String image;
    private String remark;
    private Integer count;
}