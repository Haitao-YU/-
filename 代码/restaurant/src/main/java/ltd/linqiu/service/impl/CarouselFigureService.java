package ltd.linqiu.service.impl;

import ltd.linqiu.entity.CarouselFigure;
import ltd.linqiu.mapper.CarouselFigureMapper;
import ltd.linqiu.service.ICarouselFigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Integer add(CarouselFigure carouselFigure) {
        // 轮播图数量不能超过5
        if(carouselFigureMapper.selectAll().size()>=5){
            return 0;
        }else {
            return carouselFigureMapper.insert(carouselFigure);
        }
    }

    @Override
    public Integer modify(CarouselFigure carouselFigure) {
        return carouselFigureMapper.update(carouselFigure);
    }

    @Override
    public Integer delete(CarouselFigure carouselFigure) {
        return carouselFigureMapper.delete(carouselFigure);
    }
}
