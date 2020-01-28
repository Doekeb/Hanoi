/**
 * MovePieceListener.java
 *
 * The listener for the buttons that move pieces.
 *
 * @author Doeke Buursma
 * 12 December, 2011
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MovePieceListener implements ActionListener {
		
	/**
	 * The paint panel in the window.
	 */
	private PaintPanel panel;
	
	/**
	 * This button's peg.
	 */
	private Peg peg;
	
	/**
	 * Constructor.
	 */
	public MovePieceListener(PaintPanel panel, Peg peg) {
		this.panel = panel;
		this.peg = peg;
	}
	
	/**
	 * The method that will be called when this button
	 * is pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
	
		//If a piece is selected,
		if (Hanoi.pieceIsSelected()) {
			
			//If the peg to be moved to is empty OR
			//the selected piece has a level higher than the top
			//piece on the peg to be moved to...
			if (peg.getPieces().size() == 0 ||
					Hanoi.getSelectedPiece().getLevel() >=
					peg.getPieces().get(peg.getPieces().size() - 1).getLevel()) {
					
				//Only add to the turn count (and start the game if it's the first move)
				//if I'm moving to a different peg than I am on right now.
				if (peg.getPieces().size() == 0 || 
						Hanoi.getSelectedPiece().getLevel() !=
						peg.getPieces().get(peg.getPieces().size() - 1).getLevel()) {
					Hanoi.turnCounter++;
					Hanoi.updateTurnCounter();
					Hanoi.disableButtons();
				}
				//Move this piece
				Hanoi.moveSelectedPiece(peg);
			}
		
		//If no piece is selected,
		} else {
			
			//If there is a piece on this peg, select it.
			if (peg.getPieces().size() > 0)
				Hanoi.selectTopPiece(peg);
		}
		panel.repaint();
	}
}