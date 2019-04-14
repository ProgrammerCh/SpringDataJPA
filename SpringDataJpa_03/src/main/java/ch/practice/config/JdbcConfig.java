package ch.practice.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@PropertySource(value = "classpath:jdbc.properties")
public class JdbcConfig {
    @Value("${hibernate.connection.driverClassName}")
    private String driverClassName;
    @Value("${hibernate.connection.url}")
    private String url;
    @Value("${hibernate.connection.username}")
    private String username;
    @Value("${hibernate.connection.password}")
    private String password;

    @Bean(name ="dataSource")
    public DataSource getDataSource(){

        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setDriverClassName(driverClassName);
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);

        return hikariDataSource;
    }


}
