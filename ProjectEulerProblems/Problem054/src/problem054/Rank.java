package problem054;

public class Rank {

    public R r;
    public Card of;
    
    private Rank(R r, Card of) {
        this.r = r;
        this.of = of;
    }
    
    
    public static Rank rankHand(Card[] hand) {
        if (hand.length != 5)
            return null;
        
        Card.sortHand(hand);
        
        //see if is straight first
        
        boolean allSameSuit = true;
        boolean isStraight = true;
        
        int numPairs = 0;
        int indexOfPair = 0;
        int numTrips = 0;
        int indexOfTrip = 0;
        int numQuads = 0;
        int indexOfQuad = 0;
        for (Card card : hand) {
            if (card.suit != hand[0].suit) {
                allSameSuit = false;
                break;
            }
        }
        for (int i = 1; i < hand.length; i++) {
            if (hand[i].number - hand[i-1].number != 1) {
                isStraight = false;
                break;
            }
        }
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].number == hand[i+1].number) {
                numPairs++;
                indexOfPair = i;
            }
        }
        for (int i = 0; i < hand.length - 2; i++) {
            if (hand[i].number == hand[i+1].number && hand[i].number == hand[i+2].number) {
                numTrips++;
                indexOfTrip = i;
            }
        }
        for (int i = 0; i < hand.length - 3; i++) {
            if (hand[i].number == hand[i+1].number 
                    && hand[i].number == hand[i+2].number
                    && hand[i].number == hand[i+3].number) {
                numQuads++;
                indexOfQuad = i;
            }
        }
        
        //categorize
        if (isStraight && allSameSuit && hand[0].number == Card.TEN) {
            return new Rank(R.ROYAL_FLUSH, hand[0]);
        }
        else if (isStraight && allSameSuit) {
            return new Rank(R.STRAIGHT_FLUSH, hand[0]);
        }
        else if (numQuads == 1) {
            return new Rank(R.FOUR_OF_A_KIND, hand[indexOfQuad]);
        }
        else if (numTrips == 1 && numPairs == 1) {
            return new Rank(R.FULL_HOUSE, hand[indexOfTrip]);
        }
        else if (allSameSuit) {
            return new Rank(R.FLUSH, hand[hand.length-1]);
        }
        else if (isStraight) {
            return new Rank(R.STRAIGHT, hand[hand.length-1]);
        }
        else if (numTrips == 1) {
            return new Rank(R.THREE_OF_A_KIND, hand[indexOfTrip]);
        }
        else if (numPairs == 2) {
            return new Rank(R.TWO_PAIR, hand[indexOfPair]);
        }
        else if (numPairs == 1) {
            return new Rank(R.PAIR, hand[indexOfPair]);
        }
        else {
            return new Rank(R.CARD_HIGH, hand[hand.length-1]);
        }
        
    }
    
    @Override
    public String toString() {
        return this.r + "";
    }
    
    public enum R {
        CARD_HIGH, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH;
    }
}
