/**
 * OpenListener.java
 *
 * The listener for the "Open" button.
 *
 * @author Doeke Buursma
 * 28 December, 2011
 */
 
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;
import java.io.*;

public class OpenListener implements ActionListener {

	/**
	 * This button's window.
	 */
	private JFrame window;
	
	/**
	 * The PaintPanel.
	 */
	private PaintPanel panel;
	
	/**
	 * The move buttons.
	 */
	private JPanel moveButtons;
	
	public OpenListener(JFrame window, PaintPanel panel, JPanel moveButtons) {
		this.window = window;
		this.panel = panel;
		this.moveButtons = moveButtons;
	}
	
	public void actionPerformed(ActionEvent ae) {
		try{
			JFileChooser chooser = new JFileChooser();
			int returnValue = chooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				Hanoi.deselectPiece();
				//Just like making a new game
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
				Hanoi.updateMinTurns();
				
				Scanner reader = new Scanner(new FileInputStream(chooser.getSelectedFile()));
				StringTokenizer tokenizer = new StringTokenizer(reader.nextLine());
				int nPegs = Integer.parseInt(tokenizer.nextToken());
				int nPieces = Integer.parseInt(tokenizer.nextToken());
				int nTurns = Integer.parseInt(tokenizer.nextToken());
				for (int i = 3; i < nPegs; i++) {
					Hanoi.getPegs().add(new Peg(panel));
					GridLayout g = (GridLayout) moveButtons.getLayout();
					g.setColumns(g.getColumns() + 1);
					JButton b = Hanoi.getPegs().get(Hanoi.getPegs().size() - 1).getButton();
					moveButtons.add(b);
				}
				for (int i = 0; i < nPieces; i++) {
					Hanoi.getPieces().add(new Piece(i + 1, null));
				}
				Hanoi.turnCounter = nTurns;
				Hanoi.updateTurnCounter();
				for (int i = 0; reader.hasNextLine(); i++) {
					tokenizer = new StringTokenizer(reader.nextLine());
					while (tokenizer.hasMoreTokens()) {
						int level = Integer.parseInt(tokenizer.nextToken());
						for (int j = 0; j < Hanoi.getPieces().size(); j++) {
							if (Hanoi.getPieces().get(j).getLevel() == level) {
								Hanoi.getPegs().get(i).getPieces().add(Hanoi.getPieces().get(j));
								Hanoi.getPieces().get(j).setPeg(Hanoi.getPegs().get(i));
							}
						}
					}
				}
				
				//Find the minimum number of moves
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
				} else {
					Hanoi.emptyTheNode();
					Hanoi.minTurns = 0;
					Hanoi.updateMinTurns();
				}
				reader.close();
				Hanoi.updateNPegs();
				Hanoi.updateNPieces();
				panel.repaint();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}