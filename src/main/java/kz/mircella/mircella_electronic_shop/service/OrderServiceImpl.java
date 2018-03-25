package kz.mircella.mircella_electronic_shop.service;


import kz.mircella.mircella_electronic_shop.entity.Order;
import kz.mircella.mircella_electronic_shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(long id) {
        return orderRepository.getOne(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }
}
