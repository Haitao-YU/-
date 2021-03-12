package ltd.linqiu.controller;

import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.Table;
import ltd.linqiu.entity.TableResult;
import ltd.linqiu.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class TableController {
    @Autowired
    private ITableService tableService;

    @GetMapping("/all")
    public TableResult<Table> all() {
        List<Table> tableList = tableService.getAll();
        return new TableResult<>(0, "", tableList.size(), tableList);
    }

    @PostMapping("/add")
    public CommonResult<Integer> add(Table data) {
        if (tableService.add(data) == 1) {
            return new CommonResult<>(0, "添加成功！");
        } else {
            return new CommonResult<>(400, "添加失败！");
        }
    }


    @PostMapping("/edit")
    public CommonResult<Integer> edit(Table data) {
        if (tableService.modifyById(data) == 1) {
            return new CommonResult<>(0, "修改成功！");
        } else {
            return new CommonResult<>(400, "修改失败！");
        }
    }

    @PostMapping("/switchState")
    public CommonResult<Integer> switchState(@RequestParam Map<String, String> data) {
        if (tableService.switchState(Integer.parseInt(data.get("id")), Integer.parseInt(data.get("state"))) == 1) {
            return new CommonResult<>(0, "修改成功！");
        } else {
            return new CommonResult<>(400, "修改失败！");
        }
    }

    @GetMapping("/enableAll")
    public CommonResult<Integer> enableAll() {
        if (tableService.enableAll()) {
            return new CommonResult<>(0, "全部启用成功！");
        } else {
            return new CommonResult<>(400, "全部启用失败！");
        }
    }


    @PostMapping("/delete")
    public CommonResult<Integer> delete(Table data) {
        if (tableService.deleteById(data) == 1) {
            return new CommonResult<>(0, "删除成功！");
        } else {
            return new CommonResult<>(400, "删除失败！");
        }
    }
}

