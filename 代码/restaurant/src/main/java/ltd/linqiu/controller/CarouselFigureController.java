package ltd.linqiu.controller;

import ltd.linqiu.entity.CarouselFigure;
import ltd.linqiu.entity.CommonResult;
import ltd.linqiu.entity.TableResult;
import ltd.linqiu.service.ICarouselFigureService;
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
@RequestMapping("/carouselFigure")
public class CarouselFigureController {
    @Autowired
    private ICarouselFigureService carouselFigureService;

    @GetMapping("/all")
    public TableResult<CarouselFigure> all() {
        List<CarouselFigure> carouselFigureList = carouselFigureService.getAll();
        return new TableResult<>(0, "", carouselFigureList.size(), carouselFigureList);
    }

    @GetMapping("/{id}")
    public CommonResult<CarouselFigure> getById(@PathVariable("id") Integer id) {
        return new CommonResult<CarouselFigure>(0, carouselFigureService.getById(id), "根据id获得轮播图");
    }

    @PostMapping("/add")
    public CommonResult<Integer> add(@RequestParam("file") MultipartFile file) {
        Integer code = carouselFigureService.add(file);
        if (code == 400) {
            return new CommonResult<>(400, "添加失败，轮播图到达最大数量！");
        } else if (code == 401) {
            return new CommonResult<>(401, "服务器保存文件失败");
        } else if (code == 402) {
            return new CommonResult<>(402, "服务器保存文件成功,但插入数据库失败");
        } else {
            return new CommonResult<>(0, "上传成功");
        }
    }

    @PostMapping("/delete")
    public CommonResult<Integer> delete(CarouselFigure data) {
        if (carouselFigureService.delete(data) == 1) {
            return new CommonResult<>(0, "删除成功！");
        } else {
            return new CommonResult<>(400, "删除失败！");
        }
    }
}
