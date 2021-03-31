package ltd.linqiu.mapper;

import ltd.linqiu.entity.Line;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LineMapper {
    @Select("select count(*) from line where mealsNumber = #{mealsNumber}")
    Integer sizeByMealsNumber(@Param("mealsNumber") Integer mealsNumber);

    @Select("select * from line order by serialNumber")
    List<Line> selectAll();

    @Select("select * from line where mealsNumber = #{mealsNumber} order by serialNumber")
    List<Line> selectByMealsNumber(@Param("mealsNumber") Integer mealsNumber);

    @Select("select * from line where serialNumber > #{serialNumber} and mealsNumber = #{mealsNumber} order by serialNumber")
    List<Line> selectBiggerThanSerialNumber(Line line);

    @Select("select * from line where phone = #{phone}")
    Line selectByPhone(@Param("phone") String phone);

    @Insert("insert into line(phone,serialNumber,mealsNumber,date) values (#{phone},#{serialNumber},#{mealsNumber},now())")
    Integer insert(Line line);

    @Update("update line set serialNumber = #{serialNumber} where phone = #{phone}")
    Integer updateSerialNumberByPhone(Line line);

    @Delete("delete from line where phone = #{phone}")
    Integer deleteByPhone(Line line);
}
