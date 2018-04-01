package kz.mircella.mircella_electronic_shop.product;

import kz.mircella.mircella_electronic_shop.feedback.Feedback;
import kz.mircella.mircella_electronic_shop.order.Order;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
@AllArgsConstructor
@Getter
@Setter
public class Product implements Serializable {
    private static final long serialVersionUID = 8701094601028307623L;
    @Id
    @Column(length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 10000, nullable = false)
    private String description;

    @Column(nullable = false, length = 255)
    private String path;

    @Column(length = 11, nullable = false)
    private double price;

    @Column(length = 11, nullable = false)
    private int count;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ProductCategory.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_id", nullable = false, referencedColumnName = "id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = Order.class, cascade = CascadeType.ALL)
    private Set<Order> orders;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = Feedback.class, cascade = CascadeType.ALL)
    private Set<Feedback> feedbacks ;

    public Product() {
    }

    public Product(String title, String description, double price, int count, ProductCategory productCategory, String path) {
        this.title = title;
        this.path = path;
        this.description = description;
        this.price = price;
        this.count = count;
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getTitle(), product.getTitle()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                Objects.equals(getProductCategory(), product.getProductCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getProductCategory());
    }
}
