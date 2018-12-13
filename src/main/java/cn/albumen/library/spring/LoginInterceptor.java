package cn.albumen.library.spring;

import cn.albumen.library.constant.PageCodeEnum;
import cn.albumen.library.util.Jwt;
import cn.albumen.library.util.PageCodeUtil;
import cn.albumen.library.util.RedisUtil;
import org.apache.ibatis.plugin.Intercepts;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Albumen
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof DefaultServletHttpRequestHandler) {
            PageCodeUtil.error(response, PageCodeEnum.SYSTEM_ERROR);
            return false;
        }
        RequestWrapper myRequestWrapper = new RequestWrapper((HttpServletRequest) request);
        String body = myRequestWrapper.getBody();
        String token = request.getHeader("Authorization");

        String id;
        if (!body.isEmpty()) {
            JSONObject jsonObject = new JSONObject(body);
            Integer bodyId = jsonObject.getInt("loginedUserId");
            id = bodyId.toString();
        } else {
            id = request.getParameter("loginedUserId");
        }
        if (id != null && !id.isEmpty() && token != null && !token.isEmpty()) {
            String tokenRedis = redisUtil.get(id.toString());
            if(tokenRedis!= null && tokenRedis.compareTo(token)==0) {
                boolean ret = Jwt.verify(token, id);
                if (ret) {
                    /**
                     * 验证成功
                     */
                    return true;
                }
            }
        }
        /**
         * 验证失败
         */
        PageCodeUtil.error(response,PageCodeEnum.NOT_LOGIN);
        return false;

    }

    public LoginInterceptor() {
        super();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
