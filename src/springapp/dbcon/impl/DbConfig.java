package springapp.dbcon.impl;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbConfig{
	
	@Bean
	public DataSource dataSource(){
		
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName("org.postgresql.Driver");
		dmds.setUrl(System.getenv("OPENSHIFT_POSTGRESQL_DB_MYURL"));
		dmds.setUsername(System.getenv("OPENSHIFT_POSTGRESQL_DB_USERNAME"));
		dmds.setPassword(System.getenv("OPENSHIFT_POSTGRESQL_DB_PASSWORD"));
		
		return dmds;
	}
}
