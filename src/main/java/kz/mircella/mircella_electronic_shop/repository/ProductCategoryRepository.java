package kz.mircella.mircella_electronic_shop.repository;

import kz.mircella.mircella_electronic_shop.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
    @Query("select count(pc) from ProductCategory pc")
    long getCount();

    ProductCategory findProductCategoryByTitle(String title);
}
