package com.wealthpark.purchasehistory;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.wealthpark.purchasehistory.constants.HibernateConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

	@Bean
	public DataSource dataSource() {
		
		HikariConfig hikariConfig = new HikariConfig();
		HikariDataSource dataSource=null;
		
		//Notes: Below Strings need to be set out of application in Properties file.
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/purchase-db?useSSL=false");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("root");
		
		hikariConfig.addDataSourceProperty(HibernateConstants.CACHE_SIZE, 250);
		hikariConfig.addDataSourceProperty(HibernateConstants.CACHE_LIMIT, 2048);
		dataSource=new HikariDataSource(hikariConfig);
		
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPersistenceUnitName(HibernateConstants.PERSISTANCE_UNIT_NAME);
		entityManagerFactory.setPackagesToScan(HibernateConstants.PACKAGE_TO_SCAN);
		
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
		
		Properties hibernateProperties= new Properties();
		hibernateProperties.put(HibernateConstants.HIBERNATE_SHOW_SQL, true);
		
		entityManagerFactory.setJpaProperties(hibernateProperties);
		return entityManagerFactory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManger(final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
}
