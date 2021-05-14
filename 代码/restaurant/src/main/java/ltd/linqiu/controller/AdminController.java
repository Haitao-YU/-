package ltd.linqiu.controller;


import ltd.linqiu.entity.Admin;
import ltd.linqiu.entity.StoreInfo;
import ltd.linqiu.response.CommonResult;
import ltd.linqiu.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public CommonResult<StoreInfo> login(Admin admin) {
        Integer res = adminService.login(admin);
        return new CommonResult<>(res, res == 0 ? "登录成功" : res == 401 ? "密码错误" : res == 402 ? "账号不存在" : "登录异常");
    }


    @PostMapping("/modifyPassword")
    public CommonResult<StoreInfo> modifyPassword(@RequestParam Map<String, String> data) {
        Integer res = adminService.modifyPassword(data);
        return new CommonResult<>(res, res == 0 ? "修改成功" : res == 401 ? "旧密码错误" : res == 402 ? "账号不存在" : "修改异常");
    }

}
