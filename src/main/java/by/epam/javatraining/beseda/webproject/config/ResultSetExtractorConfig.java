package by.epam.javatraining.beseda.webproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import by.epam.javatraining.beseda.webproject.dao.resultsetextractor.AddressExtractor;
import by.epam.javatraining.beseda.webproject.dao.resultsetextractor.CarExtractor;
import by.epam.javatraining.beseda.webproject.dao.resultsetextractor.CustomerExtractor;
import by.epam.javatraining.beseda.webproject.dao.resultsetextractor.DriverExtractor;
import by.epam.javatraining.beseda.webproject.dao.resultsetextractor.RequestExtractor;
import by.epam.javatraining.beseda.webproject.dao.resultsetextractor.RouteExtractor;
import by.epam.javatraining.beseda.webproject.dao.resultsetextractor.UserExtractor;
import by.epam.javatraining.beseda.webproject.dao.resultsetextractor.TaskExtraсtor;


@Configuration
@ComponentScan({ "by.epam.javatraining.beseda.webproject.dao.resultsetextractor" })
public class ResultSetExtractorConfig {

	@Bean(name = "userExtractor")
	public UserExtractor getUserExtractor() {
		return new UserExtractor();
	}

	@Bean(name = "customerExtractor")
	public CustomerExtractor getCustomerExtractor() {
		return new CustomerExtractor();
	}

	@Bean(name = "driverExtractor")
	public DriverExtractor getDriverExtractor() {
		return new DriverExtractor();
	}

	@Bean(name = "addressExtractor")
	public AddressExtractor getAddressExtractor() {
		return new AddressExtractor();
	}

	@Bean(name = "carExtractor")
	public CarExtractor getCarExtractor() {
		return new CarExtractor();
	}

	@Bean(name = "requestExtractor")
	public RequestExtractor getRequestExtractor() {
		return new RequestExtractor();
	}

	@Bean(name = "routeExtractor")
	public RouteExtractor getRouteExtractor() {
		return new RouteExtractor();
	}

	@Bean(name = "taskExtractor")
	public TaskExtraсtor getTaskExtractor() {
		return new TaskExtraсtor();
	}
	
}
