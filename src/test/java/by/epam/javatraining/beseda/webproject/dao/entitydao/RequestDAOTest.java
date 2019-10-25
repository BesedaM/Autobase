package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.TEST_LOGGER;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import by.epam.javatraining.beseda.webproject.config.EnumConfig;
import by.epam.javatraining.beseda.webproject.config.ResultSetExtractorConfig;
import by.epam.javatraining.beseda.webproject.config.RowMapperConfig;
import by.epam.javatraining.beseda.webproject.config.TestConfig;
import by.epam.javatraining.beseda.webproject.dao.exception.DAOLayerException;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.CustomerInterface;
import by.epam.javatraining.beseda.webproject.dao.interfacedao.RequestInterface;
import by.epam.javatraining.beseda.webproject.entity.Request;
import by.epam.javatraining.beseda.webproject.entity.user.Customer;
import by.epam.javatraining.beseda.webproject.util.TestDatabaseConfigure;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes= {TestConfig.class})
public class RequestDAOTest {

	@Autowired
	private RequestInterface requestDAO;
	
	@Autowired
	private CustomerInterface customerDAO;
	
	@Autowired
	private TestDatabaseConfigure dbc;
	
	private Logger log = Logger.getLogger(TEST_LOGGER);



	@Before
	public void fillData() {
		dbc.fillDatabase();
	}

	@After
	public void cleanData() {
		dbc.cleanDatabase();
	}

	@Test
	public void testSelectActiveCustomerRequests() {
		List<Request> requests = null;
		requests = requestDAO.selectActiveCustomerRequests(10);
		int[] result = new int[7];
		result[0] = requests.get(0).getId();
		result[1] = requests.get(1).getId();
		result[2] = requests.get(2).getId();
		result[3] = requests.get(3).getId();
		result[4] = requests.get(4).getId();
		result[5] = requests.get(5).getId();
		result[6] = requests.get(6).getId();
		assertArrayEquals(new int[] { 7, 9, 10, 12, 13, 14, 17 }, result);
	}

	@Test
	public void testUpdate() {
		try {
			Customer customer = customerDAO.getEntityById(10);
			Request req = new Request(customer, "some text");
			int id = requestDAO.add(req);
			req.setComment("another text");
			requestDAO.update(req);
			Request readRequest = requestDAO.getEntityById(id);
			assertNotEquals("some text", readRequest.getComment());
		} catch (DAOLayerException  e) {
			log.error(e);
		}
	}

	@Test
	public void testGetNewRequests() {
		List<Request> list = requestDAO.getNewRequests();
		assertEquals(5, list.size());
	}

	@Test
	public void testGetCurrentRequestsId() {
		List<Request> requests = requestDAO.getCurrentRequests();
		int[] result = new int[4];
		result[0] = requests.get(0).getId();
		result[1] = requests.get(1).getId();
		result[2] = requests.get(2).getId();
		result[3] = requests.get(3).getId();
		assertArrayEquals(new int[] { 6, 7, 8, 10 }, result);
	}

	@Test
	public void testGetFulfilledRequestsId() {
		List<Request> requests = requestDAO.getFulfilledRequests();
		int[] result = new int[5];
		result[0] = requests.get(0).getId();
		result[1] = requests.get(1).getId();
		result[2] = requests.get(2).getId();
		result[3] = requests.get(3).getId();
		result[4] = requests.get(4).getId();
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, result);
	}

	@Test
	public void testGetRejectedRequestsId() {
		List<Request> requests = requestDAO.getRejectedRequests();
		int[] result = new int[3];
		result[0] = requests.get(0).getId();
		result[1] = requests.get(1).getId();
		result[2] = requests.get(2).getId();
		assertArrayEquals(new int[] { 12, 14, 16 }, result);
	}

	@Test
	public void testNoRequestsIdFound() {
		dbc.truncateTable("requests");
		List<Request> requests = requestDAO.getRejectedRequests();
		assertEquals(0, requests.size());
	}

	@Test
	public void testNoNewRequests() {
		dbc.truncateTable("requests");
		List<Request> list = requestDAO.getNewRequests();
		assertEquals(0, list.size());
	}

}
