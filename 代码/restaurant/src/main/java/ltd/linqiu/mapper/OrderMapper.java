package ltd.linqiu.mapper;

import ltd.linqiu.entity.Order;
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

    @Select("select * from `order` where phone = #{phone} and state = #{state}")
    List<Order> selectByPhoneState(@Param("phone") String phone, @Param("state") Integer state);

    @Insert("insert into `order` (date,state,phone,content,tableId,nOfDiners,sum,remark) values (now(),0,#{phone},#{content},#{tableId},#{nOfDiners},#{sum},#{remark})")
    Integer insert(Order order);

    //    @Update("update order set name = #{name}, price = #{price}, type_id = #{typeId}, image = #{image} where id = #{id}")
    //    Integer update(Order order);
    //
    //    @Delete("delete from order where id = #{id}")
    //    Integer delete(Order order);
}
