package problem084;

import java.util.Random;
import mathtools.MF;

/*
    In the game, Monopoly, the standard board is set up in the following way:

    A player starts on the GO square and adds the scores on two 6-sided dice to determine the number of squares they advance in a clockwise direction. Without any further rules we would expect to visit each square with equal probability: 2.5%. However, landing on G2J (Go To Jail), CC (community chest), and CH (chance) changes this distribution.

    In addition to G2J, and one card from each of CC and CH, that orders the player to go directly to jail, if a player rolls three consecutive doubles, they do not advance the result of their 3rd roll. Instead they proceed directly to jail.

    At the beginning of the game, the CC and CH cards are shuffled. When a player lands on CC or CH they take a card from the top of the respective pile and, after following the instructions, it is returned to the bottom of the pile. There are sixteen cards in each pile, but for the purpose of this problem we are only concerned with cards that order a movement; any instruction not concerned with movement will be ignored and the player will remain on the CC/CH square.

    Community Chest (2/16 cards):
        Advance to GO
        Go to JAIL

    Chance (10/16 cards):
        Advance to GO
        Go to JAIL
        Go to C1
        Go to E3
        Go to H2
        Go to R1
        Go to next R (railway company)
        Go to next R
        Go to next U (utility company)
        Go back 3 squares.

    The heart of this problem concerns the likelihood of visiting a particular square. That is, the probability of finishing at that square after a roll. For this reason it should be clear that, with the exception of G2J for which the probability of finishing on it is zero, the CH squares will have the lowest probabilities, as 5/8 request a movement to another square, and it is the final square that the player finishes at on each roll that we are interested in. We shall make no distinction between "Just Visiting" and being sent to JAIL, and we shall also ignore the rule about requiring a double to "get out of jail", assuming that they pay to get out on their next turn.

    By starting at GO and numbering the squares sequentially from 00 to 39 we can concatenate these two-digit numbers to produce strings that correspond with sets of squares.

    Statistically it can be shown that the three most popular squares, in order, are JAIL (6.24%) = Square 10, E3 (3.18%) = Square 24, and GO (3.09%) = Square 00. So these three most popular squares can be listed with the six-digit modal string: 102400.

    If, instead of using two 6-sided dice, two 4-sided dice are used, find the six-digit modal string.
*/
public class Problem084 {

    public static Random rng = new Random(System.currentTimeMillis());
    public static final int TOTAL = 33333333;
    public static int doublesRolled = 0;
    public static final int DICE_FACES = 4;     //6 or 4

    public static void main(String[] args) {
        testingBoardProb();
    }

    /*
    IMPLEMENT THREE DOUBLES AND GO TO JAIL TO MAKE GO TO JAIL PERCENTAGE 6.24%
    */
    public static void testingBoardProb() {
        int[] board = new int[40];  //0 = GO, 10 = jail, 20 = FP, 30 = G2J, etc.

        //roll
        //while on community chest or chance, see if need to move
        //increment position counter

        int position = 0;
        for (int i = 0; i < TOTAL; i++) {

            position = rollDice(position);
            while (onChance(position) || onCommunity(position)) {
                int oldPos = position;
                if (onChance(position))
                    position = drawChance(position);
                else if (onCommunity(position))
                    position = drawCommunity(position);

                if (oldPos == position) //not a moving card, ending turn right on chance or community chest
                    break;
            }
            //if on G2J, move to jail
            if (position == 30)
                position = 10;

            //System.out.println(getSquareName(position));

            board[position]++;

        }



        //print precentages
        System.out.println("in order");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "\t");
            System.out.println(board[i] * 100.0 / TOTAL);   //percent
        }

        //print maximum percentages
        System.out.println("\nmax");
        CardPer[] cards = new CardPer[board.length];
        for (int i = 0; i < cards.length; i++)
            cards[i] = new CardPer(board[i], getSquareName(i), i);

        MF.quickSort(cards);
        MF.reverseArray(cards);

        for (int i = 0; i < board.length; i++) {
            System.out.print(cards[i].card + "\t(" + cards[i].position + ")" + "\t");
            System.out.println(cards[i].total * 100.0 / TOTAL);   //percent
        }

    }



    /*
    MAKE A CYLCLIC QUEUE, RATHER THAN RANDOM?
    */
    public static int drawChance(int position) {
        int num = rng.nextInt(16);
        switch (num) {
            case 0: return 0;   //GO
            case 1: return 10;  //JAIL
            case 2: return 11;  //C1
            case 3: return 24;  //E3
            case 4: return 39;  //H2
            case 5: return 5;   //R1
            case 6: return (((position+5)/10)*10 + 5)%40;   //next railroad
            case 7: return (((position+5)/10)*10 + 5)%40;   //next railroad
            case 8: return (position < 12 || position >= 28) ? 12 : 28;     //next utility
            case 9: return position-3;  //go back 3
            default: return position;   //no movement
        }
    }

    /*
    MAKE A CYLCLIC QUEUE, RATHER THAN RANDOM?
    */
    public static int drawCommunity(int position) {
        int num = rng.nextInt(16);
        if (num == 0)
            return 0;   //GO
        else if (num == 1)
            return 10;  //JAIL
        else
            return position;
    }

    public static boolean onChance(int position) {
        return position == 7 || position == 22 || position == 36;
    }

    public static boolean onCommunity(int position) {
        return position == 2 || position == 17 || position == 33;
    }

    public static String getSquareName(int position) {
        switch (position) {
            case 0: return "GO";
            case 1: return "A1";
            case 2: return "CC1";
            case 3: return "A2";
            case 4: return "T1";
            case 5: return "R1";
            case 6: return "B1";
            case 7: return "CH1";
            case 8: return "B2";
            case 9: return "B3";
            case 10: return "JAIL";
            case 11: return "C1";
            case 12: return "U1";
            case 13: return "C2";
            case 14: return "C3";
            case 15: return "R2";
            case 16: return "D1";
            case 17: return "CC2";
            case 18: return "D2";
            case 19: return "D3";
            case 20: return "FP";
            case 21: return "E1";
            case 22: return "CH2";
            case 23: return "E2";
            case 24: return "E3";
            case 25: return "R3";
            case 26: return "F1";
            case 27: return "F2";
            case 28: return "U2";
            case 29: return "F3";
            case 30: return "G2J";
            case 31: return "G1";
            case 32: return "G2";
            case 33: return "CC3";
            case 34: return "G3";
            case 35: return "R4";
            case 36: return "CH3";
            case 37: return "H1";
            case 38: return "T2";
            case 39: return "H2";
            default: return "BAD LOCATION !!!!!!!!!!!!!!";
        }
    }

    public static int rollDice(int position) {
        int d1 = rng.nextInt(DICE_FACES)+1;
        int d2 = rng.nextInt(DICE_FACES)+1;

        if (d1 == d2)
            doublesRolled++;
        else
            doublesRolled = 0;

        if (doublesRolled >= 3) {
            doublesRolled = 0;
            //System.out.println("\t3 DOUBLES!");
            return 10;      //GO TO JAIL SUCKER!
        }

        return (position + d1 + d2) % 40;
    }

    public static class CardPer
        implements Comparable<CardPer> {
        int total;
        String card;
        int position;

        public CardPer(int total, String name, int position) {
            this.total = total;
            this.card = name;
            this.position = position;
        }

        @Override
        public int compareTo(CardPer other) {
            if (this.total > other.total)
                return 1;
            if (this.total < other.total)
                return -1;
            else
                return 0;
        }
    }
}

