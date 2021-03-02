package ltd.linqiu.mapper;

import ltd.linqiu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface UserMapper {

    @Insert("insert into user values (#{id},#{phone},#{state})")
    Integer insert(User user);

    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where phone = #{phone}")
    User selectByPhone(@Param("phone") String phone);

    @Update("update user set state = #{state} where phone = #{phone}")
    User updateStateByPhone(@Param("phone") String phone, @Param("state") Integer state);
}
