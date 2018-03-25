package kz.mircella.mircella_electronic_shop.entity.user;

import kz.mircella.mircella_electronic_shop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private UserServiceImpl userService;

    public UserDto mapAppUserToUserUserDto(User user){
        return UserDto
                .builder()
                .userName(user.getUsername())
                .userEmail(user.getEmail())
                .userPassword(user.getPassword())
                .userCard(user.getCard())
                .userRole(user.getRole())
                .build();
    }

    public UserDto mapSecurityUserToUserDto(String userName){
        User appUser = userService.getUserByNameWithEncrytedPassword(userName);
        return mapAppUserToUserUserDto(appUser);
    }
}
