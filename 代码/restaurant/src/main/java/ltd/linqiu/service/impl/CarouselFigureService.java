package ltd.linqiu.service.impl;

import ltd.linqiu.entity.CarouselFigure;
import ltd.linqiu.mapper.CarouselFigureMapper;
import ltd.linqiu.service.ICarouselFigureService;
import ltd.linqiu.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CarouselFigureService implements ICarouselFigureService {

    @Autowired
    private CarouselFigureMapper carouselFigureMapper;

    @Override
    public List<CarouselFigure> getAll() {
        return carouselFigureMapper.selectAll();
    }

    @Override
    public CarouselFigure getById(Integer id) {
        return carouselFigureMapper.selectById(id);
    }

    @Override
    public Integer add(MultipartFile file) {
        // 轮播图数量不能超过5
        if (carouselFigureMapper.selectAll().size() >= 5) {
            return 400;
        } else {
            CarouselFigure carouselFigure = new CarouselFigure();
            String url = FileUtil.save(file, "CarouselFigure");
            if (url == null) {
                return 401;
            }
            carouselFigure.setUrl("http://www.linqiu.ltd/file/" + url);
            if (carouselFigureMapper.insert(carouselFigure) == 0) {
                return 402;
            } else {
                return 0;
            }
        }
    }

    @Override
    public Integer delete(CarouselFigure carouselFigure) {
        return carouselFigureMapper.delete(carouselFigure);
    }
}
