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
