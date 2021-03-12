package ltd.linqiu.mapper;

import ltd.linqiu.entity.Table;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TableMapper {
    @Select("select * from `table` order by id")
    List<Table> selectAll();

    @Select("select * from `table` where id = #{id}")
    Table selectById(@Param("id") Integer id);

    @Insert("insert into `table`(id,seats,image,remark) values (#{id},#{seats},#{image},#{remark})")
    Integer insert(Table table);

    @Update("update `table` set seats = #{seats}, image = #{image}, remark = #{remark} where id = #{id}")
    Integer updateById(Table table);

    @Update("update `table` set state = #{state} where id = #{id}")
    Integer updateStateById(@Param("id") Integer id, @Param("state") Integer state);

    @Update("update `table` set state = #{newState} where state = #{oldState}")
    Integer updateAllStateByState(@Param("oldState") Integer oldState, @Param("newState") Integer newState);


    @Delete("delete from `table` where id = #{id}")
    Integer deleteById(Table table);
}
