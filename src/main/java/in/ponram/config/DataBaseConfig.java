package in.ponram.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import in.ponram.util.ConnectionUtil;

@Configuration
public class DataBaseConfig {

	@Bean
	public JdbcTemplate jdbcTemplate() {
		DataSource jdbcTemplate = ConnectionUtil.getConnection();
		return new JdbcTemplate(jdbcTemplate);
	}
 
}
