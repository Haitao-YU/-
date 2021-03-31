package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.Line;
import ltd.linqiu.entity.TableResult;
import ltd.linqiu.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/line")
public class LineController {
    @Autowired
    private ILineService lineService;

    @GetMapping("/getMealsNumberOption")
    public CommonResult<List<Integer>> getMealsNumberOption() {
        List<Integer> ret = lineService.getMealsNumberOption();
        if (ret == null) {
            return new CommonResult<>(401, "商家未提供可选座位类型");
        } else {
            return new CommonResult<>(0, ret, "获取可选的座位类型");
        }
    }

    @GetMapping("/all")
    public TableResult<Line> all() {
        List<Line> lineList = lineService.getAll();
        return new TableResult<>(0, "获取当前所有排队列表", lineList.size(), lineList);
    }

    @GetMapping("/all/{mealsNumb}")
    public TableResult<Line> all(@PathVariable("mealsNumb") Integer mealsNumb) {
        List<Line> lineList = lineService.getListByMealsNumber(mealsNumb);
        return new TableResult<>(0, "获取特定排队列表", lineList.size(), lineList);
    }


    @GetMapping("/{phone}")
    public CommonResult<Line> getSerialNumberByPhone(@PathVariable String phone) {
        Line line = lineService.getByPhone(phone);
        if (line != null) {
            return new CommonResult<>(0, line, "根据手机号获取排队信息");
        } else {
            return new CommonResult<>(400, "该用户未在排队队列中");
        }

    }

    @PostMapping("/enqueue")
    public CommonResult<Line> enqueue(Line data) {
        Line in = lineService.enqueue(data);
        if (in == null) {
            return new CommonResult<>(400, "排队失败，该用户已在队列中。");
        } else {
            return new CommonResult<>(0, in, "成功加入排队！");
        }
    }

    @PostMapping("/enqueue/cancel")
    public CommonResult<Line> cancelEnqueue(Line data) {
        Line out = lineService.cancelEnqueue(data);
        if (out == null) {
            return new CommonResult<>(400, "取消失败，该用户没有在队列中。");
        } else {
            return new CommonResult<>(0, out, "成功取消排队！");
        }
    }

    @GetMapping("/dequeue/{mealsNumber}")
    public CommonResult<Line> dequeue(@PathVariable Integer mealsNumber) {
        Line out = lineService.dequeue(mealsNumber);
        if (out != null) {
            return new CommonResult<>(0, out, "该用户开始就餐。");
        } else {
            return new CommonResult<>(400, "该排队队列为空。");
        }
    }
}
