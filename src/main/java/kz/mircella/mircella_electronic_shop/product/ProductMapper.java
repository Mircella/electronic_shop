package kz.mircella.mircella_electronic_shop.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDetails mapToProductDetails(Product product) {
        return ProductDetails.builder()
                .title(product.getTitle())
                .path(product.getPath())
                .price(product.getPrice())
                .build();
    }
}
