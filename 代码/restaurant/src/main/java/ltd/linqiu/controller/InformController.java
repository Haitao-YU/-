package ltd.linqiu.controller;

import ltd.linqiu.entity.Inform;
import ltd.linqiu.entity.Order;
import ltd.linqiu.response.CommonResult;
import ltd.linqiu.response.TableResult;
import ltd.linqiu.service.IInformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inform")
public class InformController {
    @Autowired
    private IInformService informService;

    @GetMapping("all")
    public TableResult<Inform> all() {
        List<Inform> ret = informService.getAll();
        return new TableResult<>(0, "获取全部订单通知", ret.size(), ret);
    }

    @GetMapping("check/{tableId}")
    public CommonResult<Order> check(@PathVariable("tableId") String tableId) {
        if (informService.check(tableId) == 1) {
            return new CommonResult<>(0, "已确定通知");
        } else {
            return new CommonResult<>(400, "操作失败");
        }
    }
}