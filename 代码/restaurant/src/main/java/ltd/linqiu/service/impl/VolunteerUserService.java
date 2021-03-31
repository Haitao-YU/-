package ltd.linqiu.service.impl;

import ltd.linqiu.entity.VolunteerUser;
import ltd.linqiu.mapper.VolunteerUserMapper;
import ltd.linqiu.service.IVolunteerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerUserService implements IVolunteerUserService {
    @Autowired
    private VolunteerUserMapper volunteerUserMapper;

    @Override
    public Integer add(VolunteerUser volunteerUser) {
        return volunteerUserMapper.insert(volunteerUser);
    }

    @Override
    public Integer login(VolunteerUser volunteerUser) {
        VolunteerUser user = volunteerUserMapper.selectById(volunteerUser.getId());
        if (user == null) {
            return 1; //账号不存在
        }
        if (!user.getPassword().equals(volunteerUser.getPassword())) {
            return 2; //密码错误
        }
        user.setPassword(null);
        volunteerUser.copy(user);
        return 0;// 成功登录
    }
}
