package kz.mircella.mircella_electronic_shop.util;

import kz.mircella.mircella_electronic_shop.entity.user.User;
import kz.mircella.mircella_electronic_shop.entity.user.UserDto;
import kz.mircella.mircella_electronic_shop.entity.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class WebUtil {

    @Autowired
    private static UserMapper mapper;

    public static UserDto getAppUserInfo(User user){
        return mapper.mapAppUserToUserUserDto(user);
    }

    public static UserDto getSecurityUserInfo(String username){
        String name = username;
        UserDto userDto = mapper.mapSecurityUserToUserDto(name);
        return userDto;
    }
}
