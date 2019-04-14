package ch.practice.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(value = {"ch.practice.dao"},transactionManagerRef ="transactionManager",entityManagerFactoryRef ="entityManagerFactory" )
@Import(value = JdbcConfig.class)
public class PersistentConfig {

    @Bean(value = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(@Qualifier(value ="jpaVendorAdapter" ) JpaVendorAdapter jpaVendorAdapter,@Qualifier(value = "dataSource")DataSource dataSource ){

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("ch.practice.pojo");
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        HashMap<String , Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.show_sql",true);
        jpaProperties.put("hibernate.hbm2ddl.auto","update");
        entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
        HibernatePersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
        entityManagerFactoryBean.setPersistenceProvider(persistenceProvider);
        HibernateJpaDialect hibernateJpaDialect = new HibernateJpaDialect();
        entityManagerFactoryBean.setJpaDialect(hibernateJpaDialect);
        return entityManagerFactoryBean;

    }

    @Bean(name = "jpaVendorAdapter")
    public HibernateJpaVendorAdapter getJpaVendorAdapter(){

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();


        adapter.setDatabase(Database.MYSQL);

        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");

        adapter.setGenerateDdl(true);

        return adapter;


    }

    @Bean(value = "transactionManager")
    public JpaTransactionManager getJpaTransactionManager(@Qualifier(value = "entityManagerFactory")EntityManagerFactory entityManagerFactory){

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }


}
