package kz.mircella.mircella_electronic_shop.user.entity;

import kz.mircella.mircella_electronic_shop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private UserService userService;

    public UserDto mapAppUserToUserUserDto(User user){
        return UserDto
                .builder()
                .userName(user.getUsername())
                .userEmail(user.getEmail())
                .userPassword(user.getPassword())
                .userCard(user.getCard())
                .userRole(user.getRole())
                .photoPath(user.getPhotoPath())
                .build();
    }

    public UserDto mapSecurityUserToUserDto(String userName){
        User appUser = userService.getUserByName(userName);
        return mapAppUserToUserUserDto(appUser);
    }
}
