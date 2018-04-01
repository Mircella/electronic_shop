package kz.mircella.mircella_electronic_shop.user;

import kz.mircella.mircella_electronic_shop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);

    @Query("select count(u) from User u")
    long getCount();
}
