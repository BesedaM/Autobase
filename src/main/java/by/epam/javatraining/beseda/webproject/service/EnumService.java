package by.epam.javatraining.beseda.webproject.service;

import java.util.HashMap;
import java.util.Map;

import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

import static by.epam.javatraining.beseda.webproject.dao.util.dataloader.DatabaseEnumLoader.*;
import static by.epam.javatraining.beseda.webproject.service.ServiceConstants.*;

/**
 * Class provides access for all the enumeration tables in the given database.
 * Enumerations are represented as HashMap objects.
 */
public class EnumService {

	private static Map<String, ReversalHashMap<Integer, String>> collection = new HashMap<>();

	private EnumService() {
	}

	static {
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
	public static Map<String, ReversalHashMap<Integer, String>> getEnumCollection() {
		return collection;
	}

}