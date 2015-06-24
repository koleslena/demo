package ru.koleslena.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_IMPORT_FILES;

/**
 * Created by elenko on 23.06.15.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "ru.koleslena.demo.repository" })
@ComponentScan(basePackages = "ru.koleslena.demo", excludeFilters = {
        @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION)
})
public class AppConfig  {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                //.addScript("classpath:sql/schema.sql")
                //.addScript("classpath:sql/initDB.sql")
                .build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.HSQL);
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("ru.koleslena.demo.model");
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put(HBM2DDL_AUTO, "create-drop");
        properties.put(HBM2DDL_IMPORT_FILES, "sql/dbSeed.sql");
        return properties;
    }
}
