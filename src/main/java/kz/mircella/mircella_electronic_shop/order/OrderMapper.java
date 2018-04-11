package kz.mircella.mircella_electronic_shop.order;

import kz.mircella.mircella_electronic_shop.product.ProductDetails;
import kz.mircella.mircella_electronic_shop.product.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderMapper {

    private final ProductMapper productMapper;

    public OrderDetails mapToOrderDetails(Order order) {
        Map<ProductDetails, Integer> products = productsListToMap(order.getProducts()
                .stream()
                .map(product -> productMapper.mapToProductDetails(product))
                .collect(Collectors.toList()));
        return OrderDetails.builder()
                .username(order.getUser().getUsername())
                .address(order.getUser().getUsername())
                .date(order.getOrderDate())
                .id(order.getId())
                .products(products)
                .price(countPrice(products))
                .build();
    }

    private Map<ProductDetails, Integer> productsListToMap(List<ProductDetails> products) {
        Map<ProductDetails, Integer> result = new HashMap<>();
        products.forEach(productDetails -> {
            if (result.containsKey(productDetails)) {
                int count = result.get(productDetails);
                count++;
                result.put(productDetails, count);
            } else {
                result.put(productDetails, 1);
            }
        });
        return result;
    }

    private double countPrice(Map<ProductDetails, Integer> products) {
        double resut = 0;
        for (Map.Entry<ProductDetails, Integer> pair : products.entrySet()) {
            resut += pair.getValue() * pair.getKey().getPrice();
        }
        return resut;
    }
}
