package ltd.linqiu.front;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 排队信息
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineInfo {
    private Integer nOfFreeTables; // 空闲餐桌数
    private Integer nOfLine; // 排队人数
    private Integer myPosition; // 我在队伍中的位置
}
