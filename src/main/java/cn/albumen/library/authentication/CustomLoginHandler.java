package cn.albumen.library.authentication;

import cn.albumen.library.bean.Log;
import cn.albumen.library.constant.HttpConst;
import cn.albumen.library.constant.LevelConst;
import cn.albumen.library.constant.PageCodeEnum;
import cn.albumen.library.dao.LogDao;
import cn.albumen.library.util.Jwt;
import cn.albumen.library.util.NetworkUtil;
import cn.albumen.library.util.PageCodeUtil;
import cn.albumen.library.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Albumen
 */
@Component
public class CustomLoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    LogDao logDao;

    /**
     * Login Success
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // builder the token
        String token = null;
        try {
            token = Jwt.create(authentication.getName());
            redisUtil.set(authentication.getName(), token);
            // 登录成功后，返回token到header里面
            response.addHeader(HttpConst.AUTHORIZATION, HttpConst.AUTHORIZATION_PREFIX + token);
            PageCodeUtil.printCode(response, PageCodeEnum.LOGIN_SUCCESS);
            Log log = new Log();
            log.setUser(Integer.parseInt(authentication.getName()));
            log.setIp(NetworkUtil.getIpAddress(request));
            log.setContent("Action: Login Params: \"userNo\": \"" + authentication.getName() + "\" ");
            log.setLevel(LevelConst.CRITICAL);
            logDao.insert(log);
        } catch (Exception e) {
            try {
                PageCodeUtil.printCode(response, PageCodeEnum.SYSTEM_ERROR);
            } catch (Exception e1) {
                return;
            }
        }
    }

    /**
     * Login Failure
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        PageCodeUtil.printCode(response, PageCodeEnum.LOGIN_FAILED);
    }
}