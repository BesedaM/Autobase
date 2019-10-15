package by.epam.javatraining.beseda.webproject.service;

import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.CAR_STATE_MAP;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.CAR_STATUS_MAP;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.CAR_TYPE_MAP;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.CUSTOMER_TYPE_MAP;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.REQUEST_STATUS_MAP;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.ROUTE_STATUS_MAP;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.TRUCK_CAPACITY_MAP;
import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.USER_ROLE_MAP;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.CAR_STATE;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.CAR_STATUS;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.CAR_TYPE;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.CUSTOMER_TYPE;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.REQUEST_STATUS;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.ROUTE_STATUS;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.TRUCK_CAPACITY;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.USER_ROLE;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

/**
 * Class provides access for all the enumeration tables in the given database.
 * Enumerations are represented as HashMap objects.
 */
@Service
public class EnumService {

	private Map<String, ReversalHashMap<Integer, String>> collection = new HashMap<>();

	public EnumService() {
		collection.put(USER_ROLE, USER_ROLE_MAP);
		collection.put(CUSTOMER_TYPE, CUSTOMER_TYPE_MAP);
		collection.put(TRUCK_CAPACITY, TRUCK_CAPACITY_MAP);
		collection.put(CAR_STATUS, CAR_STATUS_MAP);
		collection.put(CAR_STATE, CAR_STATE_MAP);
		collection.put(CAR_TYPE, CAR_TYPE_MAP);
		collection.put(ROUTE_STATUS, ROUTE_STATUS_MAP);
		collection.put(REQUEST_STATUS, REQUEST_STATUS_MAP);
	}


	/**
	 * Returns the object with all the enumeration collections.
	 *
	 * @return Map object containing all the enumerations of the database
	 */
	public Map<String, ReversalHashMap<Integer, String>> getEnumCollection() {
		return collection;
	}

}