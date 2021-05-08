package ltd.linqiu.controller;

import ltd.linqiu.response.CommonResult;
import ltd.linqiu.entity.StoreInfo;
import ltd.linqiu.service.IStoreInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public CommonResult<StoreInfo> adminLogin(@RequestParam Map<String, String> data) {
        String name = data.get("name");
        String password = data.get("password");
        String rightName = storeInfoService.getByName("admin-name").getValue();
        String rightPassword = storeInfoService.getByName("admin-password").getValue();
        if (name.equals(rightName) && password.equals(rightPassword)) {
            return new CommonResult<>(0, "登录成功！");

        } else {
            return new CommonResult<>(400, "账号或密码错误！");
        }
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

    
    @PostMapping("/defaultFoodImage")
    public CommonResult<StoreInfo> defaultFoodImage(@RequestParam("file") MultipartFile file) {
        Integer code = storeInfoService.defaultFoodImage(file);
        if (code == 400) {
            return new CommonResult<>(400, "服务器保存文件失败");
        } else if (code == 401) {
            return new CommonResult<>(401, "服务器保存文件成功,但插入数据库失败");
        } else {
            return new CommonResult<>(0, "更新成功");
        }
    }

    @PostMapping("/defaultTableImage")
    public CommonResult<StoreInfo> defaultTableImage(@RequestParam("file") MultipartFile file) {
        Integer code = storeInfoService.defaultTableImage(file);
        if (code == 400) {
            return new CommonResult<>(400, "服务器保存文件失败");
        } else if (code == 401) {
            return new CommonResult<>(401, "服务器保存文件成功,但插入数据库失败");
        } else {
            return new CommonResult<>(0, "更新成功");
        }
    }

}
