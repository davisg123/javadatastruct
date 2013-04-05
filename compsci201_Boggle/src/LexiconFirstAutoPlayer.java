import java.util.*;


public class LexiconFirstAutoPlayer extends AbstractAutoPlayer {

    private IWordOnBoardFinder myFinder;
    
    public LexiconFirstAutoPlayer(){
    	//To find all the words on a board simply iterate 
    	//over every value in a lexicon checking to see if 
    	//the word is on the board by calling 
    	//the cellsForWord method you wrote earlier.
    	//The code to do this is already written for you - just change 
    	//LexiconFirstAutoPlayer to use the GoodWordOnBoardFinder you wrote.
        myFinder = new GoodWordOnBoardFinder();
    }
    
    @Override
    public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
        
        clear();
        for(String word : lex) {
            if (word.length() < minLength) continue;
            List<BoardCell> list = myFinder.cellsForWord(board,word);
            if (list.size() > 0) {
                // found word
                add(word);
            }
        }    
    }

}
