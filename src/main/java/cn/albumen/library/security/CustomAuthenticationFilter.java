package cn.albumen.library.security;

import cn.albumen.library.constant.HttpConst;
import cn.albumen.library.constant.PageCodeEnum;
import cn.albumen.library.service.impl.GrantedAuthorityImpl;
import cn.albumen.library.util.JwtUtil;
import cn.albumen.library.util.PageCodeUtil;
import cn.albumen.library.util.RedisUtil;
import cn.albumen.library.util.ServletUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Albumen
 */
public class CustomAuthenticationFilter extends BasicAuthenticationFilter {
    private RedisUtil redisUtil;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, RedisUtil redisUtil) {
        super(authenticationManager);
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getHeader(HttpConst.AUTHORIZATION) != null && request.getHeader(HttpConst.AUTHORIZATION).startsWith(HttpConst.AUTHORIZATION_PREFIX)) {

            String token = request.getHeader(HttpConst.AUTHORIZATION);

            String id = ServletUtil.requestGetId(request);
            if (id != null && !id.isEmpty() && token != null && !token.isEmpty()) {
                String tokenRedis = redisUtil.get(id);
                if (tokenRedis != null && tokenRedis.compareTo(token.substring(HttpConst.AUTHORIZATION_PREFIX.length())) == 0) {
                    String[] ret = JwtUtil.verifyWithPermission(token.substring(HttpConst.AUTHORIZATION_PREFIX.length()), id);
                    if (ret != null) {
                        //验证成功
                        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                        for (String permission : ret) {
                            authorities.add(new GrantedAuthorityImpl(permission));
                        }

                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(id, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
            //验证失败
            PageCodeUtil.printCode(response, PageCodeEnum.NOT_LOGIN);
        } else {
            //验证失败
            PageCodeUtil.printCode(response, PageCodeEnum.NOT_LOGIN);
        }
    }
}
