package in.ponram.util;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionUtil {

	private ConnectionUtil() {
		//default Constructor
	}
	private static final String DRIVER_CLASS = System.getenv("spring.datasource.driver-class-name");
	private static final String DB_URL = System.getenv("spring.datasource.url");
	private static final String DB_USERNAME = System.getenv("spring.datasource.username");
	private static final String DB_PASSWORD = System.getenv("spring.datasource.password");
	
	public static DataSource getConnection() {
		
		BasicDataSource db = new BasicDataSource();
		db.setDriverClassName(DRIVER_CLASS);
		db.setUrl(DB_URL);
		db.setUsername(DB_USERNAME);
		db.setPassword(DB_PASSWORD);
		return db;
	}
}
