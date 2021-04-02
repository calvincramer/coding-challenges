package problem054;

import java.util.Random;


public class Card {
    
    public static final int SPADES = 0;
    public static final int CLUBS = 1;
    public static final int DIAMONDS = 2;
    public static final int HEARTS = 3;
    
    public static final int TEN = 10;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;
    
    
    public int number;
    public int suit;
    
    public Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
        
        if (number < 2)
            this.number = 2;
        if (suit < 0)
            suit = 0;
    }
    
    public int compareTo(Card other) {
        if (this.number > other.number)
            return 1;                           //this is greater than other
        else if (other.number > this.number)
            return -1;                          //other is greater than this
        //compare suits
        if (this.suit > other.suit)
            return 1;
        if (other.suit > this.suit)
            return -1;
        
        //else they're both equal
        return 0;
    }
    
    public String toString() {
        String str = "";
        if (this.number < 10) {
            str += number;
        } else {
            switch (this.number) {
                case Card.TEN : str += "T"; break;  //so card is only 2 characters long rather than 10C
                case Card.JACK: str += "J"; break;
                case Card.QUEEN:  str += "Q"; break;
                case Card.KING: str += "K"; break;
                case Card.ACE:  str += "A"; break;
            }
        }
        
        switch (this.suit) {
            case Card.SPADES: str += "S"; break;
            case Card.CLUBS:  str += "C"; break;
            case Card.DIAMONDS: str += "D"; break;
            case Card.HEARTS:  str += "H"; break;
        }
        
        return str;
    }
    
    public static void sortHand(Card[] hand) {
        for (int j = hand.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (hand[i].number > hand[i+1].number) {
                    Card temp = hand[i];
                    hand[i] = hand[i+1];
                    hand[i+1] = temp;
                }
            }
            
        }
    }
    
    public static void main(String[] args) {
        Card c1 = new Card(5, Card.CLUBS);
        Card c2 = new Card(8, Card.DIAMONDS);
        
        Random rng = new Random(System.currentTimeMillis());
        
        for (int i = 1; i <= 100; i++) {
            c1.number = rng.nextInt(Card.ACE+1);
            c2.number = rng.nextInt(Card.ACE+1);
            
            c1.suit = rng.nextInt(Card.HEARTS+1);
            c2.suit = rng.nextInt(Card.HEARTS+1);
            
            System.out.print(c1 + "\t" + c2 + "\t");
            if (c1.compareTo(c2) > 0) 
                System.out.println("c1 greater");
            else if (c1.compareTo(c2) < 0)
                System.out.println("c2 greater");
            else
                System.out.println("equal");
        }
        
    }
    
}
