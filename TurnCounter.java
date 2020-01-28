/**
 * TurnCounter.java
 *
 * A program to find the number of turns for each number of pieces
 * from one to the specified number of pieces and for the specified
 * number of pegs.
 *
 * @author Doeke Buursma
 * 27 December, 2011
 */
 
import java.util.*;

public class TurnCounter {
	
	/**
	 * The main method
	 * @param args The string arguments. The first index is used for the number of pegs and the
	 * second index is used for the number of pieces.
	 */
	public static void main(String[] args) {
		int nPegs = Integer.parseInt(args[0]);
		int nPieces = Integer.parseInt(args[1]);
		ArrayList<Integer> theList = new ArrayList();
		for (int i = 1; i <= nPieces; i++) {
			SNode theNode = new SNode(nPegs - 1, nPegs - 1, nPegs, 0);
			while (theNode.nSNodes() < i) {
				theNode.makeReplacement();
			}
			while (i < theNode.nSNodes()) {
				theNode.removeChild();
			}
			theList.add(new Integer(theNode.getData(new ArrayList()).size()));
		}
		for (int i = 0; i < theList.size(); i++) {
			System.out.println(theList.get(i));
		}
	}
}