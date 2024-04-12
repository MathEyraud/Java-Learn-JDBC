package com.mycompany.tennis.core;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceProvider {
	
	private static BasicDataSource singleDataSource;
	
	private static final String URL_MYSQL 	= "jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
    private static final String USER_MYSQL	= "ADMIN";
    private static final String PASSWORD	= "Password";
	
	public static DataSource getSingleDataSourceInstance() {
		
		if (singleDataSource==null) {
			singleDataSource = new BasicDataSource();
			singleDataSource.setUrl(URL_MYSQL);
			singleDataSource.setUsername(USER_MYSQL);
			singleDataSource.setPassword(PASSWORD);
		}
		
		return singleDataSource;
	}
}
