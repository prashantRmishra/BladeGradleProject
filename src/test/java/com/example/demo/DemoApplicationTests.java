package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.config.DatabaseConfig;
import com.example.demo.config.PTransactionManagerConfig;
import com.example.demo.dao.LoginDaoImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

class DemoApplicationTests {
    static DatabaseConfig databaseConfig;
    static PTransactionManagerConfig pTransactionManagerConfig;
    static PlatformTransactionManager ptmDataSource;

    /**
 * This method has common operation that are executed before running the tests
 * 
*/
	@BeforeAll
    static void setup(){
        BasicDataSource bs = new BasicDataSource();
        bs.setDriverClassName("org.postgresql.Driver");
        bs.setUrl("jdbc:postgresql://localhost:5432/StargateSPA");
        bs.setUsername("postgres");
        bs.setPassword("password");
        databaseConfig= new DatabaseConfig();
        databaseConfig.setDataSource(bs);
        ptmDataSource= new DataSourceTransactionManager(databaseConfig.getDataSource());
        pTransactionManagerConfig = new PTransactionManagerConfig();
        pTransactionManagerConfig.setDataSource(ptmDataSource);
        
    }
    /**
     * This operation is executed after all the tests are excecuted
    */
    @AfterAll
    static void closeup(){
        databaseConfig=null;
        System.out.println("end process");
    }

    /*This test case is to retrive Iframe Url from db
    */
	@Test
	void testIFrameUrl() {
		LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
        loginDaoImpl.setDatabaseConfig(databaseConfig);
        String iframeUrl=null;
        iframeUrl=loginDaoImpl.getIFrameUrlDao(33212);
        System.out.println("Result from testing :"+iframeUrl);
        assertNotNull(iframeUrl,()->"getIframeUrlDao() should return url");
   
    }

    /*This test case is to check insert operation */
    @Test
    void testAddIFrameDetails(){
        boolean result;
        LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
        loginDaoImpl.setDatabaseConfig(databaseConfig);
        loginDaoImpl.setPlatTransactionMngrConfig(pTransactionManagerConfig);
        result=loginDaoImpl.setIFrameUrlDaoPlatTransacMnger(212,"http://drive.google.com",33212);
        System.out.println("insert operation returns:"+result);
        assertTrue(result);
        
    }
    /*This test case is to delete record form the database*/
    @Test
    void testDeleteRecord(){
        boolean result;
        LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
        loginDaoImpl.setDatabaseConfig(databaseConfig);
        loginDaoImpl.setPlatTransactionMngrConfig(pTransactionManagerConfig);
        result=loginDaoImpl.deleteRecord(32);
        assertTrue(result);
    }

}
