package ltd.linqiu.controller;

import ltd.linqiu.entity.Remark;
import ltd.linqiu.response.CommonResult;
import ltd.linqiu.response.TableResult;
import ltd.linqiu.service.IRemarkService;
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
@RequestMapping("/remark")
public class RemarkController {
    @Autowired
    private IRemarkService remarkService;

    @GetMapping("/all")
    public TableResult<Remark> all() {
        List<Remark> remarkList = remarkService.getAll();
        return new TableResult<>(0, "", remarkList.size(), remarkList);
    }

    @GetMapping("/orderId/{orderId}")
    public CommonResult<Remark> getByOrderId(@PathVariable("orderId")Integer orderId) {
        Remark remark = remarkService.getByOrderId(orderId);
        return new CommonResult<>(0, remark,"根据orderId获取评论");
    }

    @PostMapping("/add")
    public CommonResult<Integer> add(Remark remark) {
        if (remarkService.add(remark) == 1) {
            return new CommonResult<>(0, remark.getId(), "添加成功！");
        } else {
            return new CommonResult<>(400, "添加失败！");
        }
    }


    @PostMapping("/uploadImage")
    public CommonResult<Integer> uploadImage(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile file) {
        if (remarkService.uploadImage(id, file)) {
            return new CommonResult<>(0, "上传成功！");
        } else {
            return new CommonResult<>(400, "上传失败！");
        }
    }

    @PostMapping("/reply")
    public CommonResult<Integer> reply(@RequestParam("id") Integer id, @RequestParam("reply") String reply) {
        if (remarkService.reply(reply, id) == 1) {
            return new CommonResult<>(0, "回复成功！");
        } else {
            return new CommonResult<>(400, "回复失败！");
        }
    }

    @PostMapping("/delete")
    public CommonResult<Integer> delete(Remark remark) {
        if (remarkService.delete(remark) == 1) {
            return new CommonResult<>(0, "删除成功！");
        } else {
            return new CommonResult<>(400, "删除失败！");
        }
    }
}
