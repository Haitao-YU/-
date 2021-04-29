package ltd.linqiu.service;

import ltd.linqiu.entity.CarouselFigure;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICarouselFigureService {
    List<CarouselFigure> getAll();

    CarouselFigure getById(Integer id);

    Integer add(MultipartFile file);

    Integer delete(CarouselFigure carouselFigure);
}
