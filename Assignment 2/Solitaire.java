package solitaire;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author Jesse Strivelli
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		CardNode prev = deckRear;=
		CardNode tmp = deckRear.next;
		while(tmp.cardValue != 27){
			prev = prev.next;
			tmp = tmp.next;
		}
		prev.next = tmp.next;
		tmp.next = tmp.next.next;
		prev.next.next = tmp; 
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	    CardNode prev = deckRear;
	    CardNode tmp = deckRear.next;
	    while(tmp.cardValue != 28){
	    	prev = prev.next;
	    	tmp = tmp.next;
	    }
	   prev.next = tmp.next;
	   tmp.next = tmp.next.next.next;
	   prev.next.next.next = tmp;
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		CardNode jokerA, jokerB, apple, beta, cube, drums;
		
		apple = deckRear.next;
		beta = deckRear.next;
		while(beta.next.cardValue != 27 && beta.next.cardValue != 28){
			beta = beta.next;
		}
		jokerA = beta.next;
		jokerB = jokerA.next;
		while(jokerB.cardValue != 27 && jokerB.cardValue != 28){
			jokerB = jokerB.next;
		}
		cube = jokerB.next;
		drums = deckRear;
		if((jokerA == deckRear.next || jokerB == deckRear.next) && (jokerB == deckRear || jokerA == deckRear))
			return;
		if(jokerB == deckRear || jokerA == deckRear){
			deckRear = beta;
			return;
		}
		if(jokerA == deckRear.next || jokerB == deckRear.next){
			deckRear = jokerB;
			return;
		}
			
		beta.next = cube;
		drums.next = jokerA;
		jokerB.next = apple;
		deckRear = beta;
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		// COMPLETE THIS METHOD
		int superDuper = deckRear.cardValue;
		if(superDuper == 27 || superDuper == 28)
		{
			return;
		}	
		CardNode amazing = deckRear.next;
		CardNode prevDeckRear = deckRear.next;
		for(int i = 1; i < superDuper; i++){
			amazing = amazing.next;
		}
		do
		{
			prevDeckRear = prevDeckRear.next;
		}while(prevDeckRear.next != deckRear);
		prevDeckRear.next = deckRear.next;
		deckRear.next = amazing.next;
		amazing.next = deckRear;
	}
	
        /**
         * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
         * counts down based on the value of the first card and extracts the next card value 
         * as key, but if that value is 27 or 28, repeats the whole process until a value
         * less than or equal to 26 is found, which is then returned.
         * 
         * @return Key between 1 and 26
         */
	int getKey() {
		int key = 0;
		jokerA();
		jokerB();
		tripleCut();
		countCut();
		CardNode tmp = deckRear.next;
		int counter = 0;
		if(tmp.cardValue == 28)
			counter = 27;
		else
			counter = tmp.cardValue; 
		for(int i = 0; i < counter; i++)
		{
			tmp = tmp.next;
		}
		key = tmp.cardValue;
		if(key >26)
			return getKey();

		return key;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	public void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {
		String jesse = "";
		String jesseEncrypt = "";
		for(int i = 0; i < message.length(); i++)
		{
			if(Character.isLetter(message.charAt(i)))
			{
				jesse += message.charAt(i);
			}	
		}
		for(int i = 0; i < jesse.length(); i++)
		{
			int alphaPosition = jesse.charAt(i) - 'A' + 1;
			int j = alphaPosition + getKey();
			if(j > 26)
			{
				j = j - 26;
			}
			 j += 'A' - 1;
			 char a = (char)j;
			 jesseEncrypt += a;
		}
		return jesseEncrypt;	
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		String jesse = "";
		int alphaPosition = 0;
		String jesseDecrypt = "";
		for(int i = 0; i < message.length(); i++)
		{
			if(Character.isLetter(message.charAt(i)))
			{
				jesse += message.charAt(i);
			}	
		}
		for(int i = 0; i < jesse.length(); i++){
			alphaPosition = jesse.charAt(i) - 'A' + 1;
			int j = alphaPosition - getKey();
			if(j < 0){
				j += 26;
			}
			 j += 'A' - 1;
			 char a = (char)j;
			 jesseDecrypt += a;
		}
		return jesseDecrypt;	
		
	}
}
