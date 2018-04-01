package kz.mircella.mircella_electronic_shop.product_category;

import kz.mircella.mircella_electronic_shop.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product_category")
@AllArgsConstructor
@Getter
@Setter
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = -1284135854138678054L;
    @Id
    @Column(length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, targetEntity = Product.class, cascade = CascadeType.ALL)
    private Set<Product> products;

    public ProductCategory() {
    }

    public ProductCategory(String title) {
        this.title = title;
    }

    public ProductCategory(String title, Set<Product> products) {
        this.title = title;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategory)) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    public Set<Product> getProducts() {
        products.forEach(product -> {
            product.getOrders();
            product.getFeedbacks();
        });
        return products;
    }
}

