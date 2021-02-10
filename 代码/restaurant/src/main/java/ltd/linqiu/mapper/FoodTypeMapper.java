package ltd.linqiu.mapper;

import ltd.linqiu.entity.FoodType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FoodTypeMapper {
    @Select("select * from food_type")
    List<FoodType> selectAll();

    @Select("select * from food_type where id = #{id}")
    List<FoodType> selectById(@Param("id") Integer id);
}
