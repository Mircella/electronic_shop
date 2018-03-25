package kz.mircella.mircella_electronic_shop.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = 8701094601028307623L;
    @Id
    @Column(name = "id",length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "path",nullable = false)
    private String path;

    @Column(name = "price", length = 11, nullable = false)
    private double price;

    @Column(name = "count", length = 11)
    private int count;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ProductCategory.class,cascade = CascadeType.ALL)
    @JoinColumn(name="product_category_id", nullable = false, referencedColumnName = "id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = Order.class,cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<Order>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, targetEntity = Feedback.class, cascade = CascadeType.ALL)
    private Set<Feedback> feedbacks = new HashSet<>();

    public Product() {
    }

    public Product(String title, double price, int count, ProductCategory productCategory, String path) {
        this.title = title;
        this.price = price;
        this.count = count;
        this.path = path;
        this.productCategory = productCategory;
    }

    public Product(String title, String description, double price, int count, ProductCategory productCategory, String path) {
        this.title = title;
        this.path = path;
        this.description = description;
        this.price = price;
        this.count = count;
        this.productCategory = productCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", productCategory=" + productCategory.getTitle() +
                '}';
    }
}
