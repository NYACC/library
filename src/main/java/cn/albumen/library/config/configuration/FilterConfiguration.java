package cn.albumen.library.config.configuration;

import cn.albumen.library.config.HttpServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认拦截器配置
 * 修复OutputSteam只能读取一次
 *
 * @author Albumen
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<HttpServletFilter> filterRegistrationBean() {
        FilterRegistrationBean<HttpServletFilter> registrationBean = new FilterRegistrationBean();
        HttpServletFilter httpServletFilter = new HttpServletFilter();

        registrationBean.setFilter(httpServletFilter);
        registrationBean.addUrlPatterns("/**");
        return registrationBean;
    }
}
