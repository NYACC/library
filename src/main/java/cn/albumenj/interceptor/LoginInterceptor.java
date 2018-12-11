package cn.albumenj.interceptor;

import cn.albumenj.constant.PageCodeEnum;
import cn.albumenj.dto.PageCodeDto;
import cn.albumenj.filter.RequestWrapper;
import cn.albumenj.util.Jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Albumen
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestWrapper myRequestWrapper = new RequestWrapper((HttpServletRequest) request);
        String body = myRequestWrapper.getBody();
        String token = request.getHeader("Authorization");

        /**
         * TODO:Redis验证
         */
        String id;
        if (!body.isEmpty()) {
            JSONObject jsonObject = new JSONObject(body);
            Integer bodyId = jsonObject.getInt("loginedUserId");
            id = bodyId.toString();
        } else {
            id = request.getParameter("loginedUserId");
        }
        if (id != null && !id.isEmpty() && token != null && !token.isEmpty()) {
            boolean ret = Jwt.verify(token, id);
            if (ret) {
                /**
                 * 验证成功
                 */
                return true;
            }
        }
        /**
         * 验证失败
         */
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new PageCodeDto(PageCodeEnum.NOT_LOGIN));
        writer.print(json);
        writer.close();
        response.flushBuffer();
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
