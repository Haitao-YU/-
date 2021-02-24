package ltd.linqiu.mapper;

import ltd.linqiu.entity.FoodType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FoodTypeMapper {
    @Select("select * from food_type")
    List<FoodType> selectAll();

    @Select("select * from food_type where id = #{id}")
    FoodType selectById(@Param("id") Integer id);

    @Select("select * from food_type where name = #{name}")
    FoodType selectByName(@Param("name") String name);

    @Insert("insert into food_type(name) values (#{name})")
    Integer insert(FoodType foodType);

    @Update("update food_type set name = #{name} where id = #{id}")
    Integer update(FoodType foodType);

    @Delete("delete from food_type where id = #{id}")
    Integer delete(FoodType foodType);
}
