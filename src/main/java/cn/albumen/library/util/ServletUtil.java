package cn.albumen.library.util;

import cn.albumen.library.config.RequestWrapper;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Albumen
 */
public class ServletUtil {

    public static String requestGetId(HttpServletRequest request) throws IOException {
        RequestWrapper myRequestWrapper = new RequestWrapper(request);
        String body = myRequestWrapper.getBody();
        String id = null;
        if (!body.isEmpty()) {
            JSONObject jsonObject = new JSONObject(body);
            Integer bodyId = jsonObject.getInt("loginUserId");
            id = bodyId.toString();
        } else {
            id = request.getParameter("loginUserId");
        }
        return id;
    }
}
