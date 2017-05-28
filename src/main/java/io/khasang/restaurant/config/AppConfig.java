package io.khasang.restaurant.config;

import io.khasang.restaurant.dao.DocumentDao;
import io.khasang.restaurant.dao.OrderDao;
import io.khasang.restaurant.dao.impl.DocumentDaoImpl;
import io.khasang.restaurant.dao.impl.OrderDaoImpl;
import io.khasang.restaurant.entity.Document;
import io.khasang.restaurant.entity.Order;
import io.khasang.restaurant.model.Cat;
import io.khasang.restaurant.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@PropertySource(value = {"classpath:util.properties"})
@PropertySource(value = {"classpath:auth.properties"})
public class AppConfig {
    @Autowired
    private Environment environment;

    @Bean
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource());
        jdbcImpl.setUsersByUsernameQuery(environment.getProperty("usersByQuery"));
        jdbcImpl.setAuthoritiesByUsernameQuery(environment.getProperty("rolesByQuery"));
        return jdbcImpl;
    }

    @Bean
    DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.postgresql.driver"));
        dataSource.setUrl(environment.getProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getProperty("jdbc.postgresql.username"));
        dataSource.setPassword(environment.getProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public DocumentDao documentDao(){
        return new DocumentDaoImpl(Document.class);
    }

    @Bean
    public OrderDao orderDao(){
        return new OrderDaoImpl(Order.class);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public Cat cat() {
        return new Cat(jdbcTemplate());
    }

    @Bean
    public Message message() {
        return new Message("hello bean!");
    }
}
