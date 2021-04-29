package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltd.linqiu.util.String2Date;

import java.util.Date;
import java.util.Map;

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


    public Order(Map<String, String> map)  throws Exception{
        String id = map.get("id");
        String date = map.get("date");
        String state = map.get("state");
        String phone = map.get("phone");
        String content = map.get("content");
        String tableId = map.get("tableId");
        String nOfDiners = map.get("nOfDiners");
        String sum = map.get("sum");
        String remark = map.get("remark");

        if (id != null) this.id = Integer.parseInt(id);
        if (date != null) this.date = String2Date.parse(date);
        if (state != null) this.state = Integer.parseInt(state);
        if (phone != null) this.phone = phone;
        if (content != null) this.content = content;
        if (tableId != null) this.tableId = tableId;
        if (nOfDiners != null) this.nOfDiners = Integer.parseInt(nOfDiners);
        if (sum != null) this.sum = Double.parseDouble(sum);
        if (remark != null) this.remark = remark;
    }
}
