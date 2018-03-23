package kz.mircella.mircella_electronic_shop.service;

import kz.mircella.mircella_electronic_shop.entity.Product;
import kz.mircella.mircella_electronic_shop.entity.ProductCategory;
import kz.mircella.mircella_electronic_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsWithProductCategory(String category) {
        return productRepository.getProductsWithProductCategory(category);
    }

    @Override
    public List<Product> getProductsWithLowPrice(int amount) {
        return productRepository.getProductsWithLowPrice(amount);
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getProductByNameOrDescription(String search) {
        return productRepository.findProductsByTitleOrDescription(search);
    }

    @Override
    public List<Product> getProductsByProductCategory(ProductCategory productCategory) {
        return productRepository.getProductsByProductCategory(productCategory);
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        Long id = getId(product.getTitle(),product.getPrice());
        product.setId(id);
        return productRepository.saveAndFlush(product);
    }

    private Long getId(String title, double price){
        Long id;
        Product product = productRepository.findProductByTitleAndPrice(title,price);
        if(product!=null){
            id = product.getId();
        }else{
            id = productRepository.getCount()+1;
        }
        return id;
    }
}
