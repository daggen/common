package se.daggen.common.types;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Lists {
    /**
     * Will zip two lists to a map. Example: zip({a, b, c}, {1,2,3}) -> {(a,1),(b,2),(b,3)}
     * @param keys, all key elements, if this is not unqiue will the result contain the zipping of the last element
     * @param values
	 * @param mapToPuValuesInto the map to use as return
     * @return a map of size numberOfUniqueElementsIn(keys) where all elements in keys are mapped to corresponding element in values. THis is the same object as mapToPuValuesInto
     * @throws NullPointerException if any of the arguements is null
     * @throws IllegalArgumentException if the two lists is not of equal size
     */	
	public static <K, V> Map<K, V> zip(final List<K> keys, final List<V> values, final Map<K, V> mapToPuValuesInto) throws NullPointerException, IllegalArgumentException{
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Both lists must be of equal size");
        }
        
        Iterator<K> keyIterator = keys.iterator();
        Iterator<V> valueIterator = values.iterator();
        
        while (keyIterator.hasNext()) {
            K key = keyIterator.next();
            V value = valueIterator.next();
            
            mapToPuValuesInto.put(key, value);
        }
		
		return mapToPuValuesInto;
    }
}