/* for 6 sided dice
JAIL	(10)	6.240786285714286
E3	(24)	3.184158
GO	(0)	3.0949588571428572
D3	(19)	3.0858437142857142
R3	(25)	3.066848857142857
R1	(5)	2.9620602857142857
D2	(18)	2.934748
R2	(15)	2.9204971428571427
FP	(20)	2.8813905714285712
E1	(21)	2.836837714285714
U2	(28)	2.8056017142857144
D1	(16)	2.792646
E2	(23)	2.734796285714286
F1	(26)	2.705748857142857
C1	(11)	2.7002145714285715
F2	(27)	2.6787648571428573
G1	(31)	2.678026857142857
H2	(39)	2.62449
G2	(32)	2.622572285714286
U1	(12)	2.6028091428571427
CC2	(17)	2.597759142857143
F3	(29)	2.585636
G3	(34)	2.499756571428571
C3	(14)	2.4636174285714287
R4	(35)	2.431257142857143
C2	(13)	2.3718848571428572
CC3	(33)	2.3666925714285716
T1	(4)	2.3275022857142855
B2	(8)	2.320956857142857
B3	(9)	2.2993405714285715
B1	(6)	2.2615728571428573
H1	(37)	2.1859185714285716
T2	(38)	2.1800094285714287
A2	(3)	2.1607697142857143
A1	(1)	2.1318034285714287
CC1	(2)	1.8845045714285715
CH2	(22)	1.0455837142857143
CH3	(36)	0.8664802857142857
CH1	(7)	0.865154
G2J	(30)	0.0
*/

/* for 4 sided dice
JAIL	(10)	7.022302285714286
R2	(15)	3.616253142857143
E3	(24)	3.287921142857143
D1	(16)	3.2273422857142857
R3	(25)	3.109660857142857
D3	(19)	3.0609082857142855
E1	(21)	3.050475714285714
FP	(20)	2.9959737142857144
E2	(23)	2.9954134285714287
D2	(18)	2.9737202857142857
R1	(5)	2.9009194285714286
C3	(14)	2.883371142857143
U2	(28)	2.8547954285714288
G1	(31)	2.813392857142857
GO	(0)	2.7886925714285713
F3	(29)	2.7535765714285714
CC2	(17)	2.7175374285714287
F1	(26)	2.6472294285714284
G2	(32)	2.5820402857142857
F2	(27)	2.5528651428571427
C2	(13)	2.516513428571429
H2	(39)	2.4772474285714288
C1	(11)	2.4438505714285714
U1	(12)	2.413572285714286
T1	(4)	2.2834802857142855
G3	(34)	2.200352571428571
CC3	(33)	2.1897814285714285
H1	(37)	2.1191177142857143
B2	(8)	2.1130714285714287
B3	(9)	2.0981365714285714
B1	(6)	2.0923502857142857
T2	(38)	2.0796454285714288
A2	(3)	2.0296145714285716
R4	(35)	1.9830622857142857
A1	(1)	1.7592665714285713
CC1	(2)	1.6779342857142858
CH2	(22)	1.1248825714285715
CH3	(36)	0.7879297142857142
CH1	(7)	0.7757991428571429
G2J	(30)	0.0
*/

//answer: JAIL, R2, E3 = (101524)