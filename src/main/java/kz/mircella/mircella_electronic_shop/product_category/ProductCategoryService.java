package kz.mircella.mircella_electronic_shop.product_category;

import kz.mircella.mircella_electronic_shop.exception.server_exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAllProductCategories() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        productCategories.forEach(productCategory -> productCategory.getProducts());
        validateProductCategories(productCategories);
        return productCategories;
    }

    public ProductCategory getProductCategoryById(long id) {
        ProductCategory category = productCategoryRepository.getOne(id);
        validateProductCategory(category, id);
        return category;
    }

    private void validateProductCategories(List<ProductCategory> productCategories) {
        if (productCategories == null) {
            throw new NotFoundException("There are no productCategories");
        }
    }

    private void validateProductCategory(ProductCategory category, long id) {
        if (category == null) {
            throw new NotFoundException("There is no category with id %d", id);
        }
    }
}
