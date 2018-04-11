package kz.mircella.mircella_electronic_shop.order;

import kz.mircella.mircella_electronic_shop.product.ProductDetails;
import lombok.*;

import java.util.Date;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {
    private Long id;
    private String address;
    private Date date;
    private Map<ProductDetails, Integer> products;
    private String username;
    private double price;
}
