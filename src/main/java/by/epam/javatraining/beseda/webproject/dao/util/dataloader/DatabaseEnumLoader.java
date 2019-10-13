package by.epam.javatraining.beseda.webproject.dao.util.dataloader;

import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CAR_STATE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CAR_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CAR_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_CUSTOMER_TYPE;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_REQUEST_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_ROUTE_STATUS;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_TRUCK_CAPACITY;
import static by.epam.javatraining.beseda.webproject.dao.util.databaseconstants.DBEnumTable.T_USER_ROLE;

import by.epam.javatraining.beseda.webproject.dao.entitydao.EnumDAO;
import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

public class DatabaseEnumLoader {

	public static EnumDAO enumDAO = EnumDAO.getDAO();
	public static ReversalHashMap<Integer, String> USER_ROLE_MAP;
	public static ReversalHashMap<Integer, String> CUSTOMER_TYPE_MAP;
	public static ReversalHashMap<Integer, String> TRUCK_CAPACITY_MAP;
	public static ReversalHashMap<Integer, String> CAR_STATUS_MAP;
	public static ReversalHashMap<Integer, String> CAR_STATE_MAP;
	public static ReversalHashMap<Integer, String> CAR_TYPE_MAP;
	public static ReversalHashMap<Integer, String> ROUTE_STATUS_MAP;
	public static ReversalHashMap<Integer, String> REQUEST_STATUS_MAP;

	static {
		USER_ROLE_MAP = enumDAO.getEnumMap(T_USER_ROLE);
		CUSTOMER_TYPE_MAP = enumDAO.getEnumMap(T_CUSTOMER_TYPE);
		TRUCK_CAPACITY_MAP = enumDAO.getEnumMap(T_TRUCK_CAPACITY);
		CAR_STATUS_MAP = enumDAO.getEnumMap(T_CAR_STATUS);
		CAR_STATE_MAP = enumDAO.getEnumMap(T_CAR_STATE);
		CAR_TYPE_MAP = enumDAO.getEnumMap(T_CAR_TYPE);
		ROUTE_STATUS_MAP = enumDAO.getEnumMap(T_ROUTE_STATUS);
		REQUEST_STATUS_MAP = enumDAO.getEnumMap(T_REQUEST_STATUS);
	}

}
