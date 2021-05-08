package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Admin;
import ltd.linqiu.mapper.AdminMapper;
import ltd.linqiu.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Integer login(Admin admin) {
        Admin old = adminMapper.selectById(admin.getId());
        if (old != null) {
            if (admin.getPassword() != null && admin.getPassword().equals(old.getPassword())) {
                return 0;
            } else {
                return 401; //密码错误
            }
        } else {
            return 402; //账号不存在
        }
    }

    @Override
    public Integer modifyPassword(Map<String, String> map) {
        String id = map.get("id");
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");
        Admin old = adminMapper.selectById(id);
        if (old != null) {
            if (oldPassword != null && oldPassword.equals(old.getPassword())) {
                if (adminMapper.updatePasswordById(new Admin(id, newPassword)) == 1) {
                    return 0;
                } else {
                    return 403; // 异常
                }
            } else {
                return 401; //密码错误
            }
        } else {
            return 402; //账号不存在
        }
    }

}
