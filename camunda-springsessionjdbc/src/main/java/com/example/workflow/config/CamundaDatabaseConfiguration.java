package com.example.workflow.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CamundaDatabaseConfiguration {

    @Primary // TODO Remove the primary from here and define your own if you need to create your tables/entities
    @Bean(name = "camundaBpmDataSource")
    @ConfigurationProperties(prefix = "datasource.camunda")
    public DataSource businessDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary // TODO Remove the primary from here and define your own if you need to create your tables/entities
    @Bean(name = "camundaBpmTransactionManager")
    public PlatformTransactionManager camundaBpmTransactionManager(@Qualifier("camundaBpmDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}