package github.com.leonardowiest.java.persist.config;

import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_JPA_TEMPLATE;
import static github.com.leonardowiest.java.persist.constants.JavaPersistConstants.JAVA_PERSIST_PERSISTENCE_UNIT_NAME;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLTemplates;

@Configuration
@DependsOn(JAVA_PERSIST_PERSISTENCE_UNIT_NAME)
public class JavaPersistTemplatesConfiguration {

    @Qualifier(JAVA_PERSIST_PERSISTENCE_UNIT_NAME)
    @Autowired
    @Lazy
    private DataSource dataSource;

    @Bean(JAVA_PERSIST_JPA_TEMPLATE)
    @Lazy
    public SQLTemplates getSQLTemplates() {

        return PostgreSQLTemplates.builder().build();
    }

}
