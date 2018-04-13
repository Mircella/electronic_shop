package kz.mircella.mircella_electronic_shop.product;

import kz.mircella.mircella_electronic_shop.exception.server_exception.NotFoundException;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategory;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ProductCategoryService productCategoryService;

    public List<Product> getProductsWithLowPrice(int amount) {
        return productRepository.getProductsWithLowPrice(amount);
    }

    public Product getProductById(long id) {
        Product product = productRepository.getOne(id);
        validateProduct(product, id);
        return product;
    }

    public List<List<Product>> getProductsForMainPage(int limit) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        List<List<Product>> result = new ArrayList<>();
        productCategories.forEach(productCategory -> {
            List<Product> products = fetchProductsFromProductCategory(productCategory);
            result.add(products.stream().limit(limit).collect(Collectors.toList()));
        });
        return result;
    }

    public List<Product> getProductByNameOrDescription(String search) {
        return productRepository.findProductsByTitleOrDescription(search);
    }


    private List<Product> fetchProductsFromProductCategory(ProductCategory productCategory) {
        List<Product> products = productCategory.getProducts().stream().collect(Collectors.toList());
        return products == null ? Collections.emptyList() : products;
    }

    public List<Product> getProductsByProductCategory(ProductCategory productCategory) {
        List<Product> products = productRepository.getProductsByProductCategory(productCategory);
        return products == null ? Collections.emptyList() : products;
    }

    public void validateProduct(Product product, long id) {
        if (product == null) {
            throw new NotFoundException("There is no product %d", id);
        }
        product.getFeedbacks();
        product.getOrders();
    }
}
