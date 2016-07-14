package structures;

import java.util.ArrayList;

/**
 * This class is a repository of sorting methods used by the interval tree.
 * It's a utility class - all methods are static, and the class cannot be instantiated
 * i.e. no objects can be created for this class.
 * 
 * @author runb-cs112
 */
public class Sorter {

	private Sorter() { }
	
	/**
	 * Sorts a set of intervals in place, according to left or right endpoints.  
	 * At the end of the method, the parameter array list is a sorted list. 
	 * 
	 * @param intervals Array list of intervals to be sorted.
	 * @param lr If 'l', then sort is on left endpoints; if 'r', sort is on right endpoints
	 */
	public static void sortIntervals(ArrayList<Interval> intervals, char lr) {
		//if the ArrayList is empty. Don't do anything
		if(intervals == null || intervals.size() == 1){
			return;
		}

		Interval temp;

		for(int i = 0; i < intervals.size(); i++)
		{
			if(lr == 'l'){
				//loops through the next series of end points to find the minimum
				for(int j = i; j > 0; j--){
					if(intervals.get(j).leftEndPoint < intervals.get(j-1).leftEndPoint){
						temp = intervals.get(j);
						intervals.set(j,intervals.get(j-1));
						intervals.set(j-1,temp);
					}
				}
			}
			else{
				for(int j = i; j > 0; j--){
					if(intervals.get(j).rightEndPoint < intervals.get(j-1).rightEndPoint){
						temp = intervals.get(j);
						intervals.set(j,intervals.get(j-1));
						intervals.set(j-1,temp);
					}
				}	

			}
			
		}

		

			

			
	}
	
	/**
	 * Given a set of intervals (left sorted and right sorted), extracts the left and right end points,
	 * and returns a sorted list of the combined end points without duplicates.
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 * @return Sorted array list of all endpoints without duplicates
	 */
	public static ArrayList<Integer> getSortedEndPoints(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		
			//declaring a new arraylist which will later be returned
			ArrayList<Integer> points = new ArrayList<Integer>();
			//first i will add all the left endpoints to my arraylist of integers
			for (int j = 0; j < leftSortedIntervals.size(); j++){
				points.add(leftSortedIntervals.get(j).leftEndPoint);
			}
			//I am now removing all of duplicates of the left end points
			int i = 0;
			while(i<points.size()-2){
				while (points.get(i) == points.get(i+1)){
					points.remove(i+1);
				}
				i++;
			}
			
			boolean different = true;
			int place = 0;
			//adding all of the right endpoints
			for (int f = 0; f < rightSortedIntervals.size()-1; f++){
				for (int a = 0; a < points.size(); a++){
					if (points.get(a) == rightSortedIntervals.get(f).rightEndPoint){
						different = false;
						break;
					}
					else if(points.get(a) < rightSortedIntervals.get(f).rightEndPoint){
						different = true;
						place = a +1;
					}
				}
				//if this passes than we add the right end point in the appropriate spot in the arraylist
				if (different){
					points.add(place, rightSortedIntervals.get(f).rightEndPoint);
				}
			}
			return points;
			

		
	}
}
