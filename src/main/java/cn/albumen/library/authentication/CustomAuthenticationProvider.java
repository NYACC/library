package cn.albumen.library.authentication;

import cn.albumen.library.bean.UserSecurity;
import cn.albumen.library.service.UserSecurityService;
import cn.albumen.library.service.impl.GrantedAuthorityImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 自定义身份认证验证组件
 *
 * @author zhaoxinguo on 2017/9/12.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserSecurityService userSecurityService;

    public CustomAuthenticationProvider(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUserNo(name);
        userSecurity.setPassword(password);
        // 认证逻辑
        userSecurity = userSecurityService.login(userSecurity);
        if (null != userSecurity) {
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new GrantedAuthorityImpl(userSecurity.getPermission().toString()));
            // 生成令牌 这里令牌里面存入了:name,password,authorities, 当然你也可以放其他内容
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
            return auth;
        } else {
            throw new BadCredentialsException("Username or Password Error");
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
