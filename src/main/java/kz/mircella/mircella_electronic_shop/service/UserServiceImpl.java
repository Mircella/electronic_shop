package kz.mircella.mircella_electronic_shop.service;

import kz.mircella.mircella_electronic_shop.entity.user.User;
import kz.mircella.mircella_electronic_shop.repository.UserRepository;
import kz.mircella.mircella_electronic_shop.util.EncrytedPasswordUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.getOne(id);
    }

    public User getUserByNameWithEncrytedPassword(String name) {
        User user = userRepository.findUserByUsername(name);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findUserByUsername(name);
    }

    public User saveUser(User user) {
        Long id = getId(user.getUsername());
        user.setId(id);
        return userRepository.saveAndFlush(user);
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    private Long getId(String name) {
        Long id;
        User user = userRepository.findUserByUsername(name);
        if (user != null) {
            id = user.getId();
        } else {
            id = userRepository.getCount() + 1;
        }
        return id;
    }

    public String getUserRole(String name) {
        User user = getUserByNameWithEncrytedPassword(name);
        String role = user.getRole().name();
        return role;
    }
}
