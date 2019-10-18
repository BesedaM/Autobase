package by.epam.javatraining.beseda.webproject.config;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
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
import by.epam.javatraining.beseda.webproject.util.TestDatabaseConfigure;

@Configuration
@ComponentScan({ "by.epam.javatraining.beseda.webproject.dao", 
	"by.epam.javatraining.beseda.webproject.service", "by.epam.javatraining.beseda.webproject.util" })
public class TestConfig {

	private Logger log = Logger.getLogger(ERROR_LOGGER);
	private String CREATE_DB_SCRIPT = "db_dump.sql";
	private String ENCODING = "UTF-8";

	@Bean
	public ClassLoader getClassLoader() {
		return ClassLoader.getSystemClassLoader();
	}

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

	@Bean(name = "enumDAO")
	public EnumDAO getEnumDAO() {

		try (Connection connection = getDataSource().getConnection()) {
			File dbScript = new File(getClassLoader().getResource(CREATE_DB_SCRIPT).getFile());
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(dbScript), ENCODING));
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				log.error(e);
			}
			ScriptRunner scriptRunner = new ScriptRunner(connection);
			scriptRunner.runScript(br);
		} catch (SQLException e) {
			log.error(e);
		}
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

	@Bean
	public TestDatabaseConfigure getDatabaseConfigure() {
		return new TestDatabaseConfigure();
	}

}
