package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreInfo {
    private Integer id;
    private String name;
    private String value;

    public StoreInfo(String name, String value) {
        this(null, name, value);
    }
}
