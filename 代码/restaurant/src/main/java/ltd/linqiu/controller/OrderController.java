package ltd.linqiu.controller;

import ltd.linqiu.entity.Order;
import ltd.linqiu.response.CommonResult;
import ltd.linqiu.response.TableResult;
import ltd.linqiu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("/all")
    public TableResult<Order> all(HttpServletRequest request) {
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        params.remove("limit");
        params.remove("page");
        if (params.size() == 0) {
            List<Order> ret = orderService.getAll();
            return new TableResult<>(0, "获取全部订单", ret.size(), ret);
        } else {
            Map<String, String> conditions = new HashMap<>();
            for (String key : params.keySet()) {
                conditions.put(key, params.get(key)[0]);
            }
            try {
                List<Order> ret = orderService.getByConditions(conditions);
                return new TableResult<>(0, "条件查询结果", ret.size(), ret);
            } catch (Exception e) {
                return new TableResult<>(400, "查询参数有误", 0, null);
            }
        }
    }

    @PostMapping("/add")
    public CommonResult<Integer> add(Order data) {
        Boolean ret = false;
        try {
            ret = orderService.add(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret) {
            return new CommonResult<>(0, "添加成功！");
        } else {
            return new CommonResult<>(400, "添加失败！");
        }
    }

    @PostMapping("/tally")
    public CommonResult<Integer> tally(@RequestParam("orderId") Integer orderId, @RequestParam("tableId") String tableId) {
        Integer ret = orderService.tally(orderId, tableId);
        if (ret == 0) {
            return new CommonResult<>(0, "结算成功！");
        } else if (ret == 1) {
            return new CommonResult<>(401, "订单结算成功,餐桌状态更新失败");
        } else {
            return new CommonResult<>(402, "结算失败！");
        }
    }

    @GetMapping("/{phone}")
    public TableResult<Order> getByPhone(@PathVariable("phone") String phone) {
        List<Order> ret = orderService.getByPhone(phone);
        return new TableResult<>(0, "根据手机号获取订单", ret.size(), ret);
    }


    @GetMapping("/{phone}/{state}")
    public TableResult<Order> getByPhoneState(@PathVariable("phone") String phone, @PathVariable("state") Integer state) {
        List<Order> ret = orderService.getByPhoneState(phone, state);
        return new TableResult<>(0, "", ret.size(), ret);
    }

    @GetMapping("/id/{id}")
    public CommonResult<Order> getById(@PathVariable("id") Integer id) {
        Order ret = orderService.getById(id);
        if (ret != null) {
            return new CommonResult<>(0, ret, "根据手机号获取订单");
        } else {
            return new CommonResult<>(400, "未找到");
        }
    }



}