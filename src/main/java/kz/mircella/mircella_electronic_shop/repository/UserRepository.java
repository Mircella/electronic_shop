package kz.mircella.mircella_electronic_shop.repository;

import kz.mircella.mircella_electronic_shop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByName(String name);

    @Query("select count(u) from User u")
    long getCount();
}
