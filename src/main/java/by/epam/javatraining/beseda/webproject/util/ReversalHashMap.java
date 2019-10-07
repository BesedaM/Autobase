package by.epam.javatraining.beseda.webproject.util;

import java.util.HashMap;

/**
 * Class designed to easily retrieve key by value from Map object.
 *
 * @param <K> type of key
 * @param <V> type of value
 */
public class ReversalHashMap<K, V> extends HashMap<K, V> {

    private final HashMap<V, K> reverseMap;

    public ReversalHashMap() {
        super();
        reverseMap = new HashMap<>();
    }

    @Override
    public V put(K key, V value) {
        reverseMap.put(value, key);
        return super.put(key, value);
    }

    /**
     * Retrieves the key by the object value.
     * 
     * @param value specified object value
     * @return object's key value
     */
    public K getKey(V value) {
        return reverseMap.get(value);
    }
}