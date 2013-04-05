import java.util.*;



public class BinarySearchLexicon implements ILexicon {

    private ArrayList<String> myWords;
    
    public BinarySearchLexicon() {
        myWords = new ArrayList<String>();
    }
    
    public void load(Scanner s) {
        myWords.clear();
        while (s.hasNext()){
            myWords.add(s.next().toLowerCase());
        }
        Collections.sort(myWords);
    }

    public void load(ArrayList<String> list) {
        myWords.clear();
        myWords.addAll(list);
        Collections.sort(myWords);
    }

    public LexStatus wordStatus(StringBuilder s) {
        return wordStatus(s.toString());
    }

    public LexStatus wordStatus(String s) {
        
        // You need to make this code use Binary Search
    	//comments = statements from assignment page
    	
    	int binaryreturn = Collections.binarySearch(myWords, s);
    	//"any non-negative value returned by binarySearch means the status of a word is LexStatus.WORD"
        if(binaryreturn >= 0) {
        	return LexStatus.WORD;
        } else {
        	//If the value returned is negative, one call 
        	//of the appropriate String.startsWith() method can
        	//determine if LexStatus.PREFIX should be returned
        	int should = (binaryreturn * -1) - 1;
        	//make sure you don't go off the end of 
        	//the array of words in the lexicon when 
        	//calling startsWith
        	if(should < myWords.size()) {
        		if(myWords.get(should).startsWith(s)) {
        			return LexStatus.PREFIX;
        		}
        		
        	}
        }
    	
        return LexStatus.NOT_WORD;
    }

    public Iterator<String> iterator() {
        return myWords.iterator();
    }

    public int size() {
        return myWords.size();
    }

}
