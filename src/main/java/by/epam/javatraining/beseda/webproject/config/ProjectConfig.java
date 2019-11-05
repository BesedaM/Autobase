package by.epam.javatraining.beseda.webproject.config;

import by.epam.javatraining.beseda.webproject.aop.LoggingAspect;
import by.epam.javatraining.beseda.webproject.dao.entitydao.*;
import by.epam.javatraining.beseda.webproject.service.PasswordHash;
import by.epam.javatraining.beseda.webproject.service.entitybuilder.RouteBuilder;
import by.epam.javatraining.beseda.webproject.service.entityservice.*;
import by.epam.javatraining.beseda.webproject.service.processors.CarsDataProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.CustomerProcessor;
import by.epam.javatraining.beseda.webproject.service.processors.DriverProcessor;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

import static by.epam.javatraining.beseda.webproject.util.DatabaseProperties.*;

@Configuration
@ComponentScan({"by.epam.javatraining.beseda.webproject.dao", "by.epam.javatraining.beseda.webproject.service",
        "by.epam.javatraining.beseda.webproject.controller"})
@Import({RowMapperConfig.class, ResultSetExtractorConfig.class, EnumConfig.class})
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
    public RequestService getRequestService() {
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
    public PasswordHash getPasswordHash() {
        return new PasswordHash();
    }

}
