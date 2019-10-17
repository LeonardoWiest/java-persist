package github.com.leonardowiest.java.persist.config;

import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_DATASOURCE_PROPERTIES;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_ENTITY_MANAGER_FACTORY_BEAN_NAME;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_PERSISTENCE_CONTEXT;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_PERSISTENCE_UNIT_NAME;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_PKG_DOMAIN;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_PKG_REPOSITORY;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_TRANSACTION_MANAGER_BEAN_NAME;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;

@Lazy
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        JAVA_PERSIST_PKG_REPOSITORY }, entityManagerFactoryRef = JAVA_PERSIST_ENTITY_MANAGER_FACTORY_BEAN_NAME, transactionManagerRef = JAVA_PERSIST_TRANSACTION_MANAGER_BEAN_NAME)
public class JavaPersistEntityManager {

    private EntityManagerBuilder entityManagerBuilder = new EntityManagerBuilder();

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.ddl-auto:none}")
    private String hibernateDdlAuto;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.show_sql:false}")
    private String hibernateShowSql;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.use_sql_comments:true}")
    private String hibernateUseSqlComments;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.format_sql:true}")
    private String hibernateFormatSql;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.enable_lazy_load_no_trans:true}")
    private String hibernateEnableLazyLoadNoTransactional;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.temp.use_jdbc_metadata_defaults:false}")
    private String hibernateUseJdbcMetaDataDefaults;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.query.plan_cache_max_size:16}")
    private String hibernateQueryPlanCacheMaxSize;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.query.plan_parameter_metadata_max_size:128}")
    private String hibernateQueryPlanParameterMetadataMaxSize;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.query.plan_cache_max_soft_references:1024}")
    private String hibernateQueryPlanCacheMaxSoftReferences;

    @Value("${" + JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".jpa.hibernate.query.plan_cache_max_strong_references:64}")
    private String hibernateQueryPlanCacheMaxStrongReferences;

    @Primary
    @Bean(name = JAVA_PERSIST_DATASOURCE_PROPERTIES)
    @ConfigurationProperties(prefix = JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX)
    @Validated
    public DataSourceProperties javaPersistDataSourceProperties() {

        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = JAVA_PERSIST_PERSISTENCE_UNIT_NAME)
    @ConfigurationProperties(prefix = JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX + ".hikari")
    @Validated
    public DataSource javaPersistDataSource() {

        return javaPersistDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @PersistenceContext(unitName = JAVA_PERSIST_PERSISTENCE_CONTEXT)
    @Bean(name = JAVA_PERSIST_ENTITY_MANAGER_FACTORY_BEAN_NAME)
    public LocalContainerEntityManagerFactoryBean javaPersistEntityManagerfactory(EntityManagerFactoryBuilder builder,
            @Qualifier(JAVA_PERSIST_PERSISTENCE_UNIT_NAME) DataSource ds) {

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = entityManagerBuilder.entityManagerFactoryBuilder(builder, ds,
                JAVA_PERSIST_PERSISTENCE_UNIT_NAME, JAVA_PERSIST_PKG_REPOSITORY, JAVA_PERSIST_PKG_DOMAIN);

        localContainerEntityManagerFactoryBean.setJpaProperties(getJpaProperties());

        return localContainerEntityManagerFactoryBean;
    }

    @Primary
    @Bean(name = JAVA_PERSIST_TRANSACTION_MANAGER_BEAN_NAME)
    public JpaTransactionManager javaPersistTransactionManager(
            @Qualifier(JAVA_PERSIST_ENTITY_MANAGER_FACTORY_BEAN_NAME) EntityManagerFactory factory) {

        return new JpaTransactionManager(factory);
    }

    private Properties getJpaProperties() {

        Properties properties = new Properties();

        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.ddl-auto", hibernateDdlAuto);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.use_sql_comments", hibernateUseSqlComments);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.enable_lazy_load_no_trans", hibernateEnableLazyLoadNoTransactional);
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", hibernateUseJdbcMetaDataDefaults);

        properties.put("hibernate.query.plan_cache_max_size", hibernateQueryPlanCacheMaxSize);
        properties.put("hibernate.query.plan_parameter_metadata_max_size", hibernateQueryPlanParameterMetadataMaxSize);
        properties.put("hibernate.query.plan_cache_max_soft_references", hibernateQueryPlanCacheMaxSoftReferences);
        properties.put("hibernate.query.plan_cache_max_strong_references", hibernateQueryPlanCacheMaxStrongReferences);

        return properties;
    }

}
