package ltd.linqiu.entity;

import lombok.Data;

@Data
public class Table {
    private Integer id;
    private Integer seats;
    private Integer state;
    private String image;
    private String remark;
    private Integer orderId;
}
