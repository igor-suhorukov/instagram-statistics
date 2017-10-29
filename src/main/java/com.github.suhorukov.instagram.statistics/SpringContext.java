package com.github.suhorukov.instagram.statistics;

import com.github.igorsuhorukov.postgresql.IPostgresqlService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan("com.github.igorsuhorukov.postgresql")
public class SpringContext {
    @Autowired
    private IPostgresqlService postgresqlService;

    @Bean(name = "postgresPort")
    public int string(){
        return 9175;
    }

    @Bean
    public DataSource getDataSource(){
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl(postgresqlService.getJdbcConnectionUrl());
        configuration.setUsername(postgresqlService.getUsername());
        configuration.setPassword(postgresqlService.getPassword());
        configuration.setCatalog(postgresqlService.getDatabaseName());
        return new HikariDataSource(configuration);
    }
}
