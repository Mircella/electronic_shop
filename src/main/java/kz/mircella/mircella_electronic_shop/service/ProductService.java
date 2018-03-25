package kz.mircella.mircella_electronic_shop.service;

import kz.mircella.mircella_electronic_shop.entity.Product;
import kz.mircella.mircella_electronic_shop.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAllProducts();
    List<Product>getProductsWithProductCategory(String category);
    List<Product>getProductsWithLowPrice(int amount);
    List<Product> getProductByNameOrDescription(String search);
    List<Product> getProductsByProductCategory(ProductCategory productCategory);

    Product getProductById(long id);
    Product getProductByIdWithFeedbacks(long id);
    void deleteProductById(long id);
    Product saveProduct(Product product);
}
