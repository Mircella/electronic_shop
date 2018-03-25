package kz.mircella.mircella_electronic_shop.entity.user;

import kz.mircella.mircella_electronic_shop.entity.Feedback;
import kz.mircella.mircella_electronic_shop.entity.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = -8457321766670856111L;
    @Id
    @Column(name = "id", length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "card", nullable = false, length = 10, unique = true)
    private Long card;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, targetEntity = Order.class, cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<Order>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, targetEntity = Feedback.class, cascade = CascadeType.ALL)
    private Set<Feedback> feedbacks = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password, Long card, RoleEnum role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.card = card;
        this.role = role;
    }

    public User(String name, String email, String password, Long card, RoleEnum role, Set<Order> orders, Set<Feedback> feedbacks) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.card = card;
        this.role = role;
        this.orders = orders;
        this.feedbacks = feedbacks;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getCard(), user.getCard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getCard());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", card=" + card +
                ", role=" + role +
                '}';
    }
}
