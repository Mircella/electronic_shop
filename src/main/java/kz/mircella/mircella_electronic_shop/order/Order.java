package kz.mircella.mircella_electronic_shop.order;

import kz.mircella.mircella_electronic_shop.product.Product;
import kz.mircella.mircella_electronic_shop.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -551583044612458621L;

    @Id
    private Long id;

    @Column(name = "order_date", length = 100, nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", table = "products", nullable = false, referencedColumnName = "id"))
    private List<Product> products;

    public Order() {
    }

    public Order(Date orderDate, double price, User user) {
        this.orderDate = orderDate;
        this.user = user;
    }

    public Order(Long id, Date orderDate, User user, List<Product> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.user = user;
        this.products = products;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                Objects.equals(getUser(), order.getUser()) &&
                Objects.equals(getProducts(), order.getProducts());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getOrderDate(), getUser(), getProducts());
    }
}
