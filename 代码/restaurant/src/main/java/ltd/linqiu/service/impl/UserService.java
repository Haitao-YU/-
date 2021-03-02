package ltd.linqiu.service.impl;

import ltd.linqiu.entity.User;
import ltd.linqiu.mapper.UserMapper;
import ltd.linqiu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> all() {
        return userMapper.selectAll();
    }

    @Override
    public User getByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public User modifyStateByPhone(String phone, Integer state) {
        return userMapper.updateStateByPhone(phone, state);
    }
}
