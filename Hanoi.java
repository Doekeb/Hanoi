/**
 * Hanoi.java
 *
 * The Tower of Hanoi game.
 *
 * @author Doeke Buursma
 * 10 December, 2011
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Hanoi {

	/**
	 * The pegs.
	 */
	private static ArrayList<Peg> pegs = new ArrayList();
	
	/**
	 * The pieces.
	 */
	private static ArrayList<Piece> pieces = new ArrayList();
	
	/**
	 * Whether or not a piece is selected.
	 */
	private static boolean pieceIsSelected = false;
	
	/**
	 * The piece (if any) that is selected.
	 */
	private static Piece selectedPiece = null;
	
	/**
	 * The SNode representing this particular game.
	 */
	private static SNode theNode = null;
	
	/**
	 * The turn counter.
	 */
	public static int turnCounter = 0;
	
	/**
	 * The label containing the turn count.
	 */
	private static JLabel turnCountLabel = new JLabel("     Number of Moves: " + turnCounter);
	
	/**
	 * The smallest possible number of turns.
	 */
	public static int minTurns = 0;
	
	/**
	 * The label containing the smallest possible number of turns.
	 */
	private static JLabel minTurnsLabel = new JLabel ("Fewest Possible Moves: " + minTurns);
	
	/**
	 * The label containing the number of pegs.
	 */
	private static JLabel nPegs = new JLabel("Number of Pegs: " + pegs.size());
	
	/**
	 * The label containing the number of peices.
	 */
	private static JLabel nPieces = new JLabel("     Number of Pieces: " + pieces.size());
	
	/**
	 * The Paint Panel.
	 */
	private static PaintPanel panel = new PaintPanel(150, 150);
	
	/**
	 * The panel containing move buttons.
	 */
	private static JPanel moveButtons = new JPanel();
	
	/**
	 * The Add Peg button.
	 */
	private static JButton addPeg = new JButton("Add Peg");
		
	/**
	 * The Listener for the addPeg button.
	 */
	private static AddPegListener addPegL = new AddPegListener(panel, moveButtons);
	
	/**
	 * The Delete Peg button.
	 */	
	private static JButton deletePeg = new JButton("Delete Peg");
	
	/**
	 * The Listener for the deletePeg button.
	 */
	private static DeletePegListener deletePegL = new DeletePegListener(panel, moveButtons);
	
	/**
	 * The Add Piece button.
	 */	
	private static JButton addPiece = new JButton("Add Piece");
	
	/**
	 * The Listener for the addPiece button.
	 */
	private static AddPieceListener addPieceL = new AddPieceListener(panel);
	
	/**
	 * The Delete Piece button.
	 */
	private static JButton deletePiece = new JButton("Delete Piece");
	
	/**
	 * The Listener for the deletePiece button.
	 */
	private static DeletePieceListener deletePieceL = new DeletePieceListener(panel);
	
	/**
	 * The solution button.
	 */
	private static JButton solution = new JButton("Show Solution");
	
	/**
	 * The Listener for the solution button.
	 */
	private static SolutionListener solutionL = new SolutionListener(panel, solution);
	
	/**
	 * Accessor.
	 * @return The solution listener.
	 */
	public static SolutionListener getSolutionL() { return solutionL; }
	
	/**
	 * Accessor.
	 * @return The SNode representing this game.
	 */
	public static SNode getTheNode() { return theNode; }
	
	/**
	 * Accessor.
	 * @return The pegs.
	 */
	public static ArrayList<Peg> getPegs(){ return pegs; }
	
	/**
	 * Accessor.
	 * @return The pieces.
	 */
	public static ArrayList<Piece> getPieces(){ return pieces; }
	
	/**
	 * Accessor.
	 * @return Whether or not a piece is selected.
	 */
	public static boolean pieceIsSelected() { return pieceIsSelected; }
	
	/**
	 * Mutator.
	 * POSTCONDITION: The SNode representing this game has a replacement made on it.
	 */
	public static void makeReplacement() { theNode.makeReplacement(); }
	
	/**
	 * Mutator.
	 * POSTCONDITION: The SNode representing this game has a child removed from it.
	 */
	public static void removeChild() { theNode.removeChild(); }
	
	/**
	 * Mutator.
	 * @param next The next datum for this SNode.
	 * @param degree The degree of this SNode.
	 * @param nPegs The number of pegs for this SNode.
	 * @param previous The previous datum for this SNode.
	 * POSTCONDITION: The SNode representing this game is reset witht the given parameters.
	 */
	public static void resetTheNode(int next, int degree, int nPegs, int previous) {
		theNode = new SNode(next, degree, nPegs, previous);
	}
	
	/**
	 * Mutator.
	 * POSTCONDITION: Empties the SNode for this game.
	 */
	public static void emptyTheNode() { theNode = null; }
	 
	/**
	 * Mutator.
	 * POSTCONDITION: pieceIsSelected is false
	 */
	public static void deselectPiece() {
		pieceIsSelected = false;
		selectedPiece = null;
	}
	
	/**
	 * Accessor.
	 * @return the selected piece.
	 */
	public static Piece getSelectedPiece() { return selectedPiece; }
	 
	/**
	 * Select the top piece of a peg.
	 * @param peg The peg to select the top piece of.
	 * PRECONDITION: pieceIsSelected is false and the peg has at least one piece.
	 * POSTCONDITION: pieceIsSelected is true, the top piece of peg is selected,
	 * and all of the peg buttons read "To Here".
	 */
	public static void selectTopPiece(Peg peg) {
		selectedPiece = peg.getPieces().get(peg.getPieces().size() - 1);
		pieceIsSelected = true;
		for (int i = 0; i < pegs.size(); i++) {
			pegs.get(i).getButton().setText("       To Here       ");
		}
	}
	
	/**
	 * Move the selected piece.
	 * @param peg The peg to move the piece to.
	 * PRECONDITION: A piece is selected.
	 * POSTCONDITION: No piece is selected, the formerly selected piece is moved to peg,
	 * and all of the peg buttons read "Move Top Piece".
	 */
	public static void moveSelectedPiece(Peg peg) {
		selectedPiece.getPeg().getPieces().remove(selectedPiece.getPeg().getPieces().size() - 1);
		peg.getPieces().add(selectedPiece);
		selectedPiece.setPeg(peg);
		pieceIsSelected = false;
		selectedPiece = null;
		for (int i = 0; i < pegs.size(); i++) {
			pegs.get(i).getButton().setText(" Move Top Piece ");
		}
	}
		
	/**
	 * Update the turn counting label.
	 * POSTCONDITION: label is updated.
	 */
	public static void updateTurnCounter() {
		turnCountLabel.setText("     Number of Moves: " + turnCounter);
	}
	
	/**
	 * Update the minimum turns label.
	 * POSTCONDITION: label is updated.
	 */
	public static void updateMinTurns() {
		minTurnsLabel.setText("Fewest Possible Moves: " + minTurns);
	}
	
	/**
	 * Update the nPegs label.
	 * POSTCONDITION: label is updated.
	 */
	public static void updateNPegs() {
		nPegs.setText("Number of Pegs: " + pegs.size());
	}
	
	/**
	 * Update the nPieces label.
	 * POSTCONDITION: label is updated.
	 */
	public static void updateNPieces() {
		nPieces.setText("     Number of Pieces: " + pieces.size());
	}
	
	/**
	 * Get the number of turns.
	 */
	public static int getTurns() { return turnCounter; }
	
	/**
	 * Disable the add/delete piece/peg buttons.
	 * POSTCONDITION: buttons are disabled.
	 */
	public static void disableButtons() {
		addPeg.removeActionListener(addPegL);
		addPeg.setText("");
		
		deletePeg.removeActionListener(deletePegL);
		deletePeg.setText("");
		
		addPiece.removeActionListener(addPieceL);
		addPiece.setText("");
		
		deletePiece.removeActionListener(deletePieceL);
		deletePiece.setText("");
		
		solution.removeActionListener(solutionL);
		solution.setText("");
	}
	
	/**
	 * Enable the add/delete piece/peg buttons.
	 * POSTCONDITION: buttons are enabled.
	 */
	public static void enableButtons() {
		if (addPeg.getActionListeners().length == 0) {
		
			addPeg.addActionListener(addPegL);
			addPeg.setText("Add Peg");
			
			deletePeg.addActionListener(deletePegL);
			deletePeg.setText("Delete Peg");
			
			addPiece.addActionListener(addPieceL);
			addPiece.setText("Add Piece");
			
			deletePiece.addActionListener(deletePieceL);
			deletePiece.setText("Delete Piece");
			
			if (solution.getActionListeners().length == 0)
				solution.addActionListener(solutionL);
			solution.setText("Show Solution");
		}
	}
	 
	/**
	 * The main method.
	 */
	 
	public static void main(String[] args) {
		playGame();
	} 
	
	public static void playGame() {
	
		JFrame window = new JFrame("Tower of Hanoi");
		
		HanoiPainter p = new HanoiPainter(pegs, pieces, panel, window, moveButtons);
		panel.setPainter(p);
		
		moveButtons.setLayout(new GridLayout(1, 1));
		
		JButton help = new JButton("Help/Information");
		help.addActionListener(new HelpListener());
		
		addPeg.addActionListener(addPegL);
		
		deletePeg.addActionListener(deletePegL);
		
		addPiece.addActionListener(addPieceL);
		
		deletePiece.addActionListener(deletePieceL);
		
		solution.addActionListener(solutionL);
		
		JButton save = new JButton("Save");
		save.addActionListener(new SaveListener(window));
		
		JButton open = new JButton("Open");
		open.addActionListener(new OpenListener(window, panel, moveButtons));
		
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(new NewGameListener(moveButtons, panel, null, false));
		
		JButton resetGame = new JButton("Reset Game");
		resetGame.addActionListener(new NewGameListener(moveButtons, panel, null, true));
		
		JPanel gameButtons = new JPanel();
		gameButtons.setLayout(new GridLayout(8, 2));
		
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		window.add(panel);
		
		//Start off with three pegs.
		for (int i = 0; i <= 2; i++) {
			pegs.add(new Peg(panel));
			GridLayout g = (GridLayout) moveButtons.getLayout();
			g.setColumns(g.getColumns() + 1);
			moveButtons.add(pegs.get(i).getButton());
		}
		
		updateNPegs();
		updateNPieces();
		
		gameButtons.add(addPeg);
		gameButtons.add(deletePeg);
		gameButtons.add(addPiece);
		gameButtons.add(deletePiece);
		gameButtons.add(newGame);
		gameButtons.add(resetGame);
		gameButtons.add(solution);
		gameButtons.add(help);
		gameButtons.add(save);
		gameButtons.add(open);
		gameButtons.add(nPegs);
		gameButtons.add(nPieces);
		gameButtons.add(minTurnsLabel);
		gameButtons.add(turnCountLabel);
		window.add(moveButtons);
		window.add(gameButtons);
		
		window.setSize(150, 420);
		window.setLayout(new FlowLayout());
		window.setVisible(true);
	}
}