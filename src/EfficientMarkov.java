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
	
	public EfficientMarkov() {
		this(3);
	}
	
	@Override
	public void setTraining(String text) {
		myText = text;
		
		myMap.clear();
		
		for (int i = 0; i < text.length() - myOrder; i++) {
			String gram = text.substring(i, i + myOrder);
			
			if (!myMap.containsKey(gram)) {
				myMap.put(gram, new ArrayList<String>());
			}
			
			ArrayList<String> currList = myMap.get(gram);
			
			if (i != text.length() - myOrder) {
				currList.add(text.substring(i + myOrder, i + myOrder + 1));
			}
			else
				currList.add(PSEUDO_EOS);

			myMap.put(gram, currList);
		}
	}
	
	@Override
	public ArrayList<String> getFollows(String key){
		if (myMap.containsKey(key)) {
			return myMap.get(key);
		}
		
		else
			throw new NoSuchElementException(key+" not in map");
	}
}
