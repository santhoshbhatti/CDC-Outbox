package com.infinite.cdcoutbox;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.infinite.cdcoutbox.datasources.DefaultDatasourceBuilderService;
import com.infinite.cdcoutbox.routing.CustomRoutingDataSource;
@Configuration
public class DataSourceConfig {
	
	
	private DefaultDatasourceBuilderService defaultDatasourceBuilder;

	@Autowired
	public DataSourceConfig(DefaultDatasourceBuilderService defaultDatasourceBuilder) {
		this.defaultDatasourceBuilder = defaultDatasourceBuilder;
	}
	
	@Bean
	public DataSource dataSource() {
		CustomRoutingDataSource customDataSource = new CustomRoutingDataSource(defaultDatasourceBuilder);
		customDataSource.setTargetDataSources(defaultDatasourceBuilder.getDataSourceMap());
		return customDataSource;
	}

}
