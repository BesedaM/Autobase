package by.epam.javatraining.beseda.webproject.model.service;

import java.util.HashMap;
import java.util.Map;

import static by.epam.javatraining.beseda.webproject.model.dao.util.dataloader.DatabaseEnumLoader.*;
import static by.epam.javatraining.beseda.webproject.model.service.ServiceConstants.*;

/**
 * Class provides access for all the enum tables in the given database. Enums are represented as HashMap objects
 */
public class EnumService {

    private static Map<String, HashMap> collection = new HashMap<>();

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
     * Returns the object with all the enum collections
     *
     * @return Map object containing all the enums of the database
     */
    public static Map<String, HashMap> getEnumCollection() {
        return collection;
    }
}
