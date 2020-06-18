package com.example.demo.config;

import javax.sql.DataSource;

import com.blade.Blade;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Order;
import com.blade.loader.BladeLoader;

import org.apache.commons.dbcp2.BasicDataSource;

@Bean
@Order(1)
public class DatabaseConfig implements BladeLoader {
    private BasicDataSource dataSource;
    @Override
    public void load(Blade blade) {
        this.dataSource = new BasicDataSource();
        dataSource.setDriverClassName(blade.environment().getOrNull("spring.datasource.driver-class-name"));
        dataSource.setUrl(blade.environment().getOrNull("spring.datasource.url"));
        dataSource.setUsername(blade.environment().getOrNull("spring.datasource.username"));
        dataSource.setPassword(blade.environment().getOrNull("spring.datasource.password"));

   

    }
    public DataSource getDataSource(){
        return this.dataSource;
    }
    //to be called from testing class
    public void setDataSource(DataSource ds){
         this.dataSource= (BasicDataSource) ds;
    }
    
}