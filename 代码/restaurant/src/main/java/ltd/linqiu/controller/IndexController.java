package ltd.linqiu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"/", "/index"})
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "page/login";
    }

    @GetMapping("*")
    public String notFound() {
        return "page/404";
    }
}