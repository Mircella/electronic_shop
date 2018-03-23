package kz.mircella.mircella_electronic_shop.entity;

import kz.mircella.mircella_electronic_shop.entity.user.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "feedbacks")
@EntityListeners(AuditingEntityListener.class)
public class Feedback implements Serializable{
    private static final long serialVersionUID = -9114506376454842335L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String text;

    @Column(nullable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable = false, referencedColumnName = "id")
    private User user;

    public Feedback() {
    }

    public Feedback(String text, Date date, User user) {
        this.text = text;
        this.date = date;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return "Text "+this.getText()+"\nDate "+sdf.format(this.getDate());
    }
}
