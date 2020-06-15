package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.example.demo.config.DatabaseConfig;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
@Bean
public class LoginDaoImpl implements LoginDAO {
    @Inject
    DatabaseConfig databaseConfig;
    
    public void setDatabaseConfig(DatabaseConfig dconfig){
        this.databaseConfig= dconfig;
    }

    public JdbcTemplate getJdbctemplate() {
        return new JdbcTemplate(databaseConfig.getDataSource());
    }

    @Override
    public String getIFrameUrlDAO(int iframeID) {
        List<String> urlResult=new ArrayList<String>();
       try {
           
           urlResult= getJdbctemplate().query("select \"iframeURL\" from tb_iframe_details where \"iframeID\"=?", new  Object[]{iframeID}, new RowMapper<String>(){

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                String iframeURL=null;
                iframeURL=rs.getString("iframeURL");
				return iframeURL;
			}});

       } catch (Exception e) {
           e.printStackTrace();
       }
        return urlResult.get(0);
    }
    
}