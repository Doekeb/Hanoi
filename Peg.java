/**
 * Peg.java
 *
 * Peg for the Tower of Hanoi game.
 *
 * @author Doeke Buursma
 * 10 December, 2011
 */

import javax.swing.*;
import java.util.*;

public class Peg {
	
	/**
	 * The button for this peg.
	 */
	private JButton button;
	
	/**
	 * The pieces on this peg.
	 */
	private ArrayList<Piece> pieces;
	
	/**
	 * This peg's Paint Panel.
	 */
	private PaintPanel panel;
	
	/**
	 * Constructor.
	 */
	public Peg(PaintPanel panel) {
		this.panel = panel;
		button = new JButton(" Move Top Piece ");
		button.addActionListener(new MovePieceListener(panel, this));
		pieces = new ArrayList();
	}
	
	/**
	 * Accessor.
	 * @return This peg's button.
	 */
	public JButton getButton() { return button; }
	
	/**
	 * Accessor.
	 * @return The pieces on this peg.
	 */
	public ArrayList<Piece> getPieces() { return pieces; }
}