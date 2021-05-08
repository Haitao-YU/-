package ltd.linqiu.service;

import ltd.linqiu.entity.Order;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    List<Order> getAll();

    List<Order> getByConditions(Map<String, String> conditions) throws Exception;

    Boolean add(Order order) throws Exception;

    Integer tally(Integer orderId, String tableId);

    Order getById(Integer id);

    List<Order> getByPhone(String phone);

    List<Order> getByPhoneState(String phone, Integer state);


}
