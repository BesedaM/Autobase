package by.epam.javatraining.beseda.webproject.dao.entitydao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import by.epam.javatraining.beseda.webproject.config.EnumConfig;
import by.epam.javatraining.beseda.webproject.config.ResultSetExtractorConfig;
import by.epam.javatraining.beseda.webproject.config.RowMapperConfig;
import by.epam.javatraining.beseda.webproject.config.TestConfig;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class,classes= {TestConfig.class})
public class EnumDAOTest {

	@Autowired
	@Qualifier("userRoleMap")
	private ReversalHashMap<Integer, String> userMap;
	
	@Autowired
	@Qualifier("requestStatusMap")
	private ReversalHashMap<Integer, String> requestStatusMap;
	
	@Test
	public void testDriver() {
		String driver = "driver";
		assertTrue(userMap.containsValue(driver));
	}
	
	@Test
	public void testRequestStatus() {
		String status = "принята";
		assertTrue(requestStatusMap.containsValue(status));
	}
	
	

}
