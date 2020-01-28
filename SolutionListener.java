/**
 * SolutionListener.java
 *
 * The listener for the "Solution" button.
 *
 * @author Doeke Buursma
 * 11 December, 2011
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;

public class SolutionListener implements ActionListener {
	
	private PaintPanel panel;
	
	private JButton solution;
	
	private static boolean first = true;
	
	private static ArrayList<int[]> moves = null;
	
	/**
	 * Constructor.
	 */
	public SolutionListener(PaintPanel panel, JButton solution) {
	this.panel = panel;
	this.solution = solution;
	}
	
	/**
	 * Returns whether or not the solution button has been pressed.
	 * @return Whether or not the solution button has been pressed.
	 */
	public static boolean hasBeenPressed() { return !first; }
	
	/**
	 * Mutator.
	 */
	public static void setFirst(boolean b) { first = b; }
	
	/**
	 * The method that will be called when this button is pressed.
	 * @param ae Unused.
	 */
	public void actionPerformed(ActionEvent ae) {
	
		if (first) {
			moves = Hanoi.getTheNode().getData(new ArrayList());
			Hanoi.disableButtons();
			solution.addActionListener(Hanoi.getSolutionL());
//			for (int i = 0; i < Hanoi.getPegs().size(); i++) {
//				Hanoi.getPegs().get(i).getButton().removeActionListener(Hanoi.getPegs().get(i).getButton().getActionListeners()[0]);
//			}
		}
			
		//If there are no pieces or I'm done, do nothing.
		if (Hanoi.getPieces().size() == 0 || moves.size() == 0) {
			return;
		}
			
		solution.setText("Show Next Step");
		
		//For every pair of data, click the corresponding peg's button.
//		for (int i = 0; i < moves.size(); i++) {

			//Select the top piece on this peg.
			Peg peg = Hanoi.getPegs().get(moves.get(0)[0]);
			Hanoi.selectTopPiece(peg);
			
			//Move it.
			peg = Hanoi.getPegs().get(moves.get(0)[1]);
			Hanoi.turnCounter++;
			Hanoi.updateTurnCounter();
			Hanoi.moveSelectedPiece(peg);
			moves.remove(0);
			if (moves.size() == 0)
				solution.setText("Show Solution");
			first = false;
			panel.repaint();

//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {}
//		}
	}
}