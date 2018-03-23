package kz.mircella.mircella_electronic_shop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product_category")
public class ProductCategory implements Serializable{
    private static final long serialVersionUID = -1284135854138678054L;
    @Id
    @Column(name = "id", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, targetEntity = Product.class,cascade = CascadeType.ALL)
    private Set<Product>products = new HashSet<Product>();

    public ProductCategory() {
    }

    public ProductCategory(String title) {
        this.title = title;
    }

    public ProductCategory(long id,String title) {
        this.id = id;
        this.title = title;
    }

    public ProductCategory(String title, Set<Product> products) {
        this.title = title;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
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

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

