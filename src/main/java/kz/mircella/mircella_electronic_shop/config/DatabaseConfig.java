package kz.mircella.mircella_electronic_shop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "kz.mircella.mircella_electronic_shop")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "kz.mircella.mircella_electronic_shop")
@EnableWebMvc
public class DatabaseConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hbm2ddlAuto;
    @Value("${spring.datasource.tomcat.max-wait}")
    private String maxWait;
    @Value("${spring.datasource.tomcat.max-idle}")
    private String maxIdle;
    @Value("${spring.datasource.tomcat.min-idle}")
    private String minIdle;
    @Value("${spring.datasource.tomcat.max-active}")
    private String maxActive;
    @Value("${spring.datasource.tomcat.time-between-eviction-runs-millis}")
    private String timeBetweenEviction;
    @Value("${spring.datasource.tomcat.min-evictable-idle-time-millis}")
    private String minEvictableIdle;
    @Value("${spring.datasource.tomcat.initial-size}")
    private String initialSize;
    @Value("${spring.datasource.tomcat.validation-query}")
    private String validationQuery;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("kz.mircella.mircella_electronic_shop");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;

    }

    @Bean(name = "datasource",destroyMethod = "")
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(Long.valueOf(timeBetweenEviction));
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(minEvictableIdle));
        dataSource.setInitialSize(Integer.valueOf(initialSize));
        dataSource.setMaxIdle(Integer.valueOf(maxIdle));
        dataSource.setMinIdle(Integer.valueOf(minIdle));
        dataSource.setMaxWaitMillis(Long.valueOf(maxWait));
        return dataSource;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return jpaTransactionManager;
    }

}
