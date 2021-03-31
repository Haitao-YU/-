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
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResult<Integer> add(CarouselFigure data) {
        if (carouselFigureService.add(data) == 1) {
            return new CommonResult<>(0, "添加成功！");
        } else {
            return new CommonResult<>(400, "添加失败，轮播图到达最大数量！");
        }
    }


    @PostMapping("/edit")
    public CommonResult<Integer> edit(CarouselFigure data) {
        if (carouselFigureService.modify(data) == 1) {
            return new CommonResult<>(0, "修改成功！");
        } else {
            return new CommonResult<>(400, "修改失败！");
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
