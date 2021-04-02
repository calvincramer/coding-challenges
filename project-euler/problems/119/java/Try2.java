package problem119;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Try2 {
    
    public static void main(String[] args) {
        final int size = 201;
        BigInteger sizeBig = new BigInteger(""+size);
        BigInteger[][] powers = new BigInteger[size][size]; //x^y powers
        for (int y = 0; y < size; y++) {
            for (BigInteger x = BigInteger.ZERO; x.compareTo(sizeBig) < 0; x = x.add(BigInteger.ONE)) {
                powers[y][x.intValue()] = x.pow(y);
            } 
        }
        
        List<DigPowSum> list = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                DigPowSum temp = new DigPowSum(x, y, powers[y][x], sumOfDigits(powers[y][x].toString()));
                //add in order
                if (list.isEmpty())
                    list.add(temp);
                else {
                    int i = 0;
                    while (list.get(i).xpowy.compareTo(temp.xpowy) < 0) {
                        i++;
                        if (i >= list.size())
                            break;
                    }
                    list.add(i, temp);
                }
            }
        }
        
        List<DigPowSum> goods = new ArrayList<>();
        
        for (DigPowSum dps : list) {
            if (dps.xpowy.toString().length() < 2) 
                continue;
            if (dps.x == dps.sumOfDigits)
                goods.add(dps);
        }
        
        for (int i = 0; i < goods.size(); i++) {
            System.out.println((i+1) + "\t" + goods.get(i).toString());
        }
        
        
    }
    
    public static long sumOfDigits(String str) {
        char[] digits = str.toCharArray();
        long sum = 0;
        for (char d : digits) {
            sum += Character.getNumericValue(d);
        }
        return sum;
    }
    
    
    public static class DigPowSum {
        int x;
        int y;
        BigInteger xpowy;
        long sumOfDigits;
        
        public DigPowSum(int x, int y, BigInteger xpowy, long sumOfDigits) {
            this.x = x;
            this.y = y;
            this.xpowy = xpowy;
            this.sumOfDigits = sumOfDigits;
        }
        
        @Override 
        public String toString() {
            return x + " ^ " + y + "\t= " + xpowy + "\tsumdigits= " + sumOfDigits;
        }
    }
}
//answer: 30	63 ^ 8	= 248155780267521	sumdigits= 63
