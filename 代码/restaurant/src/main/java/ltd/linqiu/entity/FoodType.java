package ltd.linqiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class FoodType {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
} 
