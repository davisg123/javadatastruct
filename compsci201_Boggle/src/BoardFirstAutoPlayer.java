import java.util.*;

public class BoardFirstAutoPlayer extends AbstractAutoPlayer {
    
    @Override
    public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
	    // TODO: you write this code
    	for(int r=0; r<board.size(); r++){
    		for(int c=0; c<board.size(); c++){
    			StringBuilder searchWord = new StringBuilder();
    			searchWord.append("");
    			ArrayList<BoardCell> list = new ArrayList<BoardCell>(); 
    			helper(board, r, c, list, searchWord, lex);
    		}
    	}
	}
	//You'll write a recursive helper method for this class to find all the words 
	//starting at a specified [row,column]. The basic idea is to pass to this 
	//helper method at least the following:
	// 1. The current row, column.
	// 2. The string built from the search so far (you may want to use a StringBuilder).
    
    //The code you write will be very similar to 
    //the code you wrote in GoodWordOnBoardFinder.cellsForWord with its helper method.
	public void helper(BoggleBoard board, int r, int c, ArrayList<BoardCell> list,
			StringBuilder searchWord, ILexicon lex) {
		if(r < 0 || r>= board.size() || c < 0 || c >= board.size()) {
			return;
		}
		BoardCell init = new BoardCell(r,c);
		if(list.contains(init)) {
			return;
		}
		
		String letterHere = board.getFace(r, c);
		searchWord.append(letterHere);
		
		if(lex.wordStatus(searchWord)!= LexStatus.NOT_WORD) {
			list.add(init);
			if(lex.wordStatus(searchWord) == LexStatus.WORD) {
				add(searchWord.toString()); // here add is referring to the AbstractPlayer
				//not the list of boardcells
			}
			int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1};
			int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
			//If the string is either a word or the prefix of a word in the lexicon then the search is
			//continued by calling the helper method for each adjacent cube with the string built so far.
			for(int k=0; k < rdelta.length; k++){
				  helper(board, r+rdelta[k], c+cdelta[k], list, searchWord, lex); 
			}
			//If the string is not a prefix (or a word) then the 
			//search is cut-off at this point and the recursion will unwind/backtrack 
			list.remove(init);
		}
		//since you're backtracking, be sure to undo the marking of a board cell both in the string being 
		//built and in the structure storing which board cells contributed to the string
		for(int pl = 0; pl < letterHere.length(); pl++) {
			searchWord.deleteCharAt(searchWord.length() - 1);
		}
		return;
	}
}
