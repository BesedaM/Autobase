package by.epam.javatraining.beseda.webproject.integrationtests.databasecreator;

import java.util.HashMap;
import java.util.Map;

public class TableDataLoader {

	Map<String, String> sqlFile;

	{
		sqlFile = new HashMap<>();
		sqlFile.put(CopyQuery.ADDRESSES_SQL,DataFileName.ADDRESSES);
		
		
		sqlFile.put(CopyQuery.CAR_STATE_SQL,DataFileName.CAR_STATE);
		sqlFile.put(CopyQuery.CAR_STATUS_SQL,DataFileName.CAR_STATUS);
		sqlFile.put(CopyQuery.CAR_TYPE_SQL,DataFileName.CAR_TYPE);
		sqlFile.put(CopyQuery.CARS_SQL,DataFileName.CARS);
		sqlFile.put(CopyQuery.CARS_IN_ROUTES_SQL,DataFileName.CARS_IN_ROUTES);
		sqlFile.put(CopyQuery.CUSTOMER_TYPE_SQL,DataFileName.CUSTOMER_TYPE);
		sqlFile.put(CopyQuery.CUSTOMERS_SQL,DataFileName.CUSTOMERS);
		sqlFile.put(CopyQuery.DRIVERS_SQL,DataFileName.DRIVERS);
		sqlFile.put(CopyQuery.REQUEST_STATUS_SQL,DataFileName.REQUEST_STATUS);
		sqlFile.put(CopyQuery.REQUESTS_SQL,DataFileName.REQUESTS);
		sqlFile.put(CopyQuery.ROLE_SQL,DataFileName.ROLE);
		sqlFile.put(CopyQuery.ROUTE_STATUS_SQL,DataFileName.ROUTE_STATUS);
		sqlFile.put(CopyQuery.ROUTES_SQL,DataFileName.ROUTES);
		sqlFile.put(CopyQuery.TASKS_SQL,DataFileName.TASKS);
		sqlFile.put(CopyQuery.TRUCK_CAPACITY_SQL,DataFileName.TRUCK_CAPACITY);
		sqlFile.put(CopyQuery.USERS_SQL,DataFileName.USERS);
		
	}
	
}
