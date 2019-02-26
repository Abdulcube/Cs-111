import java.util.ArrayList;

/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */


public class Hand {

   private Card[] hand;   // The cards in the hand.
   private int count; 
   
   /**
    * Create a hand that is initially empty.
    */
   public Hand() {
      hand = new Card[5];
	  count = 0;
   }
   
   /**
    * Remove all cards from the hand, leaving it empty.
    */
   public void clear() {
      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
	  count = 0;
   }
   
   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(Card c) {
      
	  for(int i=0 ; i<hand.length; i++){ 
		if (hand[i] == null){
			hand[i] = c;
			count = count + 1;
			break;
		}
	 }

	  
   }
   
   /**
    * Remove a card from the hand, if present.
    * @param c the card to be removed.  If c is null or if the card is not in 
    * the hand, then nothing is done.
    */
   public void removeCard(Card c) {

	for(int i=0 ; i<hand.length; i++){ 
		if (hand[i].equals(c)){
			hand[i] = null;
			count = count-1;
		}
	}

   }
   
   /**
    * Remove the card in a specified position from the hand.
    * @param position the position of the card that is to be removed, where
    * positions are starting from zero.
    * @throws IllegalArgumentException if the position does not exist in
    * the hand, that is if the position is less than 0 or greater than
    * or equal to the number of cards in the hand.
    */
   public void removeCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
      hand[position] = null;
   }

   /**
    * Returns the number of cards in the hand.
    */
   public int getCardCount() {
      return count;
   }
   
   /**
    * Gets the card in a specified position in the hand.  (Note that this card
    * is not removed from the hand!)
    * @param position the position of the card that is to be returned
    * @throws IllegalArgumentException if position does not exist in the hand
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return hand[position];
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            Card c1 = hand[i];
			if (c1 != null){
				if ( c1.getSuit() < c.getSuit() ||
						(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            
			Card c1 = hand[i];
            if (c1 != null){
				if ( c1.getValue() < c.getValue() ||
						(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   public void printHand(){
	   
	   for(int i=0; i<hand.length; i++){
		   if (hand[i] != null){
			   System.out.println(hand[i]);
		   }
	   }
	   System.out.println();
   }
   
// Prints out number of Pairs in hand
 
public int numPairs() {//done
	int repeats=0;
this.sortByValue();
for(int j=0;j<hand.length-1;j++) {
	
			if( hand[j].getValue()==hand[j+1].getValue()) {
				repeats++;
				j++;
				
			}
		}	
	return repeats;
}

//declares whether there are duplicates or not
public boolean hasTriplet() {
	int seek=0;
	boolean value=false;
this.sortByValue();

		for(int k=0;k<hand.length-2;k++) {
			if( hand[k].getValue()==hand[k+1].getValue()&& hand[k].getValue()==hand[k+2].getValue()) {
			    seek++;
			  k=k+2;
			}
		}
		
		if(seek>0){
		  value=true;
		    
	}	
		
		
		

	return value;
}

//states if there are flush or not,, boolean
public boolean hasFlush(){
    this.sortByValue();
    String current=hand[0].getSuitAsString();
    boolean answer=false;
    int counter=0;
    for(int k= 1; k<hand.length; k++){
	String current1=hand[k].getSuitAsString();
	if(current.equals(current1)){
	    counter++;
	}
    }
    if (counter==4){
	answer=true;
	    }
    return answer;
}
// states whether there are straight

public boolean hasStraight(){
	   this.sortByValue();
	   int c1;
	   int c2;

	   if (hand[0].getValue()==1 && hand[1].getValue() == 10 && hand[2].getValue() == 11 && hand[3].getValue() == 12 && hand[4].getValue() == 13){
	     return true;
	   }

	   for (int i=0; i<hand.length-1; i++){

	     c1 = hand[i].getValue();
	     c1++;
	     c2 = hand[i+1].getValue();
	     if( c1 == c2 ){
	       continue;
	     }
	     else{
	       return false;
	     }
	   }
	   return true;
	 }
 
 
// says if there are four of the same value
public boolean hasFourOfAKind(){
	this.sortByValue();
	  if((hand[0].getValue() == hand[1].getValue() && hand[0].getValue()== hand[2].getValue() && hand[0].getValue() == hand[3].getValue()) || (hand[1].getValue() == hand[2].getValue() && hand[1].getValue()== hand[3].getValue() && hand[1].getValue() == hand[4].getValue())) {
		   return true;
		   }
	   return false;
  
	}
 // returns the highest valued card
public Card highestValue() {
	 this.sortByValue();
	 
	 
	if (this.hasStraight() == false) { 
	if (hand[0].getValue() == 1) {
		return hand[0]; 
	} else {
	
	 
	return hand[hand.length-1];
	}
	} else {
		return hand[4]; 
	}
	
	 
}
// returns the highest card values
//add ACE problem
public Card highestPair() {
	  this.sortByValue();
	Card pairs= null;
	
	for(int j=0;j<hand.length-1;j++) {
		if(hand[1].getValue()==1&& hand[0].getValue()==1) {
			return hand[0];
		}
		if( hand[j].getValue()==hand[j+1].getValue()) {
			j++;
			pairs=hand[j];
		} 
		
	}
	return pairs;
}
public Card highestTriplet() {
    Card Triplet=null;
    this.sortByValue();
    if(hand[1].getValue()==1&& hand[0].getValue()==1&& hand[1].getValue()==1) {
		return hand[0];
    }
	for(int k=0;k<hand.length-2;k++) {
		if( hand[k].getValue()==hand[k+1].getValue()&&hand[k].getValue()==hand[k+2].getValue()) {
		    Triplet= hand[k];
		    k=k+2;
		}
	}
	return Triplet;
}
public Card highestDuplicate(){

	   Card tripletCard = findTripletValue();
	   Card highPairCard = pairValues();

	   if(tripletCard == null && highPairCard == null){
	     return null;
	   }

	   if(tripletCard == null){
	     return highPairCard;
	   }
	   else if(highPairCard == null){
	     return tripletCard;
	   }
	   else if(highPairCard.getValue()==1 && tripletCard.getValue() != 1){
	     return highPairCard;
	   }
	   else if(highPairCard.getValue()!=1 && tripletCard.getValue() == 1){
	     return tripletCard;
	   }
	   else if(highPairCard.getValue() > tripletCard.getValue()){
	     return highPairCard;
	   }
	   else{
	     return tripletCard;
	   }
	   }
//returns whether there are full houses
public boolean hasFullHouse() {
	   int count = 0; 
	  
	   boolean fullHouse = false; 
	   this.sortByValue();
	  
	   for (int i = 1; i < hand.length; i++) {
		    if (hand[i-1].getValue() == hand[i].getValue()) {
	   			count++; 
	   		} 
	   }
	   if (this.hasTriplet() == true && this.numPairs() == 2 && count == 3 && this.hasFourOfAKind() == false) {
		   fullHouse = true; 
		   
	   }
	   return fullHouse; 
	   
	   
}
// return which card has better hand. -1 if parameter wins . 0 if tie . 1 if instance wins
public int compareTo(Hand h) {
	   if(this.hasFourOfAKind() && !h.hasFourOfAKind())
		   return 1;
	   else if(h.hasFourOfAKind() && !this.hasFourOfAKind())
		   return -1;
	   else if(h.hasFourOfAKind() && this.hasFourOfAKind())
		   if((this.highestDuplicate().getValue() > h.highestDuplicate().getValue()) || (this.highestDuplicate().getValue() == 0 && h.highestDuplicate().getValue() != 0))
			   return -1;
		   else if((h.highestDuplicate().getValue() > this.highestDuplicate().getValue()) || (h.highestDuplicate().getValue() == 0 && this.highestDuplicate().getValue() != 0))
			   return 1;
	   if(this.hasFullHouse() && !h.hasFullHouse())
		   return 1;
	   else if(h.hasFullHouse() && !this.hasFullHouse())
		   return -1;
	   else if(h.hasFullHouse() && this.hasFullHouse())
		   if(this.findTripletValue().getValue() == 1 && h.findTripletValue().getValue() != 1)
			   return 1;
		   else if(h.findTripletValue().getValue() == 1 && this.findTripletValue().getValue() != 1)
			   return -1;
		   else if((this.findTripletValue().getValue() > h.findTripletValue().getValue()))
			   return 1;
		   else if((h.findTripletValue().getValue() > this.findTripletValue().getValue()))
			   return -1;
	   if(this.hasFlush() && !h.hasFlush())
		   return 1;
	   else if(h.hasFlush() && !this.hasFlush())
		   return -1;
	   else if(h.hasFlush() && this.hasFlush())
		   return sameFlush(h);
	   if(this.hasStraight() && !h.hasStraight())
		   return 1;
	   else if(h.hasStraight() && !this.hasStraight())
		   return -1;
	   else if(h.hasStraight() && this.hasStraight())
		   return sameFlush(h);
	   if(this.hasTriplet() && !h.hasTriplet())
		   return 1;
	   else if(h.hasTriplet() && !this.hasTriplet())
		   return -1;
	   else if(h.hasTriplet() && this.hasTriplet())
		   if(this.findTripletValue().getValue() == 1 && h.findTripletValue().getValue() != 1)
			   return 1;
		   else if(h.findTripletValue().getValue() == 1 && this.findTripletValue().getValue() != 1)
			   return -1;
		   else if((this.findTripletValue().getValue() > h.findTripletValue().getValue()))
			   return 1;
		   else if((h.findTripletValue().getValue() > this.findTripletValue().getValue()))
			   return -1;
	   if(this.numPairs() > h.numPairs())
		   return 1;
	   else if(h.numPairs() > this.numPairs())
		   return -1;
	   else if(this.numPairs() == h.numPairs() && this.numPairs() != 0) {
		   if(this.highestDuplicate().getValue() == 1 && h.highestDuplicate().getValue() != 1)
			   return 1;
		   else if(h.highestDuplicate().getValue() == 1 && this.highestDuplicate().getValue() != 1)
			   return -1;
		   else if((this.highestDuplicate().getValue() > h.highestDuplicate().getValue()))
			   return 1;
		   else if((h.highestDuplicate().getValue() > this.highestDuplicate().getValue()))
			   return -1;
	   }
	   else
		   return sameFlush(h);		//high card
	   
	   return 0;
	   
}
public Card pairValues(){
	   ArrayList<Card> addpairvalue = new ArrayList<Card>();
	     this.sortByValue();
	     int pairs = 0;
	     for (int i=1; i<hand.length;i++){

	       if(hand[i-1].getValue() == hand[i].getValue()){
	         if(hand[i].getValue() == 1){
	           return hand[i];
	         }
	         addpairvalue.add(hand[i]);
	         pairs +=1;
	         i++;
	       }
	     }
if (pairs==-1) {
	
}
	     if(addpairvalue.size()>1){
	       if(addpairvalue.get(0).getValue()>addpairvalue.get(1).getValue()){
	         return addpairvalue.get(0);
	       }
	       else{
	         return addpairvalue.get(1);
	       }
	     }
	     else if (addpairvalue.size() == 1){
	         return addpairvalue.get(0);
	       }
	     // else
	         return null;
	       
	     }

public Card findTripletValue(){
	   this.sortByValue();
	   int a;
	   int b;
	   int c;
	   Card triple;


	   for (int i =0; i<hand.length-2; i++){

	     a = hand[i].getValue();
	     b = hand[i+1].getValue();
	     c = hand[i+2].getValue();

	     if(i==0 && (a==b && a==c)){
	       if(a == hand[3].getValue()){
	         return null;
	       }
	       else{
	         triple = hand[0];
	         return triple;
	       }
	     }

	     if(i==1 && (a==b&&a==c)){
	       if(a == hand[4].getValue()){
	         return null;
	       }
	       else{
	         triple = hand[1];
	         return triple;
	       }
	     }
	     if(i==2 && (a==b&&a==c)){
	       triple = hand[2];
	       return triple;
	     }
	 }
	 return null;
	 }

public int sameFlush(Hand h) {
	   this.sortByValue();
	   h.sortByValue();
	   
	   if(this.hand[0].getValue() == 1 && h.hand[0].getValue() != 1)
		   return 1;
	   else if(h.hand[0].getValue() == 1 && this.hand[0].getValue() != 1)
		   return -1;
	   
	   for(int i = 4; i>-1; i--) {
		   if(this.hand[i].getValue() > h.hand[i].getValue()) 
			   return 1;
		   else if(this.hand[i].getValue() < h.hand[i].getValue())
			   return -1;
	   }
	   
	   return 0;
}
}