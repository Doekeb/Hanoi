/**
 * SNode.java
 *
 * A tree node-like class to model the moves required to solve the Tower of Hanoi.
 *
 * @author Doeke Buursma
 * 12 December, 2011
 */

import java.util.*;
import java.lang.*;

public class SNode {
	
	/**
	 * The next datum.
	 */
	private int next;
	
	/**
	 * This node's degree (capacity of child pairs).
	 */
	private int degree;
	
	/**
	 * The number of pegs in the game (constant for every SNode's child).
	 */
	private int nPegs;
	
	/**
	 * The previous datum.
	 */
	private int previous;
	
	/**
	 * This node's pre-children and post-children 
	 * (pairs of children, these will always be the same size).
	 */
	private ArrayList<SNode> preChildren, postChildren;
	
	/**
	 * This node's parent (to make each node doubly linked).
	 */
	private SNode parent;
	
	/**
	 * Constructor.
	 * For the initial SNode in an n-piece, k-peg Tower of Hanoi game,
	 * use next = k-1, degree = k-1, nPegs = k, previous = 0.
	 * @param next The next datum.
	 * @param degree The degree of this SNode.
	 * @param nPegs The number of pegs in this game.
	 * @param previous The previous datum.
	 */
	public SNode(int next, int degree, int nPegs, int previous) {
		this.next = next;
		this.degree = degree;
		this.nPegs = nPegs;
		this.previous = previous;
		preChildren = new ArrayList();
		postChildren = new ArrayList();
		parent = null;
	}
	
	/**
	 * Set this SNode's parent.
	 * @param parent The SNode that will become this SNode's parent.
	 * POSTCONDITION: This SNode's parent is set to parent.
	 */
	public void setParent(SNode parent) {
		this.parent = parent;
	}
	
	/**
	 * Add children to this SNode's preChildren and postChildren lists.
	 * @param preChild The child to be added to the preChildren list.
	 * @param postChild The child to be added to the postChildren list.
	 * POSTCONDITION: The children are added and the children's parents
	 * are set to this.
	 */
	public void addChild(SNode preChild, SNode postChild) {
		preChild.setParent(this);
		postChild.setParent(this);
		preChildren.add(preChild);
		postChildren.add(postChild);
	}
	
	/**
	 * Get the length of the longest path from this to a leaf node.
	 * @return the length of the longest path.
	 */
	public int getLevel() {
	
		//If this node doesn't have any children, return 0.
		if (preChildren.size() == 0) 
			return 0;
			
		//Otherwise, find the child with the longest path length
		//and return the length of its longest path plus one.
		else {
			int n = 0;
			for (int i = 0; i < preChildren.size(); i++) {
				if (preChildren.get(i).getLevel() > preChildren.get(n).getLevel())
					n = i;
			}
			return 1 + preChildren.get(n).getLevel();
		}
	}
	
	/**
	 * Make a replacement for the Tower of Hanoi game.
	 * POSTCONDITION: A replacement is made.
	 */
	public void makeReplacement() {
	
		//Base case: If this node has no children,
		if (preChildren.size() == 0) {
			
			//Do this until the capacity for children is reached.
			for (int i = 0; i < degree - 1; i++) {
				int firstAvailable = nPegs; //The first available peg.
				
				//For each peg,
				for (int j = 0; j < nPegs; j++) {
					boolean isAvailable = true; //Whether or not the peg is available.
					
					//For each pre-child so far,
					for (int k = 0; k < preChildren.size(); k++) {
					
						//If it is on peg k, make j unavailable
						if (preChildren.get(k).next == j)
							isAvailable = false;
					}
					SNode p = parent; //The current SNode's parent.
					SNode q = this; //The current SNode.
					
					//Until I am at the ancestor (highest level) SNode.
					while (p != null) {
					
						//For all of this node's siblings before it,
						for (int k = 0; k < p.degree - q.degree; k++) {
						
							//If it is occupying peg j, make j unavailable.
							if (p.preChildren.get(k).next == j)
								isAvailable = false;
						}
						p = p.parent;
						q = q.parent;
					}	
					
					//If this node moves from peg j or to peg j, make j unavailable.
					if (next == j || previous == j)
						isAvailable = false;
						
					//If j is available and if it is earlier than the first available
					//so far, make j the first available peg.
					if (isAvailable && j < firstAvailable)
						firstAvailable = j;
				}
				
				//Add a pair of children.
				addChild(new SNode(firstAvailable, degree - i, nPegs, previous),
					  	 new SNode(next, degree - i, nPegs, firstAvailable));
			}
		
		//Recursive step: Otherwise, make replacements on all of this SNode's children.
		} else {
			for (int i = 0; i < preChildren.size(); i++) {
				preChildren.get(i).makeReplacement();
				postChildren.get(i).makeReplacement();
			}
		}
	}
	
