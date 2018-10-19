import java.util.*;

public class EfficientMarkov extends BaseMarkov{
	/**
	 * More efficient class for Markov text generation. This version
	 * rescans the training text for each randomly-generated
	 * character. This makes generating N characters an O(T+N) 
	 * task where T is the size of the training text
	 * 
	 * @author al367
	 */
	
	private Map<String, ArrayList<String>> myMap;
	
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	/**
	 * Default Efficient Markov is order 3
	 */
	public EfficientMarkov() {
		this(3);
	}
	
	/**
	 * Clear map.
	 * Generate every n-gram in the text as a possible key in the map.
	 * Add each of the following single-character strings to the ArrayList 
	 * value associated with the 3-gram key.
	 * 
	 * @param String training text
	 */
	@Override
	public void setTraining(String text) {
		myText = text;
		
		myMap.clear();
		
		for (int i = 0; i < text.length() - myOrder + 1; i++) {
			//create new key 
			String gram = text.substring(i, i + myOrder);
			
			//add new key to map
			if (!myMap.containsKey(gram)) {
				myMap.put(gram, new ArrayList<String>());
			}
			
			//ArrayList<String> currList = myMap.get(gram);
			
			//check if key is the last gram 
			if (i != text.length() - myOrder) {
				//currList.add(text.substring(i + myOrder, i + myOrder + 1));
				myMap.get(gram).add(text.substring(i + myOrder, i + myOrder + 1));
			}
			else
				//currList.add(PSEUDO_EOS);
				myMap.get(gram).add(PSEUDO_EOS);

			//myMap.put(gram, currList);
		}
	}
	
	/**
	 * Looks up the key in a map and returns the associated 
	 * ArrayList of single-character strings that was created.
	 * If the key isn't in the map, an exception is thrown.
	 * 
	 * @param String key 
	 * @return ArrayList<String> single-character strings following key
	 */
	@Override
	public ArrayList<String> getFollows(String key){
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		}
		else
			throw new NoSuchElementException(key+" not in map");
	}
}
