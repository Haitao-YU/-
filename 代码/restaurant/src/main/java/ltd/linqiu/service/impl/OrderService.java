package ltd.linqiu.service.impl;

import com.alibaba.fastjson.JSON;
import ltd.linqiu.entity.Food;
import ltd.linqiu.entity.Order;
import ltd.linqiu.entity.OrderContent;
import ltd.linqiu.mapper.OrderMapper;
import ltd.linqiu.service.IFoodService;
import ltd.linqiu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IFoodService foodService;

    @Override
    public List<Order> getAll() {
        return orderMapper.selectAll();
    }


    @Override
    public Integer add(Order order) {
        order.setState(0);
        order.setDate(new Date());
        this.completeContent(order);
        return orderMapper.insert(order);
    }

    /**
     * 工具函数：完善order的contents属性
     */
    void completeContent(Order order) {
        if (order == null) {
            return;
        }
        List<List<Integer>> content = JSON.parseObject(order.getContent(), List.class);
//        System.out.println(content);
        for (List<Integer> integers : content) {
//            System.out.println(integers);
            // 获取contentId
            Integer contentId;
            Integer foodId = integers.get(0);
            Food food = foodService.getById(foodId);
//            System.out.println(food);
            OrderContent orderContent = new OrderContent(food);
//            System.out.println(orderContent);
            List<OrderContent> orderContentList = orderMapper.selectOneOrderContent(orderContent);
            // 判断orderContent是否已经存在
            if (orderContentList.size() == 0) {
                if (orderMapper.insertOrderContent(orderContent) == 1) {
                    contentId = orderMapper.selectOneOrderContent(orderContent).get(0).getId();
                } else {
                    throw new RuntimeException("插表失败！");
                }
            } else {
                contentId = orderContentList.get(0).getId();
            }
            // 将foodId改为contentId
            integers.set(0, contentId);
        }
        order.setContent(JSON.toJSONString(content));
    }
}
