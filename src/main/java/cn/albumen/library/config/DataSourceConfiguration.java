package cn.albumen.library.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * C3P0 配置文件
 *
 * @author Albumen
 */
@Configuration
public class DataSourceConfiguration {
    @Value("${dataSource.driver}")
    String driver;
    @Value("${datasource.url}")
    String url;
    @Value("${datasource.user}")
    String user;
    @Value("${datasource.password}")
    String password;

    @Bean
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setIdleConnectionTestPeriod(60);
        comboPooledDataSource.setInitialPoolSize(5);
        comboPooledDataSource.setMaxIdleTime(60);
        comboPooledDataSource.setMaxPoolSize(10);
        comboPooledDataSource.setMinPoolSize(5);
        comboPooledDataSource.setMaxStatements(100);
        comboPooledDataSource.setMaxStatementsPerConnection(3);
        comboPooledDataSource.setPreferredTestQuery("select 1");
        comboPooledDataSource.setAcquireRetryAttempts(3);
        comboPooledDataSource.setAcquireRetryDelay(1000);
        comboPooledDataSource.setCheckoutTimeout(30000);
        return comboPooledDataSource;
    }
}
