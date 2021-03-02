package ltd.linqiu.mapper;

import ltd.linqiu.entity.Food;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FoodMapper {
    @Select("select * from food")
    List<Food> selectAll();

    @Select("select * from food where id = #{id}")
    Food selectById(@Param("id") Integer id);

    @Select("select * from food where type_id = #{typeId}")
    List<Food> selectByTypeId(@Param("typeId") Integer typeId);

    @Insert("insert into food(name,price,type_id,image,remark) values (#{name},#{price},#{typeId},#{image},#{remark})")
    Integer insert(Food food);

    @Update("update food set name = #{name}, price = #{price}, type_id = #{typeId}, image = #{image}, remark = #{remark}, state = #{state} where id = #{id}")
    Integer update(Food food);

    @Update("update food set state = #{state} where id = #{id}")
    Integer updateState(@Param("id") Integer id, @Param("state") Integer state);

    @Delete("delete from food where id = #{id}")
    Integer delete(Food food);
}
