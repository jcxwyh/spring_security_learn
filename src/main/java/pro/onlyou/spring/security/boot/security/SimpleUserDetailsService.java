package pro.onlyou.spring.security.boot.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义实现UserDetailsService
 * @author AaronJiang
 */
@Component//交给spring ioc管理
public class SimpleUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
