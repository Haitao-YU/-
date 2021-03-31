package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.Order;
import ltd.linqiu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/add")
    public CommonResult<Integer> add(Order data) {
//        System.out.println(data);
        if (orderService.add(data) == 1) {
            return new CommonResult<>(0, "添加成功！");
        } else {
            return new CommonResult<>(400, "添加失败！");
        }
    }
}
