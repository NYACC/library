package cn.albumen.library.filter;

import cn.albumen.library.config.RequestWrapper;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * AuthenticationFilter that supports rest login(json login) and form login.
 *
 * @author chenhuanming
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String json = "application/json";
        //attempt Authentication when Content-Type is json
        if (request.getContentType() != null && request.getContentType().startsWith(json)) {
            //use jackson to deserialize json
            UsernamePasswordAuthenticationToken authRequest = null;

            try {
                RequestWrapper myRequestWrapper = null;
                myRequestWrapper = new RequestWrapper(request);
                String body = myRequestWrapper.getBody();
                if (!body.isEmpty()) {
                    JSONObject jsonObject = new JSONObject(body);
                    authRequest = new UsernamePasswordAuthenticationToken(
                            jsonObject.get("userNo"),
                            jsonObject.get("password"),
                            new ArrayList<>());
                } else {
                    authRequest = new UsernamePasswordAuthenticationToken("", "");
                }
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken("", "");
            }
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
        //transmit it to UsernamePasswordAuthenticationFilter
        else {
            return super.attemptAuthentication(request, response);
        }
    }

}
