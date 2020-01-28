/**
 * DeletePegListener.java
 *
 * The listener for the "Delete Peg" button.
 *
 * @author Doeke Buursma
 * 10 December, 2011
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import javax.swing.*;

public class DeletePegListener implements ActionListener {
		
	/**
	 * The paint panel in the window.
	 */
	private PaintPanel panel;
	
	/**
	 * The panel containing move buttons
	 */
	private JPanel moveButtons;
	
	/**
	 * Constructor.
	 */
	public DeletePegListener(PaintPanel panel, JPanel moveButtons) {
		this.panel = panel;
		this.moveButtons = moveButtons;
	}
	
	/**
	 * The method that will be called when this button
	 * is pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		if (Hanoi.getPegs().size() > 3) {
			try {
				moveButtons.remove(Hanoi.getPegs().get(Hanoi.getPegs().size() - 1).getButton());
				Hanoi.getPegs().remove(Hanoi.getPegs().size() - 1);
				
				GridLayout g = (GridLayout) moveButtons.getLayout();
				g.setColumns(g.getColumns() - 1);
				
				if (Hanoi.getPieces().size() != 0) {
					//The SNode for this particular game.
					Hanoi.resetTheNode(Hanoi.getPegs().size() - 1, Hanoi.getPegs().size() - 1,
									   Hanoi.getPegs().size(), 0);
										 
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
				
				Hanoi.updateNPegs();
				
				panel.repaint();
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
	}
}