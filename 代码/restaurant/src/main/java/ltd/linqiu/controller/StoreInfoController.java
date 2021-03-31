package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.StoreInfo;
import ltd.linqiu.service.IStoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storeInfo")
public class StoreInfoController {
    @Autowired
    private IStoreInfoService storeInfoService;

    @GetMapping("/all")
    public CommonResult<List<StoreInfo>> all() {
        return new CommonResult<>(0, storeInfoService.getAll(), "获取全部店铺信息");
    }

    @GetMapping("/{name}")
    public CommonResult<StoreInfo> getByName(@PathVariable("name") String name) {
        return new CommonResult<>(0, storeInfoService.getByName(name), "获取指定店铺信息");
    }

    @GetMapping("/onOff")
    public CommonResult<StoreInfo> onOff() {
        StoreInfo now = storeInfoService.onOff();
        if (now == null) {
            return new CommonResult<>(400, "开关失败");
        } else {
            return new CommonResult<>(0, now, "开关成功");
        }
    }

    @PostMapping("/adminLogin")
    public CommonResult<StoreInfo> adminLogin(@RequestParam Map<String, String> data, HttpServletResponse response) {
        String name = data.get("name");
        String password = data.get("password");
        String rightName = storeInfoService.getByName("admin-name").getValue();
        String rightPassword = storeInfoService.getByName("admin-password").getValue();
        if (name.equals(rightName) && password.equals(rightPassword)) {
            Cookie cookie = new Cookie(name, password);
            cookie.setMaxAge(86400); // 存活1天
            cookie.setPath("/");
            response.addCookie(cookie);
            return new CommonResult<>(0, "登录成功！");

        } else {
            return new CommonResult<>(400, "账号或密码错误！");
        }
    }

    @GetMapping("/ifLogged")
    public CommonResult<StoreInfo> ifLogged(HttpServletRequest request) {
        String rightName = storeInfoService.getByName("admin-name").getValue();
        String rightPassword = storeInfoService.getByName("admin-password").getValue();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(rightName)) {
                    if (cookie.getValue().equals(rightPassword)) {
                        return new CommonResult<>(0, "已登录");
                    }
                }
            }
        }
        return new CommonResult<>(400, "未登录");
    }

    @PostMapping("/edit")
    public CommonResult<List<StoreInfo>> modify(@RequestParam Map<String, String> data) {

        Integer flag = 1;

        for (String key : data.keySet()) {
            flag *= storeInfoService.modify(new StoreInfo(key, data.get(key)));
        }

        if (flag == 1) {
            return new CommonResult<>(0, "修改成功！");
        } else {
            return new CommonResult<>(400, "出现异常！");
        }
    }

}
