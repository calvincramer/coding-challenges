package problem061;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Try4 {
    
    public static void main(String[] args) {
        
        MF.startTimer();
        
        List<PolyNumber> numbers = new ArrayList<>();
        
        int index = 1;
        int num = triangleNumbers(index);
        while (num < 10000) {
            if (num >= 1000)
                numbers.add(new PolyNumber(num, Poly.Tri));
            index++;
            num = triangleNumbers(index);
        }
        index = 1;
        num = squareNumbers(index);
        while (num < 10000) {
            if (num >= 1000)
                numbers.add(new PolyNumber(num, Poly.Sqa));
            index++;
            num = squareNumbers(index);
        }
        index = 1;
        num = pentagonalNumbers(index);
        while (num < 10000) {
            if (num >= 1000)
                numbers.add(new PolyNumber(num, Poly.Pen));
            index++;
            num = pentagonalNumbers(index);
        }
        index = 1;
        num = hexagonalNumbers(index);
        while (num < 10000) {
            if (num >= 1000)
                numbers.add(new PolyNumber(num, Poly.Hex));
            index++;
            num = hexagonalNumbers(index);
        }
        index = 1;
        num = heptagonalNumbers(index);
        while (num < 10000) {
            if (num >= 1000)
                numbers.add(new PolyNumber(num, Poly.Hpt));
            index++;
            num = heptagonalNumbers(index);
        }
        index = 1;
        num = octagonalNumbers(index);
        while (num < 10000) {
            if (num >= 1000)
                numbers.add(new PolyNumber(num, Poly.Oct));
            index++;
            num = octagonalNumbers(index);
        }
        
        PolyNumber[] polyNums = numbers.toArray(new PolyNumber[numbers.size()]);
        MF.quickSort(polyNums);
        
        /*
        for (PolyNumber pn : polyNums)
            System.out.println(pn);
        System.out.println();
        */
        
        //for each polyNum
        //generate each cycle
        
        findCycles(new PolyNumber[6], 0, polyNums);
        
        
        System.out.println("\nTime:\t" + MF.getElapsedSeconds());
    }
    
    public static void findCycles(PolyNumber[] chain, int index, PolyNumber[] allPoly) {
        if (index == 0) {       //start
            for (PolyNumber pn : allPoly) {
                chain[0] = pn;
                findCycles(chain, 1, allPoly);
            }
            return;
        }
        if (index == 6) {       //end
            //check for valid cycle
            boolean good = true;
            for (int i = 0; i < chain.length; i++) {
                for (int j = 0; j < chain.length && j != i; j++) {
                    if (chain[i].poly == chain[j].poly)
                        good = false;
                }
            }
            if (good) {
                int sum = 0;
                for (PolyNumber pn : chain) {
                    System.out.println(pn);
                    sum += pn.num;
                }
                System.out.println("Sum:\t" + sum + "\n");
            }
            
            return;
        }
        
        //generate next number
        List<PolyNumber> quals = getQuals(allPoly, chain[index-1].num+"");
        
        if (index == 5) {
            //remove any that have last two digits different than first two of chain[0]
            String first2 = (chain[0]+"").substring(0,2);
            for (int i = 0; i < quals.size(); i++) {
                if (!(quals.get(i).num+"").substring(2).equals(first2))
                    quals.set(i, null);
            }
            for (int i = 0; i < quals.size(); i++) {
                if (quals.get(i) == null) {
                    quals.remove(i);
                    i--;
                }
            }
        }
        
        for (PolyNumber pn : quals) {
            chain[index] = pn;
            findCycles(chain, index+1, allPoly);
        }
        
    }
    
    
    
    public static List<PolyNumber> getQuals(PolyNumber[] pns, String str) {
        List<PolyNumber> quals = new ArrayList<>();
        
        String last2 = str.substring(str.length()-2);
        
        for (PolyNumber pn : pns) {
            if (last2.equals( (pn.num+"").substring(0, 2)))
                quals.add(pn);
        }
        
        return quals;
    }
    
    public static int triangleNumbers(int index) {
        if (index < 1)
            return 0;
        return index * (index + 1) / 2;
    }
    
    public static int squareNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * index;
    }
    
    public static int pentagonalNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * (3 * index - 1) / 2;
    } 
    
    public static int hexagonalNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * (2 * index - 1);
    }
    
    public static int heptagonalNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * (5 * index - 3) / 2;
    }
    
    public static int octagonalNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * (3 * index - 2);
    }
    
    public enum Poly {
        Tri, Sqa, Pen, Hex, Hpt, Oct
        
    }
    
    public static class PolyNumber 
        implements Comparable<PolyNumber> {
        
        int num;
        Poly poly;
        
        public PolyNumber(int n, Poly p) {
            this.num = n;
            this.poly = p;
        }

        @Override public int compareTo(PolyNumber pn) {
            if (this.num > pn.num)
                return 1;
            else if (this.num < pn.num)
                return -1;
            else 
                return 0;
        }
        
        @Override public String toString() {
            return this.num + "\t" + poly;
        }
    }
}//answer: 28684
