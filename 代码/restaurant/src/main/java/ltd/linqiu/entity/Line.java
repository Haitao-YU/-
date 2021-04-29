package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Line {
 private String phone;
 private Integer serialNumber;
 private Integer mealsNumber;
 private Date date;
}
