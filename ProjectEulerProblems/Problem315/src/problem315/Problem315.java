package problem315;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mathtools.MF;

public class Problem315 {
    
    static ArrayList<Segment> off = new ArrayList<>();
    static ArrayList<Segment> zero = new ArrayList<>(Arrays.asList(Segment.TOP, Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.BOTTOM_LEFT, Segment.TOP_LEFT));
    static ArrayList<Segment> one = new ArrayList<>(Arrays.asList(Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT));
    static ArrayList<Segment> two = new ArrayList<>(Arrays.asList(Segment.TOP, Segment.TOP_RIGHT, Segment.BOTTOM, Segment.BOTTOM_LEFT, Segment.MIDDLE));
    static ArrayList<Segment> three = new ArrayList<>(Arrays.asList(Segment.TOP, Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.MIDDLE));
    static ArrayList<Segment> four = new ArrayList<>(Arrays.asList(Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.TOP_LEFT, Segment.MIDDLE));
    static ArrayList<Segment> five = new ArrayList<>(Arrays.asList(Segment.TOP, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.TOP_LEFT, Segment.MIDDLE));
    static ArrayList<Segment> six = new ArrayList<>(Arrays.asList(Segment.TOP, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.BOTTOM_LEFT, Segment.TOP_LEFT, Segment.MIDDLE));
    static ArrayList<Segment> seven = new ArrayList<>(Arrays.asList(Segment.TOP, Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.TOP_LEFT));
    static ArrayList<Segment> eight = new ArrayList<>(Arrays.asList(Segment.TOP, Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.BOTTOM_LEFT, Segment.TOP_LEFT, Segment.MIDDLE));
    static ArrayList<Segment> nine = new ArrayList<>(Arrays.asList(Segment.TOP, Segment.TOP_RIGHT, Segment.BOTTOM_RIGHT, Segment.BOTTOM, Segment.TOP_LEFT, Segment.MIDDLE));
    
    static ArrayList<ArrayList<Segment>> digitSegs;
    static int[] numSegments;
    static int[][] transitionTable;
    
    static long samTrans = 0;
    static long maxTrans = 0;
    
    public static void main(String[] args) {
        MF.startTimer();
        
        digitSegs = new ArrayList<ArrayList<Segment>>();
        //for (int i = 1; i <= 11; i++)
        //    digitSegs.add(new ArrayList<>());
        digitSegs.add(0, zero);
        digitSegs.add(1, one);
        digitSegs.add(2, two);
        digitSegs.add(3, three);
        digitSegs.add(4, four);
        digitSegs.add(5, five);
        digitSegs.add(6, six);
        digitSegs.add(7, seven);
        digitSegs.add(8, eight);
        digitSegs.add(9, nine);
        digitSegs.add(10, off);
        
        /*
        for (ArrayList<Segment> segment : digitSegs) {
            System.out.println(segment.toString());
            printSegments(segment);
        }
        */
        
        numSegments = new int[11];
        transitionTable = new int[11][11];
        for (int i = 0; i < 11; i++) {
            numSegments[i] = digitSegs.get(i).size();
            //System.out.println(i + ": " + numSegments[i]);
        }
        
        for (int y = 0; y < 11; y++) {
        for (int x = 0; x < 11; x++) {
           transitionTable[y][x] = numToTransition(digitSegs.get(y), digitSegs.get(x));
        
        }}
        
        //transition table
        /*
        System.out.println("\n    0 1 2 3 4 5 6 7 8 9 off\n---------------------------");
        
        for (int y = 0; y < 11; y++) {
            if (y < 10)
                System.out.print(y + " | ");
            else
                System.out.print("off ");
            for (int x = 0; x < 11; x++) {
                System.out.print(transitionTable[y][x] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        */
        
        List<Long> primes = MF.getPrimesUnder(20000000);
        for (Long prime : primes) {
            if (prime < 10000000)
                continue;
            compute(prime.intValue());
        }
        System.out.println("sam: " + samTrans);
        System.out.println("max: " + maxTrans);
        System.out.println("diff: " + MF.abs(samTrans - maxTrans));
        System.out.println(samTrans - maxTrans);
        System.out.println(MF.getElapsedSeconds());
        
        
    }
    
