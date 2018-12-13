package cn.albumen.library.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author Albumen
 */
@Configuration
public class SessionFactoryConfiguration {
    @Autowired
    DataSource dataSource;

    private static String configLocation = "classpath:mybatis.xml";
    private static String typeAliasesPackage = "cn.albumen.library.bean";
    private static String mapperLocations = "classpath:mapper/*.xml";

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(pathMatchingResourcePatternResolver.getResource(configLocation));
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(mapperLocations));
        return sqlSessionFactoryBean;
    }
}
