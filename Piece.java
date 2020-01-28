/**
 * Piece.java
 *
 * Piece for the Tower of Hanoi game.
 *
 * @author Doeke Buursma
 * 11 December, 2011
 */
 
public class Piece {
 	
 	/**
 	 * This piece's level.
 	 */
 	private int level;
 	
 	/**
 	 * This piece's peg.
 	 */
 	private Peg peg;
 	
 	/**
 	 * Constructor.
 	 */
 	public Piece(int level, Peg peg) {
 		this.level = level;
 		this.peg = peg;
 	}
 	
 	/**
 	 * Accessor.
 	 * @return This piece's level.
 	 */
 	public int getLevel() { return level; }
 	
 	/**
 	 * Accessor.
 	 * @return This piece's peg.
 	 */
 	public Peg getPeg() { return peg; }
 	
 	/**
 	 * Mutator.
 	 * @param peg The peg to set this piece to.
 	 */
 	public void setPeg(Peg p) { peg = p; }
 }