package ltd.linqiu.mapper;

import ltd.linqiu.entity.Inform;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InformMapper {
    @Select("select * from `inform` order by date")
    List<Inform> selectAll();

    @Insert("insert into `inform`(tableId,date) values (#{tableId},now())")
    Integer insert(@Param("tableId") String tableId);

    @Delete("delete from `inform` where tableId = #{tableId}")
    Integer deleteByTableId(@Param("tableId") String tableId);
}
