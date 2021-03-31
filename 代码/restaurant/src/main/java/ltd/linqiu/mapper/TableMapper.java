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
    Table selectById(@Param("id") String id);

    @Select("select count(*) from `table` where state = #{state}")
    Integer countByState(@Param("state") Integer state);

    @Insert("insert into `table`(id,seats,position,image,remark) values (#{id},#{seats},#{position},#{image},#{remark})")
    Integer insert(Table table);

    @Update("update `table` set seats = #{seats}, position = #{position}, image = #{image}, remark = #{remark} where id = #{id}")
    Integer updateById(Table table);

    @Update("update `table` set state = #{state} where id = #{id}")
    Integer updateStateById(@Param("id") String id, @Param("state") Integer state);

    @Update("update `table` set state = #{newState} where state = #{oldState}")
    Integer updateAllStateByState(@Param("oldState") Integer oldState, @Param("newState") Integer newState);

    @Delete("delete from `table` where id = #{id}")
    Integer deleteById(Table table);

    @Select("select distinct position from `table`")
    List<String> selectDistinctPosition();

    @Select("select distinct seats from `table` order by seats")
    List<Integer> selectDistinctSeats();
}
