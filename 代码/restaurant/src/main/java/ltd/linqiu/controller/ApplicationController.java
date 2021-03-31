package ltd.linqiu.controller;

import ltd.linqiu.entity.Application;
import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.TableResult;
import ltd.linqiu.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/volunteer/application")
public class ApplicationController {
    @Autowired
    private IApplicationService applicationService;

    @GetMapping("/all")
    public TableResult<Application> all() {
        List<Application> applicationList = applicationService.getAll();
        return new TableResult<>(0, "所有志愿时长登记申请", applicationList.size(), applicationList);
    }

    @GetMapping("/{stuId}")
    public CommonResult<List<Application>> showByTypeId(@PathVariable("stuId") String stuId) {
        List<Application> ret = applicationService.getByStuId(stuId);
        // 这里没必要判断是否ret为空列表
        return new CommonResult<>(0, ret, "根据学生id获得志愿时长登记申请");
    }

    @PostMapping("/add")
    public CommonResult<Integer> add(Application data) {
        System.out.println(data);
        Integer id = applicationService.add(data);
        if (id != 0) {
            return new CommonResult<>(0, id, "添加成功,申请表id为" + id);
        } else {
            return new CommonResult<>(400, "添加失败！");
        }
    }


    @PostMapping("/evidentMaterial")
    public CommonResult<String> evidentMaterial(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile file) {
        // 如果文件不为空
        if (!file.isEmpty() && applicationService.evidentMaterial(id, file)) {
            return new CommonResult<>(0, "文件上传成功");
        } else {
            return new CommonResult<>(400, "文件上传失败");
        }
    }


    @PostMapping("/modifyState")
    public CommonResult<Integer> edit(Application data) {
        if (applicationService.modifyState(data) == 1) {
            return new CommonResult<>(0, "修改成功！");
        } else {
            return new CommonResult<>(400, "修改失败！");
        }
    }

    @PostMapping("/delete")
    public CommonResult<Integer> delete(Application data) {
        if (applicationService.delete(data) == 1) {
            return new CommonResult<>(0, "删除成功！");
        } else {
            return new CommonResult<>(400, "删除失败！");
        }
    }
}
