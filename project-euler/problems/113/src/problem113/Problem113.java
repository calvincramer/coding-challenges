package problem113;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Problem113 {

    /*
    Working from left-to-right if no digit is exceeded by the digit to its left it is called an increasing number; for example, 134468.

    Similarly if no digit is exceeded by the digit to its right it is called a decreasing number; for example, 66420.

    We shall call a positive integer that is neither increasing nor decreasing a "bouncy" number; for example, 155349.

    As n increases, the proportion of bouncy numbers below n increases such that there are only 12951 numbers below one-million that are not bouncy and only 277032 non-bouncy numbers below 1010.

    How many numbers below a googol (10^100) are not bouncy?
    */
    
    public static final BigInteger thou = BigInteger.TEN.pow(3);
    public static final BigInteger million = BigInteger.TEN.pow(6);
    public static final BigInteger tenMillion = BigInteger.TEN.pow(7);
    public static final BigInteger tenBillion = BigInteger.TEN.pow(10);
    public static final BigInteger googol = BigInteger.TEN.pow(100);
    
    public static void main(String[] args) {

        /*
        for (int digits = 4; digits <= 100; digits++) {
            MF.startTimer();
            testByte(digits);
            System.out.println(MF.getElapsedSeconds());
            System.out.println();
        }
        */
        
        
        BigInteger incLast = BigInteger.ZERO;
        BigInteger decLast = new BigInteger("63");
        
        for (int digits = 4; digits <= 100; digits++) {
            MF.startTimer();
            
            BigInteger inc = countIncreasing(digits);
            BigInteger dec = inc.add(decLast);
            BigInteger incAndDec = new BigInteger(""+(9*(digits-1)));
            
            BigInteger notBouncy = inc.add(dec).subtract(incAndDec);
            
            decLast = dec;
            
            System.out.println("not bouncy:\t" + notBouncy);
            System.out.println("increasing:\t" + inc);
            System.out.println("decreasing:\t" + dec);
            System.out.println("inc and dec:\t"+ incAndDec);
            System.out.println("digits: " + digits + "\t10^("+ (digits-1) + ")");
            System.out.println(MF.getElapsedSeconds());
            System.out.println();
            
        }
        
        
        /*
        for (int digits = 4; digits <= 100; digits++) {
            System.out.println("increasing:\t" + countIncreasingRecur(digits));
            System.out.println("digits: " + digits + "\t10^("+ (digits-1) + ")");
            System.out.println();
        }
        */

    }
    
    //4 digits = 1### stops at 1###
    public static void testByte(int digits) {
        byte[] number = new byte[digits]; //start at 0000001, last number is 0999999, stop when reach 1000000
                                    //length of number = digits
        number[number.length-1] = 1;
        
        long numNB = 0;
        long numIncreasing = 0;
        long numDecreasing = 0;
        long numIncDec = 0;
        List<byte[]> incDecNums = new ArrayList<byte[]>();
        
        while (number[0] != 1) {
            boolean increasing = isIncreasing(number);
            boolean decreasing = isDecreasing(number);
            /*
            if (isNotBouncy(number)) {
                numNB++;
                //printByteNum(number);
            }
            */
            if (increasing || decreasing)
                numNB++;
            if (increasing)
                numIncreasing++;
            if (decreasing)
                numDecreasing++;
            if (increasing && decreasing) {
                numIncDec++;
                byte[] copy = new byte[number.length];
                for (int i = 0; i < number.length; i++)
                    copy[i] = number[i];
                incDecNums.add(copy);
            }
            
            
            //increment
            incrementByteNum(number);
        }
        System.out.println("not bouncy:\t" + numNB);
        System.out.println("increasing:\t" + numIncreasing);
        System.out.println("decreasing:\t" + numDecreasing);
        System.out.println("inc and dec:\t" + numIncDec + "\tguessing: " + (9*(digits-1)));
        System.out.println("out of " + ((int) Math.pow(10, number.length-1)));
    }

    
    //<editor-fold desc="BigInteger functions">
    public static boolean isIncreasing(BigInteger num) {
        char[] charDigs = num.toString().toCharArray();
        int[] digs = new int[charDigs.length];
        for (int i = 0; i < digs.length; i++) {
            digs[i] = Integer.parseInt(""+charDigs[i]);
        }
        
        for (int i = 0; i < digs.length - 1; i++) {
            if (digs[i] > digs[i+1])
                return false;
        }
        
        return true;
    }
    
    public static boolean isDecreasing(BigInteger num) {
        char[] charDigs = num.toString().toCharArray();
        int[] digs = new int[charDigs.length];
        for (int i = 0; i < digs.length; i++) {
            digs[i] = Integer.parseInt(""+charDigs[i]);
        }
        
        for (int i = 0; i < digs.length - 1; i++) {
            if (digs[i] < digs[i+1])
                return false;
        }
        
        return true;
    }
    
    public static boolean isBouncy(BigInteger num) {
        return !isIncreasing(num) && !isDecreasing(num);
    }
    //</editor-fold>
    
    //<editor-fold desc="byte num slow methods">
    public static boolean isIncreasing(byte[] num) {
        int start = 0;
        while (num[start] == 0)
            start++;
        
        for (int i = start; i < num.length - 1; i++) {
            if (num[i+1] < num[i])
                return false;
        }
        return true;
    }
    
    public static boolean isDecreasing(byte[] num) {
        int start = 0;
        while (num[start] == 0)
            start++;
        
        for (int i = start; i < num.length - 1; i++) {
            if (num[i+1] > num[i])
                return false;
        }
        return true;
    }
    
    public static boolean isBouncy(byte[] num) {
        return !isIncreasing(num) && !isDecreasing(num);
    }
    
    public static boolean isNotBouncy(byte[] num) {
        return isIncreasing(num) || isDecreasing(num);
    }
//</editor-fold>
    
    
    public static BigInteger countIncreasing(int digits) {
        byte[] num = new byte[digits];
        num[num.length-1] = 1;
        
        BigInteger nInc = BigInteger.ZERO;
        
        while (num[0] != 1) {
            int decAt = -1;
            for (int i = 1; i < num.length; i++) {
                if (num[i-1] > num[i]) {
                    decAt = i;
                    break;
                }
            }
            
            if (decAt == -1) {
                nInc = nInc.add(BigInteger.ONE);
                incrementByteNum(num);
            }
            else {
                byte toFill = num[decAt-1];
                for (int i = decAt; i < num.length; i++)
                    num[i] = toFill;
            }
        }
        return nInc;
    }
   
    
    
    public static BigInteger countDecreasing(int digits) {
        byte[] num = new byte[digits-1];
        for (int i = 0; i < num.length; i++)
            num[i] = 9;
        
        BigInteger nDec = BigInteger.ZERO;
        
        while (!byteNumIsZero(num)) {
            int decAt = -1;
            for (int i = 1; i < num.length; i++) {
                if (num[i-1] < num[i]) {
                    decAt = i;
                    break;
                }
            }
            
            if (decAt == -1) {
                nDec = nDec.add(BigInteger.ONE);
                decrementByteNum(num);
            }
            else {
                byte toFill = num[decAt-1];
                for (int i = decAt; i < num.length; i++)
                    num[i] = toFill;
            }
        }
        return nDec;
    }
    
    
    public static void incrementByteNum(byte[] num) {
        int i = num.length - 1;
        while (i >= 0) {
            num[i]++;
            if (num[i] == 10) {
                num[i] = 0;
                //continue
            }
            else
                return;
            
            i--;
        }
    }
    
    public static void decrementByteNum(byte[] num) {
        int i = num.length - 1;
        while (i >= 0) {
            num[i]--;
            if (num[i] == -1) {
                num[i] = 9;
            }
            else
                return;
            
            i--;
        }
    }
    
    public static void printByteNum(byte[] num) {
        for (byte n : num)
            System.out.print(n);
        System.out.println();
    }
    
    public static boolean byteNumIsZero(byte[] num) {
        for (int i = 0; i < num.length; i++) {
            if (num[i] != 0)
                return false;
        }
        return true;
    }
    
    
    //
    public static BigInteger countIncreasingRecur(int digits) {
        byte[] num = new byte[digits-1];
        return countIncreasingRecur(0, num).subtract(BigInteger.ONE);
    }
    
    public static BigInteger countIncreasingRecur(int digit, byte[] num) {
        if (digit == num.length-1) {
            BigInteger toReturn = new BigInteger(""+(10-num[digit-1]));
            return toReturn;
        }
        BigInteger total = BigInteger.ZERO;
        byte start = (digit > 0) ? num[digit-1] : 0;
        for (byte i = start; i <= 9; i++) {
            num[digit] = i;
            total = total.add(countIncreasingRecur(digit+1, num));
        }
        return total;
    }
    
}
/* with times
not bouncy:	474
increasing:	219
decreasing:	282
inc and dec:	27
digits: 4	10^(3)
0.001029661

not bouncy:	1674
increasing:	714
decreasing:	996
inc and dec:	36
digits: 5	10^(4)
4.90858E-4

not bouncy:	4953
increasing:	2001
decreasing:	2997
inc and dec:	45
digits: 6	10^(5)
7.1363E-4

not bouncy:	12951
increasing:	5004
decreasing:	8001
inc and dec:	54
digits: 7	10^(6)
0.002433239

not bouncy:	30816
increasing:	11439
decreasing:	19440
inc and dec:	63
digits: 8	10^(7)
0.001384867

not bouncy:	67986
increasing:	24309
decreasing:	43749
inc and dec:	72
digits: 9	10^(8)
0.002357521

not bouncy:	140906
increasing:	48619
decreasing:	92368
inc and dec:	81
digits: 10	10^(9)
0.00464897

not bouncy:	277032
increasing:	92377
decreasing:	184745
inc and dec:	90
digits: 11	10^(10)
0.004494023

not bouncy:	520564
increasing:	167959
decreasing:	352704
inc and dec:	99
digits: 12	10^(11)
0.009843467

not bouncy:	940454
increasing:	293929
decreasing:	646633
inc and dec:	108
digits: 13	10^(12)
0.008874614

not bouncy:	1641354
increasing:	497419
decreasing:	1144052
inc and dec:	117
digits: 14	10^(13)
0.01697976

not bouncy:	2778304
increasing:	817189
decreasing:	1961241
inc and dec:	126
digits: 15	10^(14)
0.034437807

not bouncy:	4576112
increasing:	1307503
decreasing:	3268744
inc and dec:	135
digits: 16	10^(15)
0.043826959

not bouncy:	7354548
increasing:	2042974
decreasing:	5311718
inc and dec:	144
digits: 17	10^(16)
0.083541414

not bouncy:	11560663
increasing:	3124549
decreasing:	8436267
inc and dec:	153
digits: 18	10^(17)
0.107251977

not bouncy:	17809753
increasing:	4686824
decreasing:	13123091
inc and dec:	162
digits: 19	10^(18)
0.192740451

not bouncy:	26936718
increasing:	6906899
decreasing:	20029990
inc and dec:	171
digits: 20	10^(19)
0.246749712

not bouncy:	40059818
increasing:	10015004
decreasing:	30044994
inc and dec:	180
digits: 21	10^(20)
0.421202423

not bouncy:	58659103
increasing:	14307149
decreasing:	44352143
inc and dec:	189
digits: 22	10^(21)
0.564948314

not bouncy:	84672093
increasing:	20160074
decreasing:	64512217
inc and dec:	198
digits: 23	10^(22)
0.778218254

not bouncy:	120609608
increasing:	28048799
decreasing:	92561016
inc and dec:	207
digits: 24	10^(23)
1.103310498

not bouncy:	169694998
increasing:	38567099
decreasing:	131128115
inc and dec:	216
digits: 25	10^(24)
1.561211188

not bouncy:	236030400
increasing:	52451255
decreasing:	183579370
inc and dec:	225
digits: 26	10^(25)
2.158919505

not bouncy:	324794054
increasing:	70607459
decreasing:	254186829
inc and dec:	234
digits: 27	10^(26)
2.966778374

not bouncy:	442473144
increasing:	94143279
decreasing:	348330108
inc and dec:	243
digits: 28	10^(27)
4.087182064

not bouncy:	597137094
increasing:	124403619
decreasing:	472733727
inc and dec:	252
digits: 29	10^(28)
5.543181886

not bouncy:	798756744
increasing:	163011639
decreasing:	635745366
inc and dec:	261
digits: 30	10^(29)
7.370862463

not bouncy:	1059575358
increasing:	211915131
decreasing:	847660497
inc and dec:	270
digits: 31	10^(30)
9.770350789

not bouncy:	1394537976
increasing:	273438879
decreasing:	1121099376
inc and dec:	279
digits: 32	10^(31)
12.966283423

not bouncy:	1821786216
increasing:	350343564
decreasing:	1471442940
inc and dec:	288
digits: 33	10^(32)
16.832058828

not bouncy:	2363226261
increasing:	445891809
decreasing:	1917334749
inc and dec:	297
digits: 34	10^(33)
21.754797119

not bouncy:	3045178431
increasing:	563921994
decreasing:	2481256743
inc and dec:	306
digits: 35	10^(34)
28.006845404

*/