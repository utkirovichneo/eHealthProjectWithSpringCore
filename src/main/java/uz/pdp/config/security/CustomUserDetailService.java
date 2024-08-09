package uz.pdp.config.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.dao.auth.AuthUserDao;
import uz.pdp.domains.auth.AuthUser;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private AuthUserDao authUserDao;

    public CustomUserDetailService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserDao.getByUsername(username);
        if (authUser == null) {
            throw new UsernameNotFoundException(username);
        }
        else{
            System.err.println("authUser = " + authUser);
            return User.withUsername(authUser.getUsername())
                    .password(authUser.getPassword())
                    .build();
        }
    }
}