package com.infinite.cdcoutbox.vos;

import lombok.Data;

@Data
public class DataSourceProperties {

	private String tenantId;

	private String driver;

	private String dbUrl;

	private String username;

	private String password;

}
