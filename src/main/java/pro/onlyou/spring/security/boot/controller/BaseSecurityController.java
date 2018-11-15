package pro.onlyou.spring.security.boot.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseSecurityController {

    @RequestMapping("")
    public String index(){
        return "hello /";
    }

    /**
     * 只需要登陆就可以
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        return "hello /hello";
    }

    /**
     * 验证角色
     *      @PreAuthorize()支持的参数：
     *              来自SecurityExpressionRoot
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_NORMAL')")//需要EnableGlobalMethodSecurity注解启用
    @PostAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_ADMIN')")
    @PreFilter("")
    @PostFilter("")
    @RequestMapping("/role")
    public String role(){
        return "hello /role";
    }

    /**
     * @PreAuthorize("#id>10")　验证传入的id大于10
     * @PreAuthorize("principal.username.equals(#username)") 验证当前用户username与传入username相同
     * @PreAuthorize("#user.username.startWith('user_')") 验证传入User的username以user_开头
     *
     * @PostAuthorize("returnObject%@==0") 验证返回参数对２取余为０【 returnObject 】
     *
     * @PreAuthorize可对传入参数或相应权限进行验证
     * @PostAuthorize可对返回（相应）数据进行验证（方法调用完成后）
     *
     * @param id
     * @return
     */
    @PreAuthorize("#id>10 and principal.username.equals(#username) and #user.username.startsWith('user_')")//需要EnableGlobalMethodSecurity注解启用
    @PostAuthorize("returnObject.startsWith('hello')")
    @RequestMapping("/expression")
    public String testSecurityExpression(String username, Integer id, User user){
        return "hello /expression";
    }

    /**@PreFilter和@PostFilter对集合类型参数或返回值进行验证
     *
     * ＠PreFilter
     *
     *
     * @return
     */
    @PreFilter("filterObject%2==0")
    @PostFilter("true")
    @RequestMapping("/filter")
    public String filter(List<Integer> ids){
        return "hello /expression";
    }



}
