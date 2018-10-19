import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov{
	/**
	 * Similar to EfficientMarkov, but uses WordGram objects instead of String
	 * objects to generate random text.
	 * 
	 * @author al367
	 *
	 */
	
	private Map<WordGram, ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order){
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	
	public EfficientWordMarkov() {
		this(2);
	}

	/**
	 * Clear map.
	 * Generate every n-length WordGram in the text as a possible key in the map.
	 * Add each of the following strings to the ArrayList 
	 * value associated with the key.
	 * 
	 * @param String training text
	 */
	@Override
	public void setTraining(String text){
		myWords = text.split("\\s+");
		
		myMap.clear();
		
		for (int i = 0; i < myWords.length - myOrder + 1; i++) {
			//create a new WordGram of myOrder number of words 
			String[] kGram = new String[myOrder];
			
			for (int j = 0; j < myOrder; j++) {
				kGram[j] = myWords[i + j];
			}
			
			WordGram gram = new WordGram(kGram, 0, myOrder);
			
			//add new key to map 
			if (!myMap.containsKey(gram)) {
				myMap.put(gram, new ArrayList<String>());
			}
			
			//ArrayList<String> currList = myMap.get(gram);
			
			//check if key is the last WordGram 
			if (i != myWords.length - myOrder) {
				//currList.add(myWords[i + myOrder + 1]);
				myMap.get(gram).add(myWords[i + myOrder]);
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
	 * @param WordGram kGram (key)
	 * @return ArrayList<String> strings following key
	 */
	@Override
	public ArrayList<String> getFollows(WordGram kGram){
		if (myMap.containsKey(kGram)) {
			return myMap.get(kGram);
		}
		else
			throw new NoSuchElementException(kGram+" not in map");
	}
}
