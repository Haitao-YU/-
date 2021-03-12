package ltd.linqiu.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Order {
    private Integer id;
    private String phone;
    private Map<Integer, Integer> content;
    private Integer tableId;
    private Integer numberOfDiners;
    private Date date;
    private Double discount;
    private Double sum;
    private Integer state;
    private String remark;

    public static void main(String[] args) {
        Map<Integer, Integer> content = new HashMap<>();
        content.put(1, 2);
        content.put(13, 2);
        content.put(16, 8);
        System.out.println(JSON.toJSONString(content));
        Map<Integer,Integer> newContent = JSON.parseObject("{16:8,1:2,13:2}", Map.class);
        for (Integer o : newContent.keySet()) {
            System.out.println(o);
            System.out.println(newContent.get(o));
        }
    }
}
