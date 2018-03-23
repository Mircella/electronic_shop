package kz.mircella.mircella_electronic_shop.repository;

import kz.mircella.mircella_electronic_shop.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    @Query("select count(f) from Feedback f")
    long getCount();
}
