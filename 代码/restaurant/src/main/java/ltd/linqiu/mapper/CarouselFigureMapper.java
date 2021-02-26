package ltd.linqiu.mapper;

import ltd.linqiu.entity.CarouselFigure;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CarouselFigureMapper {
    @Select("select * from carousel_figure")
    List<CarouselFigure> selectAll();

    @Select("select * from carousel_figure where id = #{id}")
    CarouselFigure selectById(@Param("id") Integer id);

    @Insert("insert into carousel_figure(url) values (#{url})")
    Integer insert(CarouselFigure carouselFigure);

    @Update("update carousel_figure set url = #{url} where id = #{id}")
    Integer update(CarouselFigure carouselFigure);

    @Delete("delete from carousel_figure where id = #{id}")
    Integer delete(CarouselFigure carouselFigure);
}
