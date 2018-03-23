package kz.mircella.mircella_electronic_shop.service;

import kz.mircella.mircella_electronic_shop.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User>getAllUser();
    User getUserById(long id);
    User saveUser(User user);
    User getUserByNameWithEncrytedPassword(String name);
    User getUserByName(String name);
    void deleteUserById(long id);
}
