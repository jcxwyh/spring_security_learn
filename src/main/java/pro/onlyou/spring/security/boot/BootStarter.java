package pro.onlyou.spring.security.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用角色验证注解，如@PreAuthorize()
public class BootStarter {

    public static void main(String[] args) {
        SpringApplication.run(BootStarter.class, args);
    }

}
