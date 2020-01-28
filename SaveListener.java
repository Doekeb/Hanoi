/**
 * SaveListener.java
 *
 * The listener for the "Save" button.
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

public class SaveListener implements ActionListener {

	/**
	 * This button's window.
	 */
	private JFrame window;
	
	public SaveListener(JFrame window) {
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent ae) {
		try{
			JFileChooser chooser = new JFileChooser();
			int returnValue = chooser.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				PrintWriter writer = new PrintWriter(chooser.getSelectedFile());
				writer.println(Hanoi.getPegs().size() + " " + Hanoi.getPieces().size() + " "
							   + Hanoi.turnCounter);
				for (int i = 0; i < Hanoi.getPegs().size(); i++) {
					for (int j = 0; j < Hanoi.getPegs().get(i).getPieces().size(); j++) {
						writer.print(Hanoi.getPegs().get(i).getPieces().get(j).getLevel() + " ");
					}
					writer.println();
				}
				writer.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}