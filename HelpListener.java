/**
 * HelpListener.java
 *
 * The listener for the "Help" button.
 *
 * @author Doeke Buursma
 * 11 December, 2011
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;

public class HelpListener implements ActionListener {
	
	/**
	 * Constructor.
	 */
	public HelpListener(){}
	
	/**
	 * The method that will be called when this button is pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		JFrame helpWindow = new JFrame("Help/Information");
		helpWindow.setLayout(new GridLayout(29, 1));
		helpWindow.add(new JLabel(" The legend of the Tower of Hanoi goes like this:                 "));
		helpWindow.add(new JLabel("       High in the Himalayas, there is a hindu monestary, called  "));
		helpWindow.add(new JLabel(" \"Hanoi\", that is home to several monks. These monks are deeply "));
		helpWindow.add(new JLabel(" pious and are very faithful with their practices. In the         "));
		helpWindow.add(new JLabel(" monestary, there is a puzzle consisting of three large poles and "));
		helpWindow.add(new JLabel(" sixty-four washer-like disks of varying sizes. Legend has it     "));
		helpWindow.add(new JLabel(" that at the dawn of the world's existence, all of the disks were "));
		helpWindow.add(new JLabel(" stacked, largest to smallest, on a single pole. When the monks   "));
		helpWindow.add(new JLabel(" first arrived in Hanoi one-thousand years ago, they had a vision "));
		helpWindow.add(new JLabel(" in which they were instructed to move the entire stack of disks  "));
		helpWindow.add(new JLabel(" from the current pole to one of the other poles. However, they   "));
		helpWindow.add(new JLabel(" were given three strict rules:                                   "));
		helpWindow.add(new JLabel("	       1. Only one disk may be moved at a time,                     "));
		helpWindow.add(new JLabel("        2. A disk must always either be on a pole or in transit, and "));
		helpWindow.add(new JLabel("        3. A larger disk may not be put on top of a smaller disk.    "));
		helpWindow.add(new JLabel(" They were told that once they finished the puzzle, they would be "));
		helpWindow.add(new JLabel(" enlightened and the world would subsequently come to an end.     "));
		helpWindow.add(new JLabel(" Fortunately for us, even if the monks make one move per second,  "));
		helpWindow.add(new JLabel(" the world will not end for almost six hundred billion years.     "));
		helpWindow.add(new JLabel(" This puzzle allows you to play the Towers of Hanoi with as many  "));
		helpWindow.add(new JLabel(" pegs as you want and as many pieces as you want. Click the Add/  "));
		helpWindow.add(new JLabel(" Delete Peg/Piece buttons to add or delete pegs and pieces. Click "));
		helpWindow.add(new JLabel(" the New Game button to start over. Click the Show Solution button"));
		helpWindow.add(new JLabel(" to see the solution to your particular puzzle. To select the top "));
		helpWindow.add(new JLabel(" piece of a certain peg, click that peg's button. To move the     "));
		helpWindow.add(new JLabel(" selected piece, click the button of the peg that you want to move"));
		helpWindow.add(new JLabel(" it to. If you solve the puzzle, try to solve it again in the     "));
		helpWindow.add(new JLabel(" fewest possible number of moves. Last, but certainly not least,  "));
		helpWindow.add(new JLabel(" Have fun!                                                        "));
		helpWindow.setSize(430, 635);
		helpWindow.setLocation(400, 100);
		helpWindow.setVisible(true);
	}
}