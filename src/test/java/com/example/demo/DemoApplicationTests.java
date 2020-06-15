package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.dao.LoginDaoImpl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DemoApplicationTests {
	static DatabaseConfig databaseConfig;
	@BeforeAll
    static void setup(){
        BasicDataSource bs = new BasicDataSource();
        bs.setDriverClassName("org.postgresql.Driver");
        bs.setUrl("jdbc:postgresql://localhost:5432/StargateSPA");
        bs.setUsername("postgres");
        bs.setPassword("password");
        databaseConfig= new DatabaseConfig();
        databaseConfig.setDataSource(bs);
        
    }
    @AfterAll
    static void closeup(){
        databaseConfig=null;
        System.out.println("end process");
    }

	@Test
	void testIframeUrl() {
		LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
        loginDaoImpl.setDatabaseConfig(databaseConfig);
        String iframeUrl=null;
        iframeUrl=loginDaoImpl.getIFrameUrlDAO(123);
        System.out.println("Result from testing :"+iframeUrl);
        assertNotNull(iframeUrl,()->"getIframeUrlDao() should return url");
   
	}

}
