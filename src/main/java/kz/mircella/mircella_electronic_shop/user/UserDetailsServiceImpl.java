package kz.mircella.mircella_electronic_shop.user;

import kz.mircella.mircella_electronic_shop.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.getUserByName(name);
        if (user == null) {
            throw new RuntimeException("Not found entity with name " + name);
        }
        String userName = user.getUsername();
        String password = user.getPassword();
        String roleName = userService.getUserRole(name);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        authorityList.add(authority);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(userName, password, authorityList);
        return userDetails;
    }
}
