package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.Order;
import ltd.linqiu.entity.TableResult;
import ltd.linqiu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/add")
    public CommonResult<Integer> add(Order data) {
        if (orderService.add(data) == 1) {
            return new CommonResult<>(0, "添加成功！");
        } else {
            return new CommonResult<>(400, "添加失败！");
        }
    }

    @GetMapping("/{phone}")
    public TableResult<Order> getByPhone(@PathVariable("phone") String phone) {
        List<Order> ret = orderService.getByPhone(phone);
        return new TableResult<>(0, "", ret.size(), ret);
    }

    @GetMapping("/{phone}/{state}")
    public TableResult<Order> getByPhoneState(@PathVariable("phone") String phone, @PathVariable("state") Integer state) {
        List<Order> ret = orderService.getByPhoneState(phone, state);
        return new TableResult<>(0, "", ret.size(), ret);
    }
}