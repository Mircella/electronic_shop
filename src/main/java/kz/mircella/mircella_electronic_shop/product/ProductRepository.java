package kz.mircella.mircella_electronic_shop.product;

import kz.mircella.mircella_electronic_shop.product_category.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM products ORDER BY price ASC LIMIT :limit")
    List<Product> getProductsWithLowPrice(@Param("limit") int limit);

    List<Product> getProductsByProductCategory(ProductCategory category);

    @Query("select p from Product p where p.title like %:search% or p.description like %:search%")
    List<Product> findProductsByTitleOrDescription(@Param("search") String search);

}
