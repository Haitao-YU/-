package ltd.linqiu.service;

import ltd.linqiu.entity.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();

    Integer add(Order order);

    List<Order> getByPhone(String phone);

    List<Order> getByPhoneState(String phone, Integer state);
}
