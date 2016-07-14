package structures;

import java.util.*;

/**
 * Encapsulates an interval tree.
 * 
 * @author runb-cs112
 */
public class IntervalTree {
	
	/**
	 * The root of the interval tree
	 */
	IntervalTreeNode root;
	
	/**
	 * Constructs entire interval tree from set of input intervals. Constructing the tree
	 * means building the interval tree structure and mapping the intervals to the nodes.
	 * 
	 * @param intervals Array list of intervals for which the tree is constructed
	 */
	public IntervalTree(ArrayList<Interval> intervals) {
		
		// make a copy of intervals to use for right sorting
		ArrayList<Interval> intervalsRight = new ArrayList<Interval>(intervals.size());
		for (Interval iv : intervals) {
			intervalsRight.add(iv);
		}
		
		// rename input intervals for left sorting
		ArrayList<Interval> intervalsLeft = intervals;
		
		// sort intervals on left and right end points
		Sorter.sortIntervals(intervalsLeft, 'l');
		Sorter.sortIntervals(intervalsRight,'r');
		
		// get sorted list of end points without duplicates
		ArrayList<Integer> sortedEndPoints = Sorter.getSortedEndPoints(intervalsLeft, intervalsRight);
		
		// build the tree nodes
		root = buildTreeNodes(sortedEndPoints);
		
		// map intervals to the tree nodes
		mapIntervalsToTree(intervalsLeft, intervalsRight);
	}
	
	/**
	 * Builds the interval tree structure given a sorted array list of end points.
	 * 
	 * @param endPoints Sorted array list of end points
	 * @return Root of the tree structure
	 */
	public static IntervalTreeNode buildTreeNodes(ArrayList<Integer> endPoints) {
		Queue<IntervalTreeNode> treeQ = new Queue<IntervalTreeNode>();
		for(int i = 0; i < endPoints.size(); i++){
			IntervalTreeNode temp = new IntervalTreeNode(endPoints.get(i), endPoints.get(i), endPoints.get(i));
			temp.leftIntervals = new ArrayList<Interval>();
			temp.rightIntervals = new ArrayList<Interval>();
			treeQ.enqueue(temp);
		}
		int temps = treeQ.size();
		
		// This loop quits when it's at the root
		while(treeQ.size() != 1){
			// This loop makes pairs and enqueues the pairs
			while(temps > 1){
				IntervalTreeNode t1 = treeQ.dequeue();
				IntervalTreeNode t2 = treeQ.dequeue();
				float temp1 = t1.maxSplitValue;
				float temp2 = t2.minSplitValue;
				IntervalTreeNode a = new IntervalTreeNode((temp1 + temp2)/2, t1.minSplitValue, t2.maxSplitValue);
				a.leftIntervals = new ArrayList<Interval>();
				a.rightIntervals = new ArrayList<Interval>();
				a.leftChild = t1;
				a.rightChild = t2;
				treeQ.enqueue(a);
				temps = temps - 2;
				if (temps == 1){
					treeQ.enqueue(treeQ.dequeue());
					temps--;
				}
			}
			temps = treeQ.size();
		}
		return treeQ.dequeue();

	}
	
	/**
	 * Maps a set of intervals to the nodes of this interval tree. 
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 */
	public void mapIntervalsToTree(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		 //COMPLETE THIS METHOD
		if(root == null)
			return;
		IntervalTreeNode temp;
		for(Interval x: leftSortedIntervals){
			temp = root;
			while(true){
				if(temp.splitValue >= x.leftEndPoint && temp.splitValue <= x.rightEndPoint){
					temp.leftIntervals.add(x);
					break;
				}
				else if(temp.splitValue > x.rightEndPoint && temp.splitValue >= x.leftEndPoint){
					temp = temp.leftChild;
				}
				else{
					temp = temp.rightChild;
				}
				
			}
		}
		for(Interval y: rightSortedIntervals){
			temp = root;
			while(true){
				if(temp.splitValue >= y.leftEndPoint && temp.splitValue <= y.rightEndPoint){
					temp.rightIntervals.add(y);
					break;
				}
				else if(temp.splitValue > y.rightEndPoint && temp.splitValue >= y.leftEndPoint){
					temp = temp.leftChild;
				}
				else{
					temp = temp.rightChild;
				}
				
			}
			System.out.println(temp);
		}
		
	}
	
	/**
	 * Gets all intervals in this interval tree that intersect with a given interval.
	 * 
	 * @param q The query interval for which intersections are to be found
	 * @return Array list of all intersecting intervals; size is 0 if there are no intersections
	 */
	public ArrayList<Interval> findIntersectingIntervals(Interval q) {
		// COMPLETE THIS METHOD
		return helper(root, q);
	}
	private ArrayList<Interval> helper(IntervalTreeNode jesse, Interval a){
		ArrayList<Interval> b = new ArrayList<Interval>();
		if(jesse == null){
			return b;
		}
		float splitter = jesse.splitValue;
		IntervalTreeNode leftTree = jesse.leftChild;
		IntervalTreeNode rightTree = jesse.rightChild;
		ArrayList<Interval> rightVals = jesse.rightIntervals;
		ArrayList<Interval> leftVals = jesse.leftIntervals;
		if(splitter < a.leftEndPoint){
			int i = rightVals.size()-1;
			while (i > -1 && (rightVals.get(i).intersects(a))){
				b.add(rightVals.get(i));
				i--;
			}

			b.addAll(helper(rightTree, a));
		}
		else if (splitter > a.rightEndPoint){
			int i = 0;
			while ((i < leftVals.size()) && (leftVals.get(i).intersects(a))){
				b.add(leftVals.get(i));						
				i++;
			}
			b.addAll(helper(leftTree, a));
		}
		else if(a.contains(splitter)){
			for(Interval c: leftVals){
				b.add(c);
			}
			b.addAll(helper(leftTree, a));
			b.addAll(helper(rightTree,a));
		}
		
		return b;
	}
	
	/**
	 * Returns the root of this interval tree.
	 * 
	 * @return Root of interval tree.
	 */
	public IntervalTreeNode getRoot() {
		return root;
	}
}

