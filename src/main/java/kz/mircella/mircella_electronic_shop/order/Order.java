package kz.mircella.mircella_electronic_shop.order;

import kz.mircella.mircella_electronic_shop.product.Product;
import kz.mircella.mircella_electronic_shop.user.entity.User;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -551583044612458621L;

    @Column(name = "order_date", length = 100, nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date orderDate;

    @EmbeddedId
    private OrderId orderId;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
    private User user;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id",nullable = false,referencedColumnName = "id")
    private Product product;

    @Column(length = 100, nullable = false)
    private int count;

    public Order() {
    }


    public Order(OrderId orderId, Date orderDate, int count, User user, Product product) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.count = count;
        this.user = user;
        this.product = product;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getCount() == order.getCount() &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                Objects.equals(getUser(), order.getUser()) &&
                Objects.equals(getProduct(), order.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderDate(), getUser(), getProduct(), getCount());
    }

    @Override
    public String toString() {
        return "Order{" +
                ", orderDate=" + orderDate +
                ", entity=" + user.getUsername() +
                ", product=" + product.getTitle() +
                ", count=" + count +
                '}';
    }
}
