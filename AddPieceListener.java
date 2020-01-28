/**
 * AddPieceListener.java
 *
 * The listener for the "Add Piece" button.
 *
 * @author Doeke Buursma
 * 11 December, 2011
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class AddPieceListener implements ActionListener {
		
	/**
	 * The paint panel in the window.
	 */
	private PaintPanel panel;
	
	/**
	 * Constructor.
	 */
	public AddPieceListener(PaintPanel panel) {
		this.panel = panel;
	}
	
	/**
	 * The method that will be called when this button
	 * is pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		Piece p = new Piece(Hanoi.getPieces().size() + 1, Hanoi.getPegs().get(0));
		Hanoi.getPegs().get(0).getPieces().add(p);
		Hanoi.getPieces().add(p);
		
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
		}
		
		Hanoi.updateNPieces();
		
		panel.repaint();
	}
}