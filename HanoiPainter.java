/**
 * HanoiPainter.java
 *
 * Class that will handle the painting
 * of a paintable GUI component.
 *
 * @author Doeke Buursma
 * 10 December, 2011
 */
 
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.lang.*;

public class HanoiPainter implements Painter{

	/**
	 * The pegs.
	 */
	private ArrayList<Peg> pegs;
	
	/**
	 * The pieces.
	 */
	private ArrayList<Piece> pieces;
	
	/**
	 * The paint panel.
	 */
	private PaintPanel panel;
	
	/**
	 * The move buttons.
	 */
	private JPanel moveButtons;
	
	/**
	 * The JFrame.
	 */
	private JFrame window;
	
	/**
	 * Constructor.
	 */
	public HanoiPainter(ArrayList<Peg> pegs, ArrayList<Piece> pieces,
						PaintPanel panel, JFrame window, JPanel moveButtons) {
		this.pegs = pegs;
		this.pieces = pieces;
		this.panel = panel;
		this.window = window;
		this.moveButtons = moveButtons;
	}
	
    /**
     * Update the display using the given graphics
     * object.
     * @param g The graphics object to manipulate
     */
    public void paint(Graphics g) {
		//make the panel and the window big enough to fit the pegs.
		window.setSize(150 * pegs.size(), window.getHeight());
		panel.setSize(150 * pegs.size(), panel.getHeight());
		panel.setPreferredSize(new Dimension(150 * pegs.size(),
											 (int) panel.getPreferredSize().getHeight()));
	
		//paint the pegs.
		g.setColor(Color.BLACK);
		for (int i = 0; i < pegs.size(); i++) {
			g.drawLine((150 * i) + 75, 0, (150 * i) + 75, 150);
		}
		
		//paint the pieces.
		int numPieces = pieces.size();
		for (int i = 0; i < pegs.size(); i++) {
			for (int j = 0; j < pegs.get(i).getPieces().size(); j++) {
			
				//These 6 lines are to set the color of each piece.
				if (pegs.get(i).getPieces().get(j).getLevel() % 4 == 0)
					g.setColor(Color.LIGHT_GRAY);
				else if ((pegs.get(i).getPieces().get(j).getLevel() + 1) % 4 == 0)
					g.setColor(Color.DARK_GRAY);
				else if ((pegs.get(i).getPieces().get(j).getLevel() + 2) % 4 == 0)
					g.setColor(Color.GRAY);
				else if ((pegs.get(i).getPieces().get(j).getLevel() + 3) % 4 == 0)
					g.setColor(Color.BLACK);
				
				int width;
				if (numPieces == 1) {
					width = 150;
				} else {
					width = 150 - ((pegs.get(i).getPieces().get(j).getLevel() - 1) *
							(140 / (numPieces - 1)));
				}
				int height = panel.getHeight() / numPieces;
				g.fillRect((150 * i) + (75 - (width / 2)),
						   panel.getHeight() - (j * height + height), width, height);
			}
		}
		for (int i = 1; i < pegs.size(); i++) {
			if (pegs.get(i).getPieces().size() == pieces.size() &&
				pieces.size() != 0) {
				
				JFrame winner = new JFrame("You Won!");
				winner.setSize(600, 200);
				winner.setLayout(new FlowLayout());
				
				JLabel winLabel = new JLabel("YOU WON!");
				winLabel.setFont(new Font(Font.SERIF, Font.BOLD, 100));
				winLabel.setForeground(Color.BLUE);
				
				JLabel turnNumber;
				if (Hanoi.getTurns() == Hanoi.minTurns) {
					if (SolutionListener.hasBeenPressed())
						turnNumber = new JLabel("...in " + Hanoi.getTurns()
												+ " turn(s) (the best possible)! For " 
												+ pegs.size() + " pegs and " + pieces.size()
												+ " piece(s). Can you do it without help?");
					else
						turnNumber = new JLabel("...in " + Hanoi.getTurns()
												+ " turn(s) (the best possible)! For " 
												+ pegs.size() + " pegs and " + pieces.size()
												+ " piece(s).");
				} else if (Hanoi.getTurns() > Hanoi.minTurns) {
					if (SolutionListener.hasBeenPressed())
						turnNumber = new JLabel("...in " + Hanoi.getTurns()
												+ " turns (you can do better)! For " 
												+ pegs.size() + " pegs and " + pieces.size()
											 	+ " piece(s). Can you do it without help?");
					else
						turnNumber = new JLabel("...in " + Hanoi.getTurns()
												+ " turns (you can do better)! For " 
												+ pegs.size() + " pegs and " + pieces.size()
											 	+ " piece(s).");
				} else {
					turnNumber = new JLabel("...in " + Hanoi.getTurns()
											 + " turns (you must have cheated)! For " 
											 + pegs.size() + " pegs and " + pieces.size()
											 + " piece(s).");
				}
				
				JPanel buttons = new JPanel();
				buttons.setLayout(new GridLayout(1, 2));
				
				JButton newGame = new JButton("Start a New Game");
				newGame.addActionListener(new NewGameListener(moveButtons, panel, winner, false));
				
				JButton resetGame = new JButton("Reset This Game");
				resetGame.addActionListener(new NewGameListener(moveButtons, panel, winner, true));
				
				buttons.add(newGame);
				buttons.add(resetGame);
				
				winner.add(winLabel);
				winner.add(turnNumber);
				winner.add(buttons);
				winner.setLocation(400, 400);
				winner.setVisible(true);
			}
		}
	}
}
