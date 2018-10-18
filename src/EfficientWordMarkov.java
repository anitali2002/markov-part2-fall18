import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov{
	private Map<WordGram, ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order){
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	
	public EfficientWordMarkov() {
		this(2);
	}

	@Override
	public void setTraining(String text){
		myWords = text.split("\\s+");
		
		myMap.clear();
		
		for (int i = 0; i < myWords.length - myOrder; i++) {
			String[] kGram = new String[myOrder];
			
			for (int j = 0; j < myOrder; j++) {
				kGram[j] = myWords[i];
			}
			
			WordGram gram = new WordGram(kGram, 0, myOrder);
			
			if (!myMap.containsKey(gram)) {
				myMap.put(gram, new ArrayList<String>());
			}
			
			//ArrayList<String> currList = myMap.get(gram);
			
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
	
	@Override
	public ArrayList<String> getFollows(WordGram kGram){
		if (myMap.containsKey(kGram)) {
			return myMap.get(kGram);
		}
		
		else
			throw new NoSuchElementException(kGram+" not in map");
	}
}
