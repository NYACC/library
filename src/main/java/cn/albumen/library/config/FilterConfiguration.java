package cn.albumen.library.config;

import cn.albumen.library.spring.HttpServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Albumen
 */
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<HttpServletFilter> filterRegistrationBean() {
        FilterRegistrationBean < HttpServletFilter > registrationBean = new FilterRegistrationBean();
        HttpServletFilter httpServletFilter = new HttpServletFilter();

        registrationBean.setFilter(httpServletFilter);
        registrationBean.addUrlPatterns("/**");
        return registrationBean;
    }
}
