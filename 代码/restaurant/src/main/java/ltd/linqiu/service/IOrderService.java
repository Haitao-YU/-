package ltd.linqiu.service;

import ltd.linqiu.entity.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();

    Integer add(Order order);
}
