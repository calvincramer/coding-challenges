package problem099;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mathtools.MF;

public class Problem099 {

    /*
    Comparing two numbers written in index form like 2^11 and 3^7 is not difficult, as any calculator would confirm that 2^11 = 2048 < 3^7 = 2187.

    However, confirming that 632382518061 > 519432525806 would be much more difficult, as both numbers contain over three million digits.

    Using base_exp.txt (right click and 'Save Link/Target As...'), a 22K text file containing one thousand lines with a base/exponent pair on each line, determine which line number has the greatest numerical value.

    NOTE: The first two lines in the file represent the numbers in the example given above.
    */
    public static void main(String[] args) {
        File curDir = new File("");
        List<String> lines = new ArrayList<>();
        List<BigInteger> bases = new ArrayList<>();
        List<Integer> exponents = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem099/base_exp.txt"));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] expBase = line.split(",");
                bases.add(new BigInteger(expBase[0]));
                exponents.add(Integer.parseInt(expBase[1]));
            }
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        //for (int i = 0; i < bases.size(); i++)
        //    System.out.println(bases.get(i).toString() + " ^ " + exponents.get(i));
        
        BigInteger max = BigInteger.ZERO;
        int maxLine = -1;
        double totalTime = 0.0;
        
        for (int line = 0; line < bases.size(); line++) {
            System.out.print("Line: " + line);
            MF.startTimer();
            BigInteger result = bases.get(line).pow(exponents.get(line));
            totalTime += MF.getElapsedSeconds();
            
            if (result.compareTo(max) > 0) {
                max = result;
                maxLine = line;
            }
            
            System.out.println("\t\ttime= " + MF.getElapsedSeconds() + "\t\tmax line so far: " + maxLine);
            
        }
        System.out.println("Max line num: " + maxLine);
        System.out.println("Total time: " + totalTime);
    }
    
}
//answer: line 708 (actually 709 because the first line is counted as 1, whereas here it is 0)