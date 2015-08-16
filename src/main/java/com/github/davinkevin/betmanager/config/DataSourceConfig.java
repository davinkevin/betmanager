package com.github.davinkevin.betmanager.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by kevin on 11/04/15
 */
@Configuration
@ConditionalOnExpression("'${spring.datasource.url}'.contains(':h2:tcp://')")
class DataSourceConfig {

    @Autowired private DataSourceProperties properties;

    // H2 Database :
    public static final String[] PARAMETER_H2_SERVER = new String[]{"-tcp", "-tcpAllowOthers", "-tcpPort", "9999"};

    @Bean
    public DataSource primaryDataSource() throws SQLException {
        DataSourceBuilder factory = DataSourceBuilder
                .create(this.properties.getClassLoader())
                .driverClassName(this.properties.getDriverClassName())
                .url(this.properties.getUrl())
                .username(this.properties.getUsername())
                .password(this.properties.getPassword());

        if (properties.getUrl().contains(":h2:tcp://"))
            h2Server();

        return factory.build();
    }

    @Lazy
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer(PARAMETER_H2_SERVER);
    }
}