package ltd.linqiu.mapper;

import ltd.linqiu.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {
    // select
    @Select("select * from `order` order by id desc")
    List<Order> selectAll();

    List<Order> selectByConditions(Order order);

    List<Order> selectByConditionsStateGreater(@Param("order") Order order, @Param("state1") int state1, @Param("state2") int state2);

    @Select("select * from `order` where id = #{id} order by id desc")
    Order selectById(@Param("id") Integer id);

    @Select("select * from `order` where phone = #{phone} order by id desc")
    List<Order> selectByPhone(@Param("phone") String phone);

    @Select("select * from `order` where phone = #{phone} and state = #{state} order by id desc")
    List<Order> selectByPhoneState(@Param("phone") String phone, @Param("state") Integer state);

    //insert
    Integer insert(Order order);

    //update
    @Update("update `order` set state = #{state} where id = #{id}")
    Integer updateStateById(@Param("state") Integer state, @Param("id") Integer id);


    // select
    @Select("select sum(sum) from `order` where to_days(date) = to_days(now())")
    Double today();

    @Select("select sum(sum) from `order` where to_days(date) = to_days(now()) - 1")
    Double lastDay();

    @Select("select sum(sum) from `order` where YEARWEEK(date_format(date,'%Y-%m-%d'))=YEARWEEK(now())")
    Double thisWeek();

    @Select("select sum(sum) from `order` where YEARWEEK(date_format(date,'%Y-%m-%d'))=YEARWEEK(now()) - 1")
    Double lastWeek();

    @Select("select sum(sum) from `order` where date_format(date, '%Y%m') = date_format(CURDATE(), '%Y%m')")
    Double thisMonth();

    @Select("select sum(sum) from `order` where period_diff(date_format(now(), '%Y%m'), date_format(date, '%Y%m')) = 1")
    Double lastMonth();

    @Select("select sum(sum) from `order`")
    Double allSum();
}
