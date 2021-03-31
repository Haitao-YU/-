package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class FrontOrder {
    private String phone;
    private List<List<Integer>> content;
    private Integer tableId;
    private Integer numberOfDiners;
    private Double sum;
    private String remark;
}
