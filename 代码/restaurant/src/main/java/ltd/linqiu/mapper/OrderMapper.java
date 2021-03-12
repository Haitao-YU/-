package ltd.linqiu.mapper;

import ltd.linqiu.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from order")
    List<Order> selectAll();

    @Select("select * from order where phone = #{phone}")
    List<Order> selectByPhone(@Param("phone") String  phone);


//    @Insert("insert into order() values (#{name},#{price},#{typeId},#{image})")
//    Integer insert(Order order);

//    @Update("update order set name = #{name}, price = #{price}, type_id = #{typeId}, image = #{image} where id = #{id}")
//    Integer update(Order order);
//
//    @Delete("delete from order where id = #{id}")
//    Integer delete(Order order);
}