	/**
	 * Remove a child.
	 * Removes a pair of leaves (one from preChildren and one from postChildren)
	 * that are at the longest path length.
	 * POSTCONDITION: A pair of children with the longest path length is removed.
	 */
	public void removeChild() {
	
		//If this SNode has no children, do nothing.
		if (getLevel() == 0)
			return;
			
		//Base case: If this SNode has only one level of children,
		//remove the first pair.
		else if (getLevel() == 1) {
			preChildren.remove(0);
			postChildren.remove(0);
			
		//Recursive Step: Otherwise, remove the set of children that
		//are at the longest path length.
		} else {
			int n = 0;
			//Find the child with longest path length.
			for (int i = 0; i < preChildren.size(); i++) {
				if (preChildren.get(i).getLevel() > preChildren.get(n).getLevel())
					n = i;
			}
			preChildren.get(n).removeChild();
			postChildren.get(n).removeChild();
		}
	}
	
	/**
	 * Count the total number of SNodes in this SNode.
	 * @return the number of SNodes.
	 */
	public int nSNodes() {
		if (preChildren.size() == 0) 
			return 1;
		else {
			int n = 1;
			for (int i = 0; i < preChildren.size(); i++)
				n += preChildren.get(i).nSNodes();
			return n;
		}
	}
	
	/**
	 * Get this SNode's data in order (preChildren, this, postChildren).
	 * @param a an Array List (always use an empty list).
	 * @return This SNode's data in order.
	 */
	public ArrayList<int[]> getData(ArrayList<int[]> a) {
		
		//Base Case: If this SNode has no children, make a new list,
		//fill it with the values from a, and add this SNode's data.
		if (preChildren.size() == 0) {
			ArrayList<int[]> b = new ArrayList();
			b.addAll(a);
			b.add(new int[] {previous, next});
			return b;
			
		//Recursive Step: Otherwise...
		} else {
			ArrayList<int[]> toReturn = new ArrayList(); //The list to return.
			
			//For all of this node's pre-children, add their data in order.
			for (int i = 0; i < preChildren.size(); i++) {
				toReturn.addAll(preChildren.get(i).getData(a));
			}
			
			toReturn.add(new int[] {previous, next}); //add this node's data.
			
			//For all of this node's post-children, add their data in order.
			for (int i = postChildren.size() - 1; i >= 0; i--) {
				toReturn.addAll(postChildren.get(i).getData(a));
			}
			return toReturn;
		}
	}
	
	/**
	 * Print the list of data in order (previous datum, next datum)
	 * POSTCONDITION: the data is printed in the order of previous, next, new line.
	 */
	public void printData() {
		ArrayList<int[]> a = getData(new ArrayList());
		for (int i = 0; i < a.size(); i++) {
			System.out.print("\\{" + a.get(i)[0] + ", " + a.get(i)[1] + "\\}, ");
			//System.out.println(a.get(i)[1] + "}, ");
		}
	}
	
	/**
	 * For testing.
	 */
	public static void main(String[] args) {
		SNode n = new SNode(4, 4, 5, 0);
		n.makeReplacement();
		n.makeReplacement();
		n.makeReplacement();
//		while (n.NSNodes() < 4) {
//	 		n.makeReplacement();
//	 	}
//	 	while (4 < n.NSNodes()) {
//	 		n.removeChild();
//	 	}		
		
		n.printData();
		
//		n.makeReplacement();
//		n.makeReplacement();
//		System.out.println(n.getLevel());
	}
}