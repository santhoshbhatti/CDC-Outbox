package com.infinite.cdcoutbox.routing;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import com.infinite.cdcoutbox.datasources.DefaultDatasourceBuilderService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CustomRoutingDataSource extends AbstractRoutingDataSource {
	
	private DefaultDatasourceBuilderService defaultDatasourceBuilder;
	
	public CustomRoutingDataSource(DefaultDatasourceBuilderService defaultDatasourceBuilder) {
		this.defaultDatasourceBuilder = defaultDatasourceBuilder;
	}
	
	
	//http://localhost:8080/outbox/{tenantId}
	@Override
	protected Object determineCurrentLookupKey() {
		String tenantID = null;
		if (RequestContextHolder.getRequestAttributes() != null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
			Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			
			if (pathVariables != null) {
				tenantID = (String) pathVariables.get("tenantID");
			} else {
				tenantID = "default";
			}
		}else {
			tenantID = "default";
		}
		return tenantID;
	}
	
	protected DataSource determineTargetDataSource() {
		DataSource datasource = null;
		var lookupKey = this.determineCurrentLookupKey();
		try {
			datasource = super.determineTargetDataSource();
		}catch(IllegalStateException ie) {
			log.info("the datasource is not found in the first parse");
		}
		//Retrying the datasource lookup for dynamically created datasources
		if (datasource == null) {
			datasource = (DataSource)defaultDatasourceBuilder.getDataSourceMap().get(lookupKey);
		}
		if (datasource == null) {
			throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
		}
		return datasource;
	}

}
