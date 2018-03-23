package kz.mircella.mircella_electronic_shop.repository;

import kz.mircella.mircella_electronic_shop.entity.Product;
import kz.mircella.mircella_electronic_shop.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select count(p) from Product p")
    long getCount();

    @Query("select p from Product p join p.productCategory pc where pc.title=:category")
    List<Product> getProductsWithProductCategory(@Param("category") String category);

    @Query(nativeQuery = true, value = "SELECT * FROM products ORDER BY price ASC LIMIT :limit")
    List<Product> getProductsWithLowPrice(@Param("limit") int limit);

    List<Product> getProductsByProductCategory(ProductCategory category);

    Product findProductByTitleAndPrice(String title, double price);

    @Query("select p from Product p where p.title like %:search% or p.description like %:search%")
    List<Product> findProductsByTitleOrDescription(@Param("search") String search);

}