    public static void compute(int start) {
        int number = start;
        int oldNum = -1;
        do {
            samTrans += sam(number);
            maxTrans += max(oldNum, number);
            
            oldNum = number;
            number = digitalRoot(number);
        } while (oldNum != number);
        maxTrans += max(number, -1);
    }
    
    
    
    public static ArrayList<ArrayList<Segment>> buildNumber(int num) {
        ArrayList<ArrayList<Segment>> digits = new ArrayList<ArrayList<Segment>>();
        while (num >= 10) {
            int dig = num % 10;
            num /= 10;
            digits.add(Problem315.digitSegs.get(dig));
        }
        return digits;
    }
    
    
    public static long sam(int num) {
        long segments = 0;
        
        while (num >= 10) {
            int dig = num % 10;
            num /= 10;
            segments += Problem315.numSegments[dig];
        }
        segments += Problem315.numSegments[num];
        return segments * 2;
    }
    
    public static long max(int from, int to) {
        long transitions = 0;
        
        if (from == -1 && to == -1) //one of the numbers or both is off (-1)
            return 0;
        else if (from == -1) {
            return sam(to) / 2;
        }
        else if (to == -1) {
            return sam(from) / 2;
        }
        
        while (from >= 10 && to >= 10) { //regular
            int fromDig = from % 10;
            from /= 10;
            int toDig = to % 10;
            to /= 10;
            
            transitions += transitionTable[fromDig][toDig];
        }
        
        if (from < 10 && to < 10) {
            transitions += transitionTable[from][to];
        }
        else if (from < 10 && to >= 10) {
            int toDig = to % 10;
            to /= 10;
            transitions += transitionTable[from][toDig];
            
            while (to >= 10) {
                toDig = to % 10;
                to /= 10;
                transitions += transitionTable[10][toDig];
            }
            transitions += transitionTable[10][to];
        }
        else if (from >= 10 && to < 10) {
            int fromDig = from % 10;
            from /= 10;
            transitions += transitionTable[fromDig][to];
            
            while (from >= 10) {
                fromDig = from % 10;
                from /= 10;
                transitions += transitionTable[fromDig][10];
            }
            transitions += transitionTable[from][10];
        }
        
        
        return transitions;
    }
    
    public static void printSegments(List<Segment> list) {
        String str = "";
        if (list.contains(Segment.TOP))
            str += " _ ";
        str += "\n";
        if (list.contains(Segment.TOP_LEFT))
            str += "|";
        else 
            str += " ";
        if (list.contains(Segment.MIDDLE))
            str += "_";
        else
            str += " ";
        if (list.contains(Segment.TOP_RIGHT))
            str += "|";
        str += "\n";
        if (list.contains(Segment.BOTTOM_LEFT))
            str += "|";
        else 
            str += " ";
        if (list.contains(Segment.BOTTOM))
            str += "_";
        else
            str += " ";
        if (list.contains(Segment.BOTTOM_RIGHT))
            str += "|";
        str += "\n";
        
        System.out.println(str);
    }
    
    public static int numToTransition(ArrayList<Segment> from, ArrayList<Segment> to) {
        int fromHasToDoesnt = 0;
        int toHasFromDoesnt = 0;
        
        for (Segment seg : from) {
            if (!to.contains(seg))
                fromHasToDoesnt++;
        }
        
        for (Segment seg : to) {
            if (!from.contains(seg))
                toHasFromDoesnt++;
        }
        
        return fromHasToDoesnt + toHasFromDoesnt;
    }
    
    /**
     * Returns the sum of digits (digital root) of a number
     * @param num
     * @return 
     */
    public static int digitalRoot(int num) {
        int sum = 0;
        while (num >= 10) {
            sum += num % 10;
            num /= 10;
        }
        sum += num;
        return sum;
    }
    
    public enum Segment {
        TOP, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, TOP_LEFT, MIDDLE;
    }
}
/*
sam: 63424722
max: 49799480
diff: 13625242
13625242
0.729498394
*/