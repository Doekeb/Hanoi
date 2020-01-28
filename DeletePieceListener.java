/**
 * DeletePieceListener.java
 *
 * The listener for the "Delete Piece" button.
 *
 * @author Doeke Buursma
 * 11 December, 2011
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;

public class DeletePieceListener implements ActionListener {
		
	/**
	 * The paint panel in the window.
	 */
	private PaintPanel panel;
	
	/**
	 * Constructor.
	 */
	public DeletePieceListener(PaintPanel panel) {
		this.panel = panel;
	}
	
	/**
	 * The method that will be called when this button
	 * is pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		try {
			Hanoi.getPegs().get(0).getPieces().remove(Hanoi.getPegs().get(0).getPieces().size() - 1);
			Hanoi.getPieces().remove(Hanoi.getPieces().size() - 1);
			
			if (Hanoi.getPieces().size() != 0) {
				Hanoi.resetTheNode(Hanoi.getPegs().size() - 1, Hanoi.getPegs().size() - 1,
								   Hanoi.getPegs().size(), 0); //The SNode for this particular game.
									 
				//Until there are more pairs of data than pieces, make replacements.
				while (Hanoi.getTheNode().nSNodes() < Hanoi.getPieces().size()) {
					Hanoi.makeReplacement();
				}
				
				//Until there are the same number of pairs of data
				//as there are pieces, remove pairs of data.
				while (Hanoi.getPieces().size() < Hanoi.getTheNode().nSNodes()) {
					Hanoi.removeChild();
				}
				
				Hanoi.minTurns = Hanoi.getTheNode().getData(new ArrayList()).size();
				Hanoi.updateMinTurns();
			} else {
				Hanoi.emptyTheNode();
				Hanoi.minTurns = 0;
				Hanoi.updateMinTurns();
			}
			
			Hanoi.updateNPieces();
			
			panel.repaint();
		} catch (ArrayIndexOutOfBoundsException e) {}
	}
}