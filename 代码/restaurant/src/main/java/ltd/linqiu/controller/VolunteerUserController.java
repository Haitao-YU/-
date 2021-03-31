package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.VolunteerUser;
import ltd.linqiu.service.IVolunteerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volunteer/user")
public class VolunteerUserController {
    @Autowired
    private IVolunteerUserService volunteerUserService;

    @PostMapping("/add")
    public CommonResult<Integer> add(VolunteerUser data) {
        if (volunteerUserService.add(data) == 1) {
            return new CommonResult<>(0, "添加成功！");
        } else {
            return new CommonResult<>(400, "账号已被注册");
        }
    }

    @PostMapping("/login")
    public CommonResult<VolunteerUser> login(VolunteerUser data) {
        Integer ret = volunteerUserService.login(data);
        if (ret == 0) {
            return new CommonResult<>(0, data, "登录成功！");
        } else if (ret == 1) {
            return new CommonResult<>(401, "用户不存在！");
        } else if (ret == 2) {
            return new CommonResult<>(402, "密码错误！");
        }
        return null;
    }

    @GetMapping("/all")
    public CommonResult<Integer> all() {

//        if (volunteerUserService.add(data) == 1) {
//            return new CommonResult<>(0, "登录成功！");
//        } else {
//            return new CommonResult<>(400, "登录失败！");
//        }
        return new CommonResult<>(0, "用户表");
    }

}
