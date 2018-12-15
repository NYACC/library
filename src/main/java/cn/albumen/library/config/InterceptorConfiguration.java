package cn.albumen.library.config;

import cn.albumen.library.spring.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 切面配置
 *
 * @author Albumen
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**");*/
    }
}
