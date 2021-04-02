package problem022;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem022 {

    /*
    Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. 
    Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.

    For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. 
    So, COLIN would obtain a score of 938 × 53 = 49714.

    What is the total of all the name scores in the file?
    */
    public static void main(String[] args) {
        Scanner reader = null;
        File curDir = new File("");
        List<String> names = new ArrayList<>();
        
        //System.out.println(Character.getNumericValue('A'));
        //System.out.println(Character.getNumericValue('B'));
        //System.out.println(Character.getNumericValue('Z'));

        try 
        {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem022/names.txt"));
            reader.useDelimiter(",");
            while (reader.hasNext()) {
                String nam = reader.next();
                nam = nam.substring(1, nam.length()-1);
                //System.out.println(nam);
                names.add(nam);
            }
        
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        //sort names
        //selection sort
        for (int i = 0; i < names.size(); i++) {
            String smallestString = names.get(i);
            int smallestIndex = i;
            for (int j = i; j < names.size(); j++) {
                if (names.get(j).compareTo(names.get(smallestIndex)) < 0)
                    smallestIndex = j;
            }
            //swap i with smallest index
            String temp = names.get(i);
            names.set(i, names.get(smallestIndex));
            names.set(smallestIndex, temp);
        }
        
        
        long totalScore = 0;
        for (int i = 0; i < names.size(); i++) {
            long score = scoreName(names.get(i), i+1);
            System.out.println(names.get(i) + " " + score);
            totalScore += score;
        }
        System.out.println("\nTotal: " + totalScore);

    }
    //answer: 871198282
    
    public static int scoreName(String name, int position) {
        int totalOfChars = 0;
        for (int i = 0; i < name.length(); i++) {
            totalOfChars += Character.getNumericValue(name.charAt(i)) - 9; //A == 10, we want A == 1
        }
        return totalOfChars * position;
    }

}
