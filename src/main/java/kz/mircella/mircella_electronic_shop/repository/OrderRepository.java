package kz.mircella.mircella_electronic_shop.repository;

import kz.mircella.mircella_electronic_shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("select count(o) from Order o")
    long getCount();
}
