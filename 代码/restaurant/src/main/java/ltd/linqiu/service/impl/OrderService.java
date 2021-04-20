package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Order;
import ltd.linqiu.mapper.OrderMapper;
import ltd.linqiu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getAll() {
        return orderMapper.selectAll();
    }


    @Override
    public Integer add(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public List<Order> getByPhone(String phone) {
        return orderMapper.selectByPhone(phone);
    }

    @Override
    public List<Order> getByPhoneState(String phone, Integer state) {
        return orderMapper.selectByPhoneState(phone, state);
    }


    //    public static void main(String[] args) {
    //        OrderFood orderFood = new OrderFood(1, "name", 12.3, "image", "remark", 2);
    //        System.out.println(JSON.toJSONString(orderFood));
    //        List<OrderFood> list = new ArrayList<>();
    //        list.add(new OrderFood(1, "name1", 12.30, "imageUrl", "remark", 1));
    //        list.add(new OrderFood(2, "name2", 12.30, "imageUrl", "remark", 2));
    //        list.add(new OrderFood(3, "name3", 12.30, "imageUrl", "remark", 3));
    //        System.out.println(JSON.toJSONString(list));
    //        List<OrderFood> l = JSON.parseObject(
    //                "[{\"count\":2,\"id\":1,\"image\":\"image\",\"name\":\"name\"," + "\"price\":12.3," + "\"remark\":\"remark\"},{\"count\":2,\"id\":2,\"image\":\"image\",\"name\":\"name\",\"price\":12.3," + "\"remark\":\"remark\"},{\"count\":2,\"id\":3,\"image\":\"image\",\"name\":\"name\",\"price\":12.3," + "\"remark\":\"remark\"}]",
    //                List.class);
    //        System.out.println(l.get(1));
    //
    //        OrderFood f = JSON.parseObject(String.valueOf(l.get(1)), OrderFood.class);
    //
    //        System.out.println(f.toString());
    //    }
}
