package by.epam.javatraining.beseda.webproject.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

import by.epam.javatraining.beseda.webproject.dao.entitydao.AddressDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.CarDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.CustomerDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.DriverDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.EnumDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.RequestDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.RouteDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.TaskDAO;
import by.epam.javatraining.beseda.webproject.dao.entitydao.UserDAO;
import by.epam.javatraining.beseda.webproject.service.entityservice.AddressService;
import by.epam.javatraining.beseda.webproject.service.entityservice.CarService;
import by.epam.javatraining.beseda.webproject.service.entityservice.CustomerService;
import by.epam.javatraining.beseda.webproject.service.entityservice.DriverService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RequestService;
import by.epam.javatraining.beseda.webproject.service.entityservice.RouteService;
import by.epam.javatraining.beseda.webproject.service.entityservice.TaskService;
import by.epam.javatraining.beseda.webproject.service.entityservice.UserService;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

@Configuration
@ComponentScan({ "by.epam.javatraining.beseda.webproject.dao", "by.epam.javatraining.beseda.webproject.service" })
public class TestConfig {

	private Logger log = Logger.getLogger(ERROR_LOGGER);

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}

	@Bean
	public DataSource getDataSource() {
		EmbeddedPostgres database = null;
		try {
			database = EmbeddedPostgres.builder().start();

		} catch (IOException e) {
			log.error(e);
		}
		DataSource dataSource = database.getPostgresDatabase();

		return dataSource;
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

	@Bean(name = "enumDAO")
	public EnumDAO getEnumDAO() {
		return new EnumDAO(getJdbcTemplate());
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
}
