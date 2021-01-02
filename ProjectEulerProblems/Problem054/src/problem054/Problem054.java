package problem054;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem054 {

    /*
    Poker hands
    Problem 54
    In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:

    High Card: Highest value card.
    One Pair: Two cards of the same value.
    Two Pairs: Two different pairs.
    Three of a Kind: Three cards of the same value.
    Straight: All cards are consecutive values.
    Flush: All cards of the same suit.
    Full House: Three of a kind and a pair.
    Four of a Kind: Four cards of the same value.
    Straight Flush: All cards are consecutive values of same suit.
    Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
    The cards are valued in the order:
    2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

    If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next highest cards are compared, and so on.

    Consider the following five hands dealt to two players:
    (see picture)
    The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each hand there is a clear winner.

    How many hands does Player 1 win?
    */
    public static void main(String[] args) {
        
        MathFunctions.startTimer();
        
        Scanner reader = null;
        File curDir = new File("");
        List<String> lines = new ArrayList<>();

        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem054/poker.txt"));
            while (reader.hasNextLine())
                lines.add(reader.nextLine());
        
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        
        Card[][] p1Hands = new Card[lines.size()][5];   //get cards from lines
        Card[][] p2Hands = new Card[lines.size()][5];
        for (int line = 0; line < lines.size(); line++) {
            
            String[] cards = lines.get(line).split(" ");
            
            for (int i = 0; i < cards.length; i++) {
                
                int number = Character.getNumericValue(cards[i].charAt(0));
                if (number > 9) {
                    switch (cards[i].charAt(0)) {
                        case 'T' : number = Card.TEN; break;
                        case 'J' : number = Card.JACK; break;
                        case 'Q' : number = Card.QUEEN; break;
                        case 'K' : number = Card.KING; break;
                        case 'A' : number = Card.ACE; break;
                        default  : number = 0;
                    }
                }
                int suit = 0;
                switch (cards[i].charAt(1)) {
                    case 'S' : suit = Card.SPADES; break;
                    case 'C' : suit = Card.CLUBS;  break;
                    case 'D' : suit = Card.DIAMONDS; break;
                    case 'H' : suit = Card.HEARTS; break;
                }
                Card temp = new Card(number, suit);
                if (i < 5)
                    p1Hands[line][i] = temp;
                else
                    p2Hands[line][i-5] = temp;
                
            }
        }
        
        //do work with cards
        int numP1Wins = 0;
        
        for (int hand = 0; hand < p1Hands.length; hand++) {
            Card[] p1Hand = p1Hands[hand];
            Card[] p2Hand = p2Hands[hand];
            
            Card.sortHand(p1Hand);
            Card.sortHand(p2Hand);
            
            Rank p1Rank = Rank.rankHand(p1Hands[hand]);
            Rank p2Rank = Rank.rankHand(p2Hands[hand]);
            
            //print hands
            for (int i = 0; i < p1Hand.length; i++)
                //System.out.print(p1Hand[i].toString() + " ");
            //System.out.print("\t" + p1Rank.toString() + "\t");
            if (p1Rank.r == Rank.R.PAIR)
                //System.out.print("\t");
            
            //for (int i = 0; i < p2Hand.length; i++)
                //System.out.print(p2Hand[i].toString() + " ");
            //System.out.print("\t" + p2Rank.toString() + "\t");
            if (p2Rank.r == Rank.R.PAIR)
                //System.out.print("\t");
            //System.out.print("\t");
            
            //compare hands
            if (p1Rank.r.ordinal() > p2Rank.r.ordinal()) {
                numP1Wins++;
                //System.out.println("p1 wins");
            } else if (p1Rank.r.ordinal() == p2Rank.r.ordinal()) {
                //compare the cards of the rank (ie pair of 8s vs pair of 10s) (ONLY CHECK NUMBER)
                if (p1Rank.of.number > p2Rank.of.number) {
                    numP1Wins++;
                    //System.out.println("p1 wins");
                } else if (p1Rank.of.number < p2Rank.of.number) {
                    //System.out.println("p2 wins");
                } else {
                    //else compare highest cards
                    Card p1High = p1Hand[p1Hand.length-1];
                    Card p2High = p2Hand[p2Hand.length-1];
                    if (p1High.compareTo(p2High) > 0) {
                        numP1Wins++;
                        //System.out.println("p1 wins");
                    } else if (p1High.compareTo(p2High) == 0) {
                        //System.out.println("\t\tEQUAL");
                    } else {
                        //System.out.println("p2 wins");
                    }
                }
                
            } else {
                //System.out.println("p2 wins");
            }
            //System.out.println();
        }
        
        System.out.println("\nNum player 1 wins: " + numP1Wins);
        System.out.println("Time: " + MathFunctions.getElapledTime());

    }
    //answer: Num player 1 wins: 376
    
    
}
