package kz.mircella.mircella_electronic_shop.user;

import kz.mircella.mircella_electronic_shop.exception.server_exception.NotFoundException;
import kz.mircella.mircella_electronic_shop.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User getUserByNameWithEncrytedPassword(String name) {
        User user = userRepository.findUserByUsername(name);
        return user;
    }

    public User getUserByName(String name) {
        User user = userRepository.findUserByUsername(name);
        return user;
    }

    @Transactional
    public User saveUser(User user) {
        Long id = getId(user.getUsername());
        user.setId(id);
        return userRepository.saveAndFlush(user);
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
        User user = getUserByName(name);
        String role = user.getRole().name();
        return role;
    }

    private void validateUser(User user, String username) {
        if (user == null) {
            throw new NotFoundException("There is no user %s", username);
        }
    }
}
