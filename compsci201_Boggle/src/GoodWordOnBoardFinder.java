import java.util.*;


public class GoodWordOnBoardFinder implements IWordOnBoardFinder {

	@Override
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		// TODO Auto-generated method stub
		ArrayList<BoardCell> list = new ArrayList<BoardCell>();
		for(int r = 0; r < board.size(); r++) {
			for(int c = 0; c < board.size(); c++/*ha!*/) {
				int ind = 0;
				if(helper(board,r,c,list,ind, word)) {
					return list;
				}
			}
		}
		return null;
	}

	public boolean helper(BoggleBoard board, int r, int c, ArrayList<BoardCell> list, int ind, String word) {
		if(ind > word.length() - 1) {
			return true;
		}
		if(r < 0 || r >= board.size() || c< 0 || c >= board.size()) {
			return false;
		}
		BoardCell init = new BoardCell(r,c);
		if (list.contains(init)) {
			return false;
		}
		String onBoard = board.getFace(r, c);
		int ind2;
		if(onBoard.equals("Q")) {
			ind2 = Math.min(ind+2,word.length()-1);
		}else {
			ind2 = ind + 1;
		}
		String inbtwn = word.substring(ind,ind2);
		if(onBoard.equals(inbtwn)) {
			list.add(init);
			int[] rdelta = {-1,-1,-1, 0, 0, 1, 1, 1};
			int[] cdelta = {-1, 0, 1,-1, 1,-1, 0, 1};
			for(int k = 0; k < rdelta.length; k++) {
				if(helper(board, r+rdelta[k], c+cdelta[k], list, ind2, word)) {
					return true;
				}
			}
			list.remove(init);
		}
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
