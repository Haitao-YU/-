package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.service.IStoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

// 管理员登录管理
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IStoreInfoService storeInfoService;

    @PostMapping("/login")
    public CommonResult<Integer> login(@RequestParam Map<String, String> data, HttpServletRequest request, HttpServletResponse response) {
        String name = data.get("name");
        String password = data.get("password");
        String rightName = storeInfoService.getByName("admin-name").getValue();
        String rightPassword = storeInfoService.getByName("admin-password").getValue();
        if (name.equals(rightName) && password.equals(rightPassword)) {
            HttpSession session = request.getSession();
            System.out.println(session.getId());
            session.setAttribute("logged", true);
            return new CommonResult<>(0, "登录成功！");
        } else {
            return new CommonResult<>(400, "账号或密码错误！");
        }
    }

    @GetMapping("/logged")
    public CommonResult<Integer> login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        System.out.println(session.getAttribute("logged"));
        boolean logged = session.getAttribute("logged") != null && (boolean) session.getAttribute("logged");
        if (logged) {
            return new CommonResult<>(0, "已登录");
        } else {
            return new CommonResult<>(400, "未登录");
        }
    }

}
