package ltd.linqiu.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private Date date;
    private Integer state;

    private String phone;
    private String content;
    private String tableId;
    private Integer nOfDiners;
    private Double sum;
    private String remark;
}
