package ltd.linqiu.service.impl;

import ltd.linqiu.entity.Line;
import ltd.linqiu.entity.Order;
import ltd.linqiu.mapper.InformMapper;
import ltd.linqiu.mapper.OrderMapper;
import ltd.linqiu.mapper.TableMapper;
import ltd.linqiu.service.ILineService;
import ltd.linqiu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private InformMapper informMapper;
    @Autowired
    private ILineService lineService;


    @Override
    public List<Order> getAll() {
        return orderMapper.selectAll();
    }


    @Override
    public List<Order> getByConditions(Map<String, String> conditions) throws Exception {
        Order order = new Order(conditions);
        // 当传递的state大于3，
        // 4:  0和1都可
        // 5:  2和3都可
        // 6:  0和2都可
        // 7:  1和3都可
        if (order.getState() != null) {
            int state = order.getState();
            if (state == 4) return orderMapper.selectByConditionsStateGreater(order, 0, 2);
            if (state == 5) return orderMapper.selectByConditionsStateGreater(order, 1, 3);
            if (state == 6) return orderMapper.selectByConditionsStateGreater(order, 0, 1);
            if (state == 7) return orderMapper.selectByConditionsStateGreater(order, 2, 3);
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
            // 向后台添加订单通知
            informMapper.insert(order.getTableId());
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
        if (orderMapper.updateStateById(orderMapper.selectById(orderId).getState() + 1, orderId) == 1) {
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


    @Override
    public List<Double> getOrderCount() {
        List<Double> ret = new ArrayList<>();
        ret.add(orderMapper.today());
        ret.add(orderMapper.lastDay());
        ret.add(orderMapper.thisWeek());
        ret.add(orderMapper.lastWeek());
        ret.add(orderMapper.thisMonth());
        ret.add(orderMapper.lastMonth());
        ret.add(orderMapper.allSum());
        return ret;
    }
}
