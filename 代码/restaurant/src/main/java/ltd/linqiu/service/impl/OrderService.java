package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Line;
import ltd.linqiu.entity.Order;
import ltd.linqiu.mapper.OrderMapper;
import ltd.linqiu.mapper.TableMapper;
import ltd.linqiu.service.ILineService;
import ltd.linqiu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderService implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private ILineService lineService;

    @Override
    public List<Order> getAll() {
        return orderMapper.selectAll();
    }


    @Override
    public List<Order> getByConditions(Map<String, String> conditions) throws Exception {
        Order order = new Order(conditions);
        // 当state为3时，查询状态为已完成的订单（包括未评价1和已评价2）
        if (order.getState() == 3) {
            order.setState(0);
            return orderMapper.selectByConditionsStateGreater(order);
        }
        return orderMapper.selectByConditions(order);
    }


    /**
     * 客户添加订单
     */
    @Override
    public Boolean add(Order order) throws Exception {
        if (orderMapper.insert(order) == 1) {
            if (tableMapper.updateStateOrderIdById(1, order.getId(), order.getTableId()) == 0) {
                throw new Exception("订单插入时餐桌信息更新失败!");
            }
            // 删除用户的排队信息
            lineService.cancelEnqueue(new Line(order.getPhone(), null, null, null));
            return true;
        }
        return false;
    }

    /**
     * 管理员结算订单
     */
    @Override
    public Integer tally(Integer orderId, String tableId) {
        if (orderMapper.updateStateById(1, orderId) == 1) {
            if (tableMapper.updateStateOrderIdById(0, null, tableId) == 1) {
                return 0;
            }
            return 1;
        }
        return 2;
    }

    @Override
    public Order getById(Integer id) {
        return orderMapper.selectById(id);
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
