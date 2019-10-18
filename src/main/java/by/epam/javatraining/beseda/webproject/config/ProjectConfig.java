package by.epam.javatraining.beseda.webproject.config;

import static by.epam.javatraining.beseda.webproject.util.DatabaseProperties.DATABASE_PASSWORD;
import static by.epam.javatraining.beseda.webproject.util.DatabaseProperties.DATABASE_URL;
import static by.epam.javatraining.beseda.webproject.util.DatabaseProperties.DATABASE_USER;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import by.epam.javatraining.beseda.webproject.dao.entitydao.AddressDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.CarDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.CustomerDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.DriverDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.EnumDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.RequestDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.TaskDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.service.EnumMap;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.DriverService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;
import by.epam.javatraining.beseda.webproject.service.processors.CarsDataProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.CustomerProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.DriverProcessor;

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

	@Bean(name = "enumDAO")
	public EnumDAO getEnumDAO() {
		return new EnumDAO(getJdbcTemplate());
	}
	
	@Bean(name = "userDAO")
	public UserDAO getUserDAO() {
		return new UserDAO(getJdbcTemplate());
	}

	@Bean(name = "customerDAO")
	public CustomerDAO getCustomerDAO() {
		return new CustomerDAO(getJdbcTemplate());
	}

	@Bean(name = "driverDAO")
	public DriverDAO getDriverDAO() {
		return new DriverDAO(getJdbcTemplate());
	}

	@Bean(name = "addressDAO")
	public AddressDAO getAddressDAO() {
		return new AddressDAO(getJdbcTemplate());
	}

	@Bean(name = "carDAO")
	public CarDAO getCarDAO() {
		return new CarDAO(getJdbcTemplate());
	}

	@Bean(name = "taskDAO")
	public TaskDAO getTaskDAO() {
		return new TaskDAO(getJdbcTemplate());
	}

	@Bean(name = "routeDAO")
	public RouteDAO getRouteDAO() {
		return new RouteDAO(getJdbcTemplate());
	}

	@Bean(name = "requestDAO")
	public RequestDAO getRequestDAO() {
		return new RequestDAO(getJdbcTemplate());
	}

	@Bean
	public UserService getUserService() {
		return new UserService();
	}

	@Bean
	public CustomerService getCustomerService() {
		return new CustomerService();
	}

	@Bean
	public DriverService getDriverService() {
		return new DriverService();
	}
	
	@Bean
	public AddressService getAddressService() {
		return new AddressService();
	}
	
	@Bean
	public CarService getCarService() {
		return new CarService();
	}
	
	@Bean
	public TaskService getTaskService() {
		return new TaskService();
	}
	
	@Bean
	public RouteService getRouteService() {
		return new RouteService();
	}
	
	@Bean
	public RequestService getRequestservice() {
		return new RequestService();
	}
	
	@Bean
	public RouteBuilder getRouteBuilder() {
		return new RouteBuilder();
	}
	
	@Bean
	public CarsDataProcessor getCarsDataProcessor() {
		return new CarsDataProcessor();
	}
	
	@Bean
	public CustomerProcessor getCustomerProcessor() {
		return new CustomerProcessor();
	}
	
	@Bean
	public DriverProcessor getDriverProcesor() {
		return new DriverProcessor();
	}
}
