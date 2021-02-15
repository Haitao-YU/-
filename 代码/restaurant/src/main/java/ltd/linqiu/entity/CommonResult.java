package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class CommonResult<T> {
    @NonNull
    private Integer code;
    private T data;
    private String msg;

    public CommonResult() {
        this(0, null, null);
    }

    public CommonResult(Integer code) {
        this(code, null, null);
    }

    public CommonResult(Integer code, T data) {
        this(code, data, null);
    }

    public CommonResult(Integer code, String msg) {
        this(code, null, msg);
    }

}
