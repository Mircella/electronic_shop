package kz.mircella.mircella_electronic_shop.feedback;

import kz.mircella.mircella_electronic_shop.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findFeedbacksByProduct(Product product);
}
