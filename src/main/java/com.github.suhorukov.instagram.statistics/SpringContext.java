package com.github.suhorukov.instagram.statistics;

import com.github.igorsuhorukov.postgresql.IPostgresqlService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan({"com.github.igorsuhorukov.postgresql",
                "com.github.suhorukov.instagram.statistics.dao"})
@EntityScan("me.postaddict.instagram.scraper.model")
public class SpringContext {
    @Autowired
    private IPostgresqlService postgresqlService;

    @Bean
    public int postgresPort(){
        return 9175;
    }

    @Bean
    public String postgresDatabaseStoragePath(){
        return "";
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
