package ltd.linqiu.service;

import ltd.linqiu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserService {
    Integer add(User user);

    List<User> all();

    User getByPhone(@Param("phone") String phone);

    User modifyStateByPhone(@Param("phone") String phone, @Param("state") Integer state);
}
