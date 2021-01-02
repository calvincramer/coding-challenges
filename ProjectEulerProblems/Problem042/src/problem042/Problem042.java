package problem042;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem042 {

   /*
    The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:

    1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

    By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. 
    For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.

    Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, how many are triangle words?
    */
    public static void main(String[] args) {
        
        MathFunctions.startTimer();
        
        Scanner reader = null;
        File curDir = new File("");
        List<String> words = new ArrayList<>();
        
        try 
        {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem042/words.txt"));
            reader.useDelimiter(",");
            while (reader.hasNext()) {
                String nam = reader.next();
                nam = nam.substring(1, nam.length()-1);
                //System.out.println(nam);
                words.add(nam);
            }
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        int[] triNums = new int[200];   //200th triangle number is 20100. 773 'Z's approach this limit
        for (int i = 0; i < triNums.length; i++)
            triNums[i] = triangleNumber(i);
        
        int numTriangleWords = 0;
        List<String> triangleWords = new ArrayList<>();
        
        for (String word : words) {
            int wordScore = 0;
            for (char c : word.toCharArray())
                wordScore += Character.getNumericValue(c) - 9;  //A == 10, but A needs to be 1
            
            //System.out.println(word + "\t\tScore: " + wordScore);
            
            //test if word is triangle number
            boolean isTriNum = false;
            for (int i = 0; i < triNums.length; i++) {
                if (wordScore == triNums[i]) {
                    isTriNum = true;
                    break;
                }
                if (triNums[i] > wordScore)
                    break;
            }
            
            if (isTriNum) {
                numTriangleWords++;
                triangleWords.add(word);
            }
        }
        
        //for (String triWord : triangleWords) 
        //    System.out.println(triWord);
        
        System.out.println("Total: " + numTriangleWords);
        
        System.out.println("Time: " + MathFunctions.getElapledTime() + "ms");
        

    }
    //answer: 162

    public static int triangleNumber(int nthNumber) {
        return (nthNumber+1)*nthNumber / 2;
    }
}
