package ltd.linqiu.entity;

import lombok.Data;

import java.sql.Date;
import java.util.Map;

@Data
public class Order {
    private Integer id;
    private String userPhone;
    private Date date;
    private String contentJson;
    private Map<Integer, Integer> content;

    public Order(Integer id, String userPhone, Date date, String contentJson) {
        this.id = id;
        this.userPhone = userPhone;
        this.date = date;
        this.contentJson = contentJson;
    }

    public Order(Integer id, String userPhone, Date date, Map<Integer, Integer> content) {
        this.id = id;
        this.userPhone = userPhone;
        this.date = date;
        this.content = content;
    }

    // contentObject 和 content 的智能互补填充
    public void smartFill() {
        if ((contentJson == null && content == null)) {
            throw new RuntimeException("contentJson和content不可同时为空");
        }
    }


}
