package kz.mircella.mircella_electronic_shop.service;

import kz.mircella.mircella_electronic_shop.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.getUserByNameWithEncrytedPassword(name);
        if(user==null){
            throw new RuntimeException("Not found user with name "+name);
        }
        String userName = user.getName();
        String password = user.getPassword();
        String roleName = userService.getUserRole(name);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        authorityList.add(authority);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userName, password, authorityList);
        return userDetails;
    }
}
