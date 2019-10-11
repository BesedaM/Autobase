package by.epam.javatraining.beseda.webproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.epam.javatraining.beseda.webproject.dao.mapper.AddressMapper;
import by.epam.javatraining.beseda.webproject.dao.mapper.CarMapper;
import by.epam.javatraining.beseda.webproject.dao.mapper.CustomerMapper;
import by.epam.javatraining.beseda.webproject.dao.mapper.DriverMapper;
import by.epam.javatraining.beseda.webproject.dao.mapper.EnumElementMapper;
import by.epam.javatraining.beseda.webproject.dao.mapper.RequestMapper;
import by.epam.javatraining.beseda.webproject.dao.mapper.RouteMapper;
import by.epam.javatraining.beseda.webproject.dao.mapper.TaskMapper;
import by.epam.javatraining.beseda.webproject.dao.mapper.UserMapper;

@Configuration
@ComponentScan({ "by.epam.javatraining.beseda.webproject.dao.mapper" })
public class RowMapperConfig {

	@Bean(name = "userMapper")
	public UserMapper getUserMapper() {
		return new UserMapper();
	}

	@Bean(name = "customerMapper")
	public CustomerMapper getCustomerMapper() {
		return new CustomerMapper();
	}

	@Bean(name = "driverMapper")
	public DriverMapper getDriverMapper() {
		return new DriverMapper();
	}

	@Bean(name = "addressMapper")
	public AddressMapper getAddressMapper() {
		return new AddressMapper();
	}

	@Bean(name = "carMapper")
	public CarMapper getCarMapper() {
		return new CarMapper();
	}

	@Bean(name = "requestMapper")
	public RequestMapper getRequestMapper() {
		return new RequestMapper();
	}

	@Bean(name = "routeMapper")
	public RouteMapper getRouteMapper() {
		return new RouteMapper();
	}

	@Bean(name = "taskMapper")
	public TaskMapper getTaskMapper() {
		return new TaskMapper();
	}
	
	@Bean(name = "enumMapper")
	public EnumElementMapper getEnumMapper() {
		return new EnumElementMapper();
	}

}
