package kz.mircella.mircella_electronic_shop.service;

import kz.mircella.mircella_electronic_shop.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order>getOrders();
    Order getOrderById(long id);
    Order saveOrder(Order order);
    void deleteOrderById(long id);

}
