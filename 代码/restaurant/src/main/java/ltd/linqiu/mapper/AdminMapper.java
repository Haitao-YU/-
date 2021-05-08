package ltd.linqiu.mapper;

import ltd.linqiu.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where id = #{id}")
    Admin selectById(@Param("id") String id);

    @Update("update admin set password = #{password} where id = #{id}")
    Integer updatePasswordById(Admin admin);
}
