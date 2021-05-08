package ltd.linqiu.service;

import ltd.linqiu.entity.Admin;

import java.util.Map;

public interface IAdminService {
    Integer login(Admin admin);
    Integer modifyPassword(Map<String,String> map);

}
