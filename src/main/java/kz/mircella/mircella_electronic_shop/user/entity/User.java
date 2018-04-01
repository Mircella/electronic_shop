package kz.mircella.mircella_electronic_shop.user.entity;

import kz.mircella.mircella_electronic_shop.feedback.Feedback;
import kz.mircella.mircella_electronic_shop.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = -8457321766670856111L;
    @Id
    @Column(length = 11, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 10, unique = true)
    private Long card;

    @Column(nullable = false, length = 255, unique = true)
    private String photoPath;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, targetEntity = Order.class, cascade = CascadeType.ALL)
    private Set<Order> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, targetEntity = Feedback.class, cascade = CascadeType.ALL)
    private Set<Feedback> feedbacks;

    public User() {
    }

    public User(String username, String email, String password, String photoPath, Long card, RoleEnum role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.card = card;
        this.role = role;
        this.photoPath = photoPath;
    }

    public User(String name, String email, String password, String photoPath, Long card, RoleEnum role, Set<Order> orders, Set<Feedback> feedbacks) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.card = card;
        this.role = role;
        this.photoPath = photoPath;
        this.orders = orders;
        this.feedbacks = feedbacks;
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

}
