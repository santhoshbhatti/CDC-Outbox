package com.infinite.cdcoutbox.datasource.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.cdcoutbox.datasources.DefaultDatasourceBuilderService;
import com.infinite.cdcoutbox.vos.DataSourceProperties;

@RestController
public class DataSourceController {

	@Autowired
	DefaultDatasourceBuilderService defaultDatasourceBuilderService; 
	
	@PostMapping("/v1/datasource")
	public DataSourceProperties createNewDataSource(@RequestBody DataSourceProperties dataSourceProperties) {
		return defaultDatasourceBuilderService.buildCustomDataSource(dataSourceProperties);
	}
	
	@GetMapping("/v1/datasource/{tenantId}")
	public boolean testDatasource(@PathVariable("tenantId") String tenantId) {
		DataSource dataSource = defaultDatasourceBuilderService.getDataSource(tenantId);
		boolean connectionSuccess = false;
		if (dataSource != null) {
			try {
				Connection con = dataSource.getConnection();
				connectionSuccess = true;
			} catch (SQLException e) {
				throw new RuntimeException(tenantId+": datasource not setup properly", e);
			}
		}
		
		return connectionSuccess;
	}
	
	
}
