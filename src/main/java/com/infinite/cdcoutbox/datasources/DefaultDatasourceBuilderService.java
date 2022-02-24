package com.infinite.cdcoutbox.datasources;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.infinite.cdcoutbox.vos.DataSourceProperties;

@Service
public class DefaultDatasourceBuilderService {
	
	@Value("${default.db.driver}")
	String driver;
	
	@Value("${default.db.dburl}")
	String dbUrl;
	
	@Value("${default.db.username}")
	String username;
	
	@Value("${default.db.password}")
	String password;
	
	private final Map<Object, Object> dataSourceMap = new HashMap<>();
	
	public  Map<Object, Object> getDataSourceMap() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSourceMap.put("default", dataSource);
        
        return dataSourceMap;
    }
	
	public DataSourceProperties buildCustomDataSource(DataSourceProperties dataSourceProperties){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriver());
        dataSource.setUrl(dataSourceProperties.getDbUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSourceMap.put(dataSourceProperties.getTenantId(), dataSource);
		return dataSourceProperties;
		
	}
	
	public DataSource getDataSource(String tenantId) {
		if (dataSourceMap.containsKey(tenantId)) {
			return ((DataSource)dataSourceMap.get(tenantId));
		}
		
		throw new ObjectNotFoundException(tenantId, tenantId);
	}
	
	public Map<Object, Object> getDatasourceMap(){
		return dataSourceMap;
	}
	
}
