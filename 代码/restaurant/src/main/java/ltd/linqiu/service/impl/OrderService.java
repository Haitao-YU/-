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

}
