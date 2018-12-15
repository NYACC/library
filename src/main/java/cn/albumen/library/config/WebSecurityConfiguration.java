package cn.albumen.library.config;

import cn.albumen.library.authentication.CustomAuthenticationProvider;
import cn.albumen.library.authentication.CustomLoginHandler;
import cn.albumen.library.filter.CustomAuthenticationFilter;
import cn.albumen.library.filter.CustomUsernamePasswordAuthenticationFilter;
import cn.albumen.library.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置
 *
 * @author Albumen
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomLoginHandler customLoginHandler;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    RedisUtil redisUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .antMatchers("/usersecurity/login**").permitAll()
                .anyRequest().authenticated()
                .and().addFilter(new CustomAuthenticationFilter(authenticationManager(), redisUtil));

        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    CustomUsernamePasswordAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(customLoginHandler);
        filter.setAuthenticationFailureHandler(customLoginHandler);
        filter.setFilterProcessesUrl("/usersecurity/login");

        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
