package kz.mircella.mircella_electronic_shop.service;

import kz.mircella.mircella_electronic_shop.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCategoryService {
    List<ProductCategory> getAllProductCategories();
    ProductCategory getProductCategoryById(long id);
    void deleteProductCategoryById(long id);
    ProductCategory saveProductCategory(ProductCategory productCategory);
}
