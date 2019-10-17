package github.com.leonardowiest.java.persist.config;

import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_ENTITY_MANAGER_CONFIG_PREFIX;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_ENTITY_MANAGER_FACTORY_BEAN_NAME;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_PKG_REPOSITORY;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_TRANSACTION_MANAGER_BEAN_NAME;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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

}
