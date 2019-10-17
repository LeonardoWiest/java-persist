package github.com.leonardowiest.java.persist.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class EntityManagerBuilder {

    public DataSource dataSourceBuilder() {

        return DataSourceBuilder.create().build();
    }

    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBuilder(EntityManagerFactoryBuilder builder, DataSource dataSource,
            String persistenceUnit, String... packagesToScan) {

        return builder.dataSource(dataSource).packages(packagesToScan).persistenceUnit(persistenceUnit).build();
    }

}
