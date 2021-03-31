package ltd.linqiu.entity;

import lombok.Data;

@Data
public class Table {
    private String id;
    private Integer seats;
    private String position;
    private Integer state;
    private String image;
    private String remark;
    private Integer orderId;
}
