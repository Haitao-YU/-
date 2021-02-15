package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class TableResult<T>{
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;
}