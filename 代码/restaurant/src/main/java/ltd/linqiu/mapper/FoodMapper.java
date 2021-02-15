package ltd.linqiu.mapper;

import ltd.linqiu.entity.Food;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FoodMapper {
    @Select("select * from food")
    List<Food> selectAll();

    @Select("select * from food where id = #{id}")
    Food selectById(@Param("id") Integer id);

    @Select("select * from food where type_id = #{typeId}")
    List<Food> selectByTypeId(@Param("typeId") Integer typeId);


}
