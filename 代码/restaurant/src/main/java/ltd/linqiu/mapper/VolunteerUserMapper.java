package ltd.linqiu.mapper;

import ltd.linqiu.entity.VolunteerUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VolunteerUserMapper {

    @Insert("insert into volunteer_user(id,password,name) values (#{id},#{password},#{name})")
    Integer insert(VolunteerUser volunteerUser);

    @Select("select * from volunteer_user")
    List<VolunteerUser> selectAll();

    @Select("select * from volunteer_user where id = #{id}")
    VolunteerUser selectById(@Param("id") String id);
}
