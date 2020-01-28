/**
 * NewGameListener.java
 *
 * The listener for the "New Game" button.
 *
 * @author Doeke Buursma
 * 11 December, 2011
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class NewGameListener implements ActionListener {
	
	/**
	 * The panel containing move buttons.
	 */
	private JPanel moveButtons;
	
	/**
	 * The paint panel in the window.
	 */
	private PaintPanel panel;
	
	/**
	 * The "Winner" frame.
	 */
	private JFrame winner;
	
	/**
	 * Whether or not to reset this game (the other option is to start from scratch).
	 */
	private boolean reset;
	
	/**
	 * Constructor.
	 */
	public NewGameListener(JPanel moveButtons, PaintPanel panel, JFrame winner, boolean reset) {
		this.moveButtons = moveButtons;
		this.panel = panel;
		this.winner = winner;
		this.reset = reset;
	}
	
	/**
	 * The method that will be called when this button is pressed.
	 */
	 public void actionPerformed(ActionEvent ae) {
	 
	 	if (!reset) {
			Hanoi.getPieces().clear();
			if (Hanoi.getPegs().size() > 3) {
				Hanoi.getPegs().clear();
				moveButtons.removeAll();
				GridLayout g = (GridLayout) moveButtons.getLayout();
				g.setColumns(3);
				for (int i = 0; i <= 2; i++) {
					Hanoi.getPegs().add(new Peg(panel));
					moveButtons.add(Hanoi.getPegs().get(i).getButton());
				}
			} else {
				for (int i = 0; i <= 2; i++) {
					Hanoi.getPegs().get(i).getPieces().clear();
				}
				Hanoi.deselectPiece();
				for (int i = 0; i < Hanoi.getPegs().size(); i++) {
					Hanoi.getPegs().get(i).getButton().setText(" Move Top Piece ");
				}
			}
			Hanoi.minTurns = 0;
			Hanoi.updateNPieces();
			Hanoi.updateNPegs();
			Hanoi.updateMinTurns();
		} else {
			for (int i = 0; i < Hanoi.getPegs().size(); i++) {
				Hanoi.getPegs().get(i).getPieces().clear();
			}
			for (int i = 0; i < Hanoi.getPieces().size(); i++) {
				Hanoi.getPieces().get(i).setPeg(Hanoi.getPegs().get(0));
				Hanoi.getPegs().get(0).getPieces().add(Hanoi.getPieces().get(i));
			}
			Hanoi.deselectPiece();
			for (int i = 0; i < Hanoi.getPegs().size(); i++) {
				Hanoi.getPegs().get(i).getButton().setText(" Move Top Piece ");
			}
		}
		
		SolutionListener.setFirst(true);
		Hanoi.turnCounter = 0;
		Hanoi.updateTurnCounter();
		Hanoi.enableButtons();
		
		for (int i = 0; i < Hanoi.getPegs().size(); i++) {
			if (Hanoi.getPegs().get(i).getButton().getActionListeners().length == 0) {
				Hanoi.getPegs().get(i).getButton().addActionListener(new MovePieceListener(panel, Hanoi.getPegs().get(i)));
				Hanoi.getPegs().get(i).getButton().setText(" Move Top Piece ");
			}
		}
		
		if (winner != null)
			winner.setVisible(false);
		
		panel.repaint();
	}
}