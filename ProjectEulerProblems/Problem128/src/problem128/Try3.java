package problem128;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mathtools.MF;

public class Try3 {

    //what learned:
    
    //the only PD3 tiles that are along the six major axis are tiles 2 and 8
    //is appears the rest of the PD3 tiles dont fall on the arms
    
    //the only PD3 tiles have a least significant digits of either 0,1,7,8, or 9 in about equal proportions of 20%
    //(besides 2, which only occurs once
    
    //about 13% of PD3 tiles are prime
    //about 23% of PD3 tiles are prime that end in 1,7, or 9.
    
    public static void main(String[] args) {
        List<Long> top = new ArrayList<>();
        List<Long> middle = new ArrayList<>();
        List<Long> bottom = new ArrayList<>();
        
        top.add(1L);
        middle = generate(2, 7);
        bottom = generate(8, 19);
        int pd3Count = 1;   //we count the one tile already
        
        long[] countLastDigit = new long[10];
        List<Long> pd3Nums = new ArrayList<>();
        
        int level = 1;  //level of middle
        while(pd3Count < 2006) {
            //work
            
            //get neighbors
            for (int i = 0; i < middle.size(); i++) {
                Long current = middle.get(i);
                List<Long> neighbors = getNeighbors(top, middle, bottom, i, level);
                //abs difference
                List<Long> diffs = neighbors.stream().map(num -> Math.abs(current - num)).collect(Collectors.toCollection(ArrayList::new));
                //System.out.println("Neigh of " + middle.get(i) + " : " + neighbors.toString());
                //System.out.println("Diffs of " + middle.get(i) + " : " + diffs.toString());
                //System.out.println("PD(" + current + ") : \t" + PD(diffs));
                if (PD(diffs) == 3) {
                    pd3Count++;
                    pd3Nums.add(current);
                    boolean isMajorTile = current % level == 0;
                    System.out.println(pd3Count + " : " + current + " : " + ((isMajorTile) ? "major" : ""));
                    int lastDigit = (int) (current % 10);
                    countLastDigit[lastDigit]++;
                }
                
            }
            
            //System.out.println(middle);
            
            //update levels
            level++;
            top = middle;
            middle = bottom;
            bottom = generate(first(level+1), last(level+1));
        }
        
        System.out.println("");
        System.out.println("digits from " + pd3Count);
        for (int digit = 0; digit < 10; digit++) {
            System.out.println(digit + ": " + countLastDigit[digit] + "\t" + (countLastDigit[digit] * 1.0 / pd3Count));
        }
        
        System.out.println("");
        int primeCount = 0;
        for (long pd : pd3Nums)
            if (MF.isPrimeByPrimes2(pd))
                primeCount++;
        System.out.println("pd3's that are prime: " + (primeCount * 1.0 / pd3Count));
        
        System.out.println("");
        primeCount = 0;
        long pd3_179count = 0;
        for (long pd : pd3Nums) {
            long digit = pd % 10;
            if (digit != 1 && digit != 7 && digit != 9)
                continue;
            pd3_179count++;
            if (MF.isPrimeByPrimes2(pd))
                primeCount++;
        }
        System.out.println("pd3's that are prime and end in 1,7,9: " + (primeCount * 1.0 / pd3_179count));
    }
    
    public static List<Long> getNeighbors(List<Long> top, List<Long> middle, List<Long> bottom, int indexMiddle, int level) {
        int i = indexMiddle;
        List<Long> neighbors = new ArrayList<>();
        //same level neighbors
        int left = i - 1;
        int right = i + 1;
        if (left < 0)
            left = middle.size() - 1;
        if (right >= middle.size())
            right = 0;
        neighbors.add(middle.get(left));
        neighbors.add(middle.get(right));

        //top neighbor/s
        if (i % level == 0) {
            //one top neighbor
            int index = (i / level) * (level - 1);
            neighbors.add(top.get(index));
        }
        else {
            //two top neighbors
            int index = (i / level) * (level - 1) + ((i % level) - 1);
            neighbors.add(top.get(index));
            neighbors.add(top.get( (index+1) % top.size() ));
        }

        //bottom 2 or 3 neighbors
        if (i % level == 0) {
            //3 bottom neighbors
            int index = (i / level) * (level + 1);
            neighbors.add(bottom.get(index));
            neighbors.add(bottom.get(  (index - 1 >= 0)            ? index - 1 : bottom.size() - 1  ));
            neighbors.add(bottom.get(  (index + 1 < bottom.size()) ? index + 1 : 0                  ));
        }
        else {
            //2 bottom neighbors
            int index = (i / level) * (level + 1) + (i % level);
            neighbors.add(bottom.get(index));
            neighbors.add(bottom.get(index+1));
        }
        return neighbors;
    }
    
    //works for level >= 1
    public static long first(long level)  { return level * ((3 * level) - 3) + 2; }
    public static long second(long level) { return level * ((3 * level) - 2) + 2; }
    public static long third(long level)  { return level * ((3 * level) - 1) + 2; }
    public static long fourth(long level) { return level * ((3 * level) + 0) + 2; }
    public static long fifth(long level)  { return level * ((3 * level) + 1) + 2; }
    public static long sixth(long level)  { return level * ((3 * level) + 2) + 2; }
    public static long last(long level)   { return first(level + 1) - 1; }
    
    public static List<Long> generate(long low, long high) {
        List<Long> temp = new ArrayList<>();
        for (long n = low; n <= high; n++)
            temp.add(n);
        return temp;
    }
    
    public static int PD(List<Long> diffsOfNeighbors) {
        int count = 0;
        for (Long n : diffsOfNeighbors)
            if (MF.isPrimeByPrimes4(n))
                count++;
        return count;
    }
}
/*
Neigh of 2 : [7, 3, 1, 8, 19, 9]
Neigh of 3 : [2, 4, 1, 10, 9, 11]
Neigh of 4 : [3, 5, 1, 12, 11, 13]
Neigh of 5 : [4, 6, 1, 14, 13, 15]
Neigh of 6 : [5, 7, 1, 16, 15, 17]
Neigh of 7 : [6, 2, 1, 18, 17, 19]
Neigh of 8 : [19, 9, 2, 20, 37, 21]
Neigh of 9 : [8, 10, 2, 3, 21, 22]
Neigh of 10 : [9, 11, 3, 23, 22, 24]
Neigh of 11 : [10, 12, 3, 4, 24, 25]
Neigh of 12 : [11, 13, 4, 26, 25, 27]
Neigh of 13 : [12, 14, 4, 5, 27, 28]
Neigh of 14 : [13, 15, 5, 29, 28, 30]
Neigh of 15 : [14, 16, 5, 6, 30, 31]
Neigh of 16 : [15, 17, 6, 32, 31, 33]
Neigh of 17 : [16, 18, 6, 7, 33, 34]
Neigh of 18 : [17, 19, 7, 35, 34, 36]
Neigh of 19 : [18, 8, 7, 2, 36, 37]
Neigh of 20 : [37, 21, 8, 38, 61, 39]
Neigh of 21 : [20, 22, 9, 10, 39, 40]
Neigh of 22 : [21, 23, 8, 9, 40, 41]
Neigh of 23 : [22, 24, 10, 42, 41, 43]
Neigh of 24 : [23, 25, 10, 11, 43, 44]
Neigh of 25 : [24, 26, 11, 12, 44, 45]
Neigh of 26 : [25, 27, 12, 46, 45, 47]
Neigh of 27 : [26, 28, 13, 14, 47, 48]
Neigh of 28 : [27, 29, 12, 13, 48, 49]
Neigh of 29 : [28, 30, 14, 50, 49, 51]
Neigh of 30 : [29, 31, 14, 15, 51, 52]
*/

/*
1988 : 14315103019 : 
1989 : 14327125028 : 
1990 : 14330027647 : 
1991 : 14333760019 : 
1992 : 14338737271 : 
1993 : 14347034611 : 
1994 : 14370695198 : 
1995 : 14393959670 : 
1996 : 14450121020 : 
1997 : 14451786728 : 
1998 : 14455951418 : 
1999 : 14498882641 : 
2000 : 14516824220 : 
2001 : 14541459788 : 
2002 : 14551069141 : 
2003 : 14583681019 : 
2004 : 14604605269 : 
2005 : 14651110717 : 
2006 : 14663692447 : 

digits from 2006
0: 427	0.21286141575274178
1: 367	0.18295114656031905
2: 1	4.985044865403788E-4
3: 0	0.0
4: 0	0.0
5: 0	0.0
6: 0	0.0
7: 376	0.18743768693918245
8: 445	0.2218344965104686
9: 389	0.19391824526420737

pd3's that are prime: 0.09371884346959122

pd3's that are prime and end in 1,7,9: 0.16519434628975266
BUILD SUCCESSFUL (total time: 49 minutes 57 seconds)
*/