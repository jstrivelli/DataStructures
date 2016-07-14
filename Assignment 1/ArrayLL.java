package all;

class Item {
	String name;
	int next;
	public Item(String name, int next) {
		this.name = name;
		this.next = next;
	}
	public String toString() {
		return "(" + name + "," + next + ")";
	}
}

public class ArrayLL {

	private Item[] all;
	//number of items stored in all array
	private int numItems;
	private int front;
	private int[] avail;
	private int numAvail;

	// Constructor, initializes all data fields, to represent 
	// an empty Item array linked list of length maxItems
	public ArrayLL(int maxItems) {

		all = new Item[maxItems];
		avail = new int[maxItems];
		for(int i = 0; i< avail.length; i++)
		{
			avail[i] = 0;
		}	
		numItems = 0;
		front = 0;
		numAvail = all.length - 1;
		
	}

	// Adds a name to the front of this array linked list, in worst case O(1) time,
	// and returns true.
	// Returns false if the array is full, in O(1) time
	public boolean addFront(String name) {

		// COMPLETE THIS METHOD
		if(numAvail <= 0)
			return false;
		else
		{
			Item a = new Item(name, front);
			for(int i =0; i < avail.length; i++)
			{
				if(avail[i] == 0){
				avail[i] = 1;
				all[i] = a;
				numAvail--;
				numItems++;
				return true;
				}
			}

		}	
		// THE NEXT LINE IS ONLY A PLACEHOLDER TO MAKE THE PROGRAM COMPILE 
		// YOU WILL NEED TO CHANGE IT APPROPRIATELY IN YOUR IMPLEMENTATION


	}

	// Deletes the name that is at the front this array linked list, in worst case O(1) time,
	// and returns the deleted name
	// Returns null if the list is empty, in O(1) time
	public String deleteFront() {

		// COMPLETE THIS METHOD
		if(numAvail >= maxItems)
			return null;
		
		// THE NEXT LINE IS ONLY A PLACEHOLDER TO MAKE THE PROGRAM COMPILE 
		// YOU WILL NEED TO CHANGE IT APPROPRIATELY IN YOUR IMPLEMENTATION

		return null;	
	}

	// Deletes the given name from this array linked list, and returns true.
	// Returns false if the name is not in the list.
	// Note: If there are n active items in the list, then this method must run in
	// worst case O(n) time, i.e. time must not depend on the length of the all array
	// (since the array might include available space not filled by active items)
	// Also, avail array should be accessed/updated in O(1) time
	public boolean delete(String name) {

		// COMPLETE THIS METHOD

		// THE NEXT LINE IS ONLY A PLACEHOLDER TO MAKE THE PROGRAM COMPILE
		// YOU WILL NEED TO CHANGE IT APPROPRIATELY IN YOUR IMPLEMENTATION

		return false;	
	}

	// Checks if the given name is in this array linked list
	// Note: If there are n items in the list, then this method must run in
	// worst case O(n) time, i.e. time does not depend on the length of the all array.
	public boolean contains(String name) {

		// COMPLETE THIS METHOD

		// THE NEXT LINE IS ONLY A PLACEHOLDER TO MAKE THE PROGRAM COMPILE
		// YOU WILL NEED TO CHANGE IT APPROPRIATELY IN YOUR IMPLEMENTATION

		return false;
	}

	// Prints the items in this array linked list in sequence from first to last,
	// in worst case O(n) time where n is the number of items in the linked list
	// The list should be printed in a single line, separated by commas
	// Example: earth,mercury,venus
	// Make sure there aren't any extra commas in your output.
	// If the list is empty, you may print either nothing, or an empty string
	public void printList() {

		// COMPLETE THIS METHOD
	}

	// Prints all the entries in the main array (including unused spaces)
	// You may fill in this method and use it for debugging
	// This method WILL NOT be graded
	public void printArray() {

	}

	// Prints all the entries in the avail array that correspond to
	// available spaces in the main array
	// You may fill in this method and use it for debugging
	// This method WILL NOT be graded
	public void printAvailableSpots() {
	}
}
