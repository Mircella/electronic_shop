package kz.mircella.mircella_electronic_shop.feedback;

import kz.mircella.mircella_electronic_shop.product.Product;
import kz.mircella.mircella_electronic_shop.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "feedbacks")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Feedback implements Serializable {
    private static final long serialVersionUID = -9114506376454842335L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    private Product product;

    public Feedback() {
    }

    public Feedback(String text, Date date, User user, int rating, Product product) {
        this.text = text;
        this.date = date;
        this.user = user;
        this.rating = rating;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feedback)) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(getUser(), feedback.getUser()) &&
                Objects.equals(getText(), feedback.getText()) &&
                Objects.equals(getDate(), feedback.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getText(), getDate());
    }
}
