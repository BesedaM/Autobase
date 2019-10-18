package by.epam.javatraining.beseda.webproject.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import by.epam.javatraining.beseda.webproject.util.ReversalHashMap;

/**
 * Class provides access for all the enumeration tables in the given database.
 * Enumerations are represented as HashMap objects.
 */
@Service
public class EnumMap {

//	@Autowired
//	private EnumDAO enumDAO;

	private Map<String, ReversalHashMap<Integer, String>> collection;

	public EnumMap(Map<String, ReversalHashMap<Integer, String>> collection) {
		this.collection = collection;
	}

	/**
	 * Returns the object with all the enumeration collections.
	 *
	 * @return Map object containing all the enumerations of the database
	 */
	public Map<String, ReversalHashMap<Integer, String>> getCollection() {
		return collection;
	}

}