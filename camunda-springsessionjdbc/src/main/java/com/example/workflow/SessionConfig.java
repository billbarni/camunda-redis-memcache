package com.example.workflow;

import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcHttpSession
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {

    @SpringSessionDataSource
    @Bean(name = "sessionBpmDataSource")
    @ConfigurationProperties(prefix = "datasource.session")
    public DataSource sessionBpmDataSource() {
        return DataSourceBuilder.create().build();

    }

    @Bean(name = "sessionBpmTransactionManager")
    public PlatformTransactionManager sessionBpmTransactionManager(@Qualifier("sessionBpmDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public InitializingBean sessionDatabasePopulatorExecutor(@Qualifier("sessionBpmDataSource") DataSource dataSource) {
        //DataSource parsingDataSource = DSL.using(dataSource, SQLDialect.POSTGRES).parsingDataSource();
        return () -> {
            try {
                DatabasePopulatorUtils.execute(sessionDatabasePopulator(), dataSource);
            } catch (Exception e) {
                e.printStackTrace();
                // TODO
                // The populator will fail
                // since it will execute the Spring Session script always.
                // Some form of treatment to check for an already present database should be made
            }
        };
    }

    @Bean
    public ResourceDatabasePopulator sessionDatabasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setSqlScriptEncoding("UTF-8");
        populator.addScript(new ClassPathResource("org/springframework/session/jdbc/schema-postgresql.sql"));
        return populator;
    }

}