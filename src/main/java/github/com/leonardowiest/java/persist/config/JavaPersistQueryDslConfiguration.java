package github.com.leonardowiest.java.persist.config;

import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_JPA_TEMPLATE;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_PERSISTENCE_UNIT_NAME;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_SQLQUERY_FACTORY;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;

@Configuration
@DependsOn({ JAVA_PERSIST_PERSISTENCE_UNIT_NAME, JAVA_PERSIST_JPA_TEMPLATE })
public class JavaPersistQueryDslConfiguration {

    private SQLTemplates sqlTemplates;

    public JavaPersistQueryDslConfiguration(@Qualifier(JAVA_PERSIST_JPA_TEMPLATE) final SQLTemplates sqlTemplates) {
        this.sqlTemplates = sqlTemplates;
    }

    @Bean
    public com.querydsl.sql.Configuration s1ServerVendaPdvInitConfiguration() {

        return new com.querydsl.sql.Configuration(sqlTemplates);
    }

    @Bean(JAVA_PERSIST_SQLQUERY_FACTORY)
    public SQLQueryFactory s1ServerPdvQueryFactory(@Qualifier(JAVA_PERSIST_PERSISTENCE_UNIT_NAME) final DataSource dataSource) {

        return new SQLQueryFactory(s1ServerVendaPdvInitConfiguration(), new SpringConnectionProvider(dataSource));
    }
}
