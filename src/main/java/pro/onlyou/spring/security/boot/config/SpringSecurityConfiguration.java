package pro.onlyou.spring.security.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 配置验证管理其
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        //配置一个在内存中的具有ADMIN角色的admin用户
//        auth.inMemoryAuthentication()
//                //spring security 5 之后需要指定PasswordEncoder,否则报错There is no PasswordEncoder mapped for the id "null"
//                .passwordEncoder(encoder)
//                .withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
//
//        //添加一个用户检验角色
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder)
//                .withUser("jcx").password(encoder.encode("jcx")).roles("NORMAL");

        //使用自定义的UserDetailsService来管理
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

    /**
     * 配置HttpSecurity
     *  决定那些请求会被拦截
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //对/请求放行
                .antMatchers("/").permitAll()
                //对所有请求进行验证
                .anyRequest().authenticated()
                .and()
                //logout支持
                .logout().permitAll()
                .and()
                //formlogin支持
                .formLogin()
                .and()
                //取消csrf支持
                .csrf().disable();
    }

    /**
     * 配置WebSecurity
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
}
