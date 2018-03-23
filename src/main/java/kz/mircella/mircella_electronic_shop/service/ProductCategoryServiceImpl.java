package kz.mircella.mircella_electronic_shop.service;

import kz.mircella.mircella_electronic_shop.entity.ProductCategory;
import kz.mircella.mircella_electronic_shop.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(long id) {
        ProductCategory category  = productCategoryRepository.findById(id).get();
        return category;
    }

    @Override
    public void deleteProductCategoryById(long id) {
        productCategoryRepository.deleteById(id);
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        Long id = getId(productCategory.getTitle());
        productCategory.setId(id);
        return productCategoryRepository.saveAndFlush(productCategory);
    }

    private Long getId(String title){
        Long id;
        ProductCategory productCategory = productCategoryRepository.findProductCategoryByTitle(title);
        if(productCategory!=null){
            id = productCategory.getId();
        }else{
            id = productCategoryRepository.getCount()+1;
        }
        return id;
    }
}
