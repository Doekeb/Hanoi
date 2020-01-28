/**
 * AddPegListener.java
 *
 * The listener for the "Add Peg" button.
 *
 * @author Doeke Buursma
 * 10 December, 2011
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class AddPegListener implements ActionListener {
		
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
	public AddPegListener(PaintPanel panel, JPanel moveButtons) {
		this.panel = panel;
		this.moveButtons = moveButtons;
	}
	
	/**
	 * The method that will be called when this button
	 * is pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		Hanoi.getPegs().add(new Peg(panel));
		
		GridLayout g = (GridLayout) moveButtons.getLayout();
		g.setColumns(g.getColumns() + 1);
		
		JButton b = Hanoi.getPegs().get(Hanoi.getPegs().size() - 1).getButton();
		
		moveButtons.add(b);
		
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
		
		Hanoi.updateNPegs();	
			
		panel.repaint();
	}
}