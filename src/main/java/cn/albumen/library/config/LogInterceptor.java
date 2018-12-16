package cn.albumen.library.config;

import cn.albumen.library.annotation.ControllerLog;
import cn.albumen.library.bean.Log;
import cn.albumen.library.constant.HttpConst;
import cn.albumen.library.dao.LogDao;
import cn.albumen.library.util.NetworkUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Albumen
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    LogDao logDao;

    public LogInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        if (handler instanceof HandlerMethod) {
            try {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                ControllerLog annotation = ((HandlerMethod) handler).getMethodAnnotation(ControllerLog.class);
                if (annotation != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Action: ");
                    stringBuilder.append(annotation.description());

                    stringBuilder.append(" Params: ");
                    Integer user;
                    if (request.getMethod().compareToIgnoreCase(HttpConst.POST) == 0) {
                        /**
                         * POST
                         */
                        RequestWrapper myRequestWrapper = new RequestWrapper(request);
                        String body = myRequestWrapper.getBody();
                        JSONObject jsonObject = new JSONObject(body);
                        try {
                            user = jsonObject.getInt("loginUserId");
                        } catch (JSONException exception) {
                            user = 0;
                        }
                        Map<String, Object> jsonMap = jsonObject.toMap();
                        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
                            stringBuilder.append("\"" + entry.getKey() + "\": ");
                            stringBuilder.append("\"" + entry.getValue() + "\", ");
                        }
                        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
                    } else {
                        /**
                         * GET
                         */
                        user = Integer.valueOf(request.getParameter("loginUserId"));
                        Map<String, String[]> parameterMap = request.getParameterMap();
                        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                            stringBuilder.append("\"" + entry.getKey() + "\": ");
                            for (String value : entry.getValue()) {
                                stringBuilder.append("\"" + value + "\", ");
                            }
                            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                            stringBuilder.append("; ");
                        }
                        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
                    }
                    Log log = new Log();
                    log.setUser(user);
                    log.setContent(stringBuilder.toString());
                    log.setIp(NetworkUtil.getIpAddress(request));
                    log.setLevel(annotation.level());
                    logDao.insert(log);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
