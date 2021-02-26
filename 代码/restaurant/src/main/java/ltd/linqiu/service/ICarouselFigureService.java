package ltd.linqiu.service;

import ltd.linqiu.entity.CarouselFigure;

import java.util.List;

public interface ICarouselFigureService {
    List<CarouselFigure> getAll();

    CarouselFigure getById(Integer id);

    Integer add(CarouselFigure carouselFigure);

    Integer modify(CarouselFigure carouselFigure);

    Integer delete(CarouselFigure carouselFigure);
}
