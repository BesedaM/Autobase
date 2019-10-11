package by.epam.javatraining.beseda.webproject.config;

import static by.epam.javatraining.beseda.webproject.connectionpool.DatabaseProperties.DATABASE_PASSWORD;
import static by.epam.javatraining.beseda.webproject.connectionpool.DatabaseProperties.DATABASE_URL;
import static by.epam.javatraining.beseda.webproject.connectionpool.DatabaseProperties.DATABASE_USER;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import by.epam.javatraining.beseda.webproject.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;

@Configuration
@ComponentScan({ "by.epam.javatraining.beseda.webproject.dao", "by.epam.javatraining.beseda.webproject.service" })
public class ProjectConfig {

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USER);
		dataSource.setPassword(DATABASE_PASSWORD);
		dataSource.setDriverClassName("org.postgresql.Driver");
		return dataSource;
	}

	@Bean(name = "postgresqlUserDAO")
	public UserDAO getUserDAO() {
		return new UserDAO(getJdbcTemplate());
	}

	
	
	@Bean
	public UserService getUserService() {
		return new UserService();
	}
}
