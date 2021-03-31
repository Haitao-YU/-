package ltd.linqiu.mapper;

import ltd.linqiu.entity.Order;
import ltd.linqiu.entity.OrderContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from order")
    List<Order> selectAll();

    @Select("select * from `order` where phone = #{phone}")
    List<Order> selectByPhone(@Param("phone") String phone);

    @Insert("insert into `order` (date,state,phone,content,tableId,numberOfDiners,sum,remark) values (now(),0,#{phone},#{content},#{tableId},#{numberOfDiners},#{sum},#{remark})")
    Integer insert(Order order);

    @Select("select * from order_content where (name,price,typeName,image,remark) = (#{name},#{price},#{typeName},#{image},#{remark})")
    List<OrderContent> selectOneOrderContent(OrderContent orderContent);

    @Insert("insert into order_content(name,price,typeName,image,remark) values (#{name},#{price},#{typeName},#{image},#{remark})")
    Integer insertOrderContent(OrderContent orderContent);

//    @Update("update order set name = #{name}, price = #{price}, type_id = #{typeId}, image = #{image} where id = #{id}")
//    Integer update(Order order);
//
//    @Delete("delete from order where id = #{id}")
//    Integer delete(Order order);
}
