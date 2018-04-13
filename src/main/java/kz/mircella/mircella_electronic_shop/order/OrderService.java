package kz.mircella.mircella_electronic_shop.order;

import kz.mircella.mircella_electronic_shop.exception.server_exception.NotFoundException;
import kz.mircella.mircella_electronic_shop.product.Product;
import kz.mircella.mircella_electronic_shop.product.ProductService;
import kz.mircella.mircella_electronic_shop.user.UserService;
import kz.mircella.mircella_electronic_shop.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private UserService userService;
    private ProductService productService;

    @Transactional
    public void saveOrder(String username, String productId) {
        Order order = findOrder(username);
        if (order == null) {
            order = createOrder(username, Long.parseLong(productId));
        } else {
            Product product = productService.getProductById(Long.parseLong(productId));
            order.getProducts().add(product);
        }
        orderRepository.saveAndFlush(order);
    }

    public OrderDetails getOrder(String username) {
        Order order = findOrder(username);
        if (order == null) {
            return null;
        }
        return orderMapper.mapToOrderDetails(order);
    }

    @Transactional
    public void deleteOrder(String productTitle) {
    }

    public Order findOrder(String username) {
        User user = userService.getUserByName(username);
        List<Order> orders = orderRepository.findOrdersByUser(user);
        return orders.isEmpty() ? null : orders.stream().sorted(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return -(o1.getOrderDate().compareTo(o2.getOrderDate()));
            }
        }).findFirst().orElseThrow(() -> new NotFoundException("No order for %s", username));
    }

    private Order createOrder(String username, Long productId) {
        Long id = createOrderId();
        Date orderDate = new Date();
        User user = userService.getUserByName(username);
        List<Product> products = new ArrayList<>();
        Product product = productService.getProductById(productId);
        products.add(product);
        return new Order(id, orderDate, user, products);
    }

    private Long createOrderId() {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        Long id;
        do {
            for (int i = 0; i < 4; i++) {
                int number = random.nextInt(10);
                sb.append(number);
            }
            id = Long.parseLong(sb.toString());
        } while (orderRepository.existsById(id));
        return id;
    }
}
