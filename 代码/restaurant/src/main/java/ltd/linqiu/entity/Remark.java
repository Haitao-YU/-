package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remark {
    private Integer id;
    private Integer orderId;
    private Date date;
    private Integer score;
    private String text;
    private String images;
    private String phone;
    private String reply;
}


