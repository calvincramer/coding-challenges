package problem098;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mathtools.MF;
import misctools.FH;

public class Problem098 {
    
    /*
    By replacing each of the letters in the word CARE with 1, 2, 9, and 6 respectively, we form a square number: 1296 = 362. What is remarkable is that, 
    by using the same digital substitutions, the anagram, RACE, also forms a square number: 9216 = 962. 
    We shall call CARE (and RACE) a square anagram word pair and specify further that leading zeroes are not permitted, 
    neither may a different letter have the same digital value as another letter.

    Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, 
    find all the square anagram word pairs (a palindromic word is NOT considered to be an anagram of itself).

    What is the largest square number formed by any member of such a pair?

    NOTE: All anagrams formed must be contained in the given text file.
    */
    
    public static List<Integer> squares;
    public static int greatestSquare = 0;
    
    public static void main(String[] args) {
        
        MF.startTimer();
        
        File f = new File("");
        f = new File(f.getAbsolutePath() + "\\src\\problem098\\words.txt");
        String[] lines = FH.readFile(f.getAbsolutePath());
        
        List<String> words = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(",");
            words.addAll(Arrays.asList(split));
        }
        
        //take out the double quotes
        for (int i = 0; i < words.size(); i++)
            words.set(i, words.get(i).substring(1, words.get(i).length()-1));
        
        List<String> allAnagrams = new ArrayList<>();
        ArrayList<ArrayList<String>> anagrams = new ArrayList<ArrayList<String>>();
        
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            int numAnagrams = 0;
            ArrayList<String> tempAnagrams = new ArrayList<>();
            tempAnagrams.add(word);
            
            for (int j = 0; j < words.size(); j++) {
                if (i == j)
                    continue;
                if (areAnagrams(word, words.get(j))) {
                    numAnagrams++;
                    tempAnagrams.add(words.get(j));
                }
            }
            
            if (numAnagrams != 0) {
                //System.out.println(word + " :\t" + numAnagrams);
                allAnagrams.add(word);
                anagrams.add(tempAnagrams);
            }
        }
        
        //sort all anagrams by length
        Collections.sort(allAnagrams, new Comparator() {   
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String) {
                    String str1 = (String) o1;
                    String str2 = (String) o2;
                    
                    if (str1.length() == str2.length())
                        return 0;
                    if (str1.length() > str2.length())
                        return 1;
                    return -1;
                }
                return -1;
            }
        });
        
        //sort anagrams lexicographically (inside themselves)
        for (int i = 0; i < anagrams.size(); i++) {
            Collections.sort(anagrams.get(i));
        }
        
        //sort anagrams by length
        Collections.sort(anagrams, new Comparator() {   
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof ArrayList && o2 instanceof ArrayList) {
                    ArrayList arr1 = (ArrayList) o1;
                    ArrayList arr2 = (ArrayList) o2;
                    String first = (String) arr1.get(0);
                    String second = (String) arr2.get(0);
                    
                    if (first.length() == second.length())
                        return 0;
                    if (first.length() > second.length())
                        return 1;
                    return -1;
                }
                return -1;
            }
        });
        
        //remove duplicate anagram lists
        ArrayList<ArrayList<String>> uniqueAnagrams = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < anagrams.size(); i++) {
            if (!uniqueAnagrams.contains(anagrams.get(i)))
                uniqueAnagrams.add(anagrams.get(i));
        }
        
        //print unique anagrams
        for (ArrayList<String> anagramList : uniqueAnagrams) {
            MF.printList(anagramList);
        }
        
        //build squares to use
        squares = new ArrayList<>();
        long stop = (int)Math.pow(10, uniqueAnagrams.get(uniqueAnagrams.size()-1).get(0).length()+1);
        long n = 1;
        long nsqr = n*n;
        while (nsqr < stop) {
            squares.add((int)nsqr);
            n++;
            nsqr = n*n;
        }
        
        //remove square with duplicate digits
        List<Integer> goodSquares = new ArrayList<>();
        for (Integer sqr : squares)
            if (hasUniqueDigits(sqr))
                goodSquares.add(sqr);
        squares = goodSquares;
        
        
        
        
        //work
        //
        //
        //
        System.out.println("\n\n");
        
        for (ArrayList<String> anagramList : uniqueAnagrams)
            findSquares(anagramList.get(0), anagramList.get(1));
        
        System.out.println(MF.getElapsedSeconds());
        
    }
    
    public static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;
        //must have same characters
        int[] char1 = new int[26];
        int[] char2 = new int[26];
        for (int i = 0; i < str1.length(); i++)
            char1[Character.getNumericValue(str1.charAt(i)) - 10]++;
        for (int i = 0; i < str2.length(); i++)
            char2[Character.getNumericValue(str2.charAt(i)) - 10]++;
        
        for (int i = 0; i < char1.length; i++)
            if (char1[i] != char2[i])
                return false;
            
        //System.out.println(str1.charAt(i) + ": " + Character.getNumericValue(str1.charAt(i)));
        return true;
    }
    
    public static int convert(String start, String end, int num) {
        if (start.length() != end.length())
            return -1;
        if ((""+num).length() != start.length())
            return -2;
        
        //assuming the starting word does not have multiple characters that are the same
        String number = ""+num;
        StringBuilder newNumStr = new StringBuilder("");
        //pad with placeholder character
        for (int i = 0; i < start.length(); i++)
            newNumStr.append("#");
        
        for (int i = 0; i < start.length(); i++) {
            int endIndex = end.indexOf(start.charAt(i));
            if (endIndex < 0 || endIndex > newNumStr.length())
                return - 3;
            newNumStr.setCharAt(endIndex, number.charAt(i));
        }
        
        String str = newNumStr.toString();
        if (str.charAt(0) == '0')
            return -4;
        if (str.contains("#"))
            return -5;
        
        return Integer.parseInt(str);
    }
    
    public static void findSquares(String str1, String str2) {
        if (str1.length() != str2.length())
            return;
        
        int startIndex = 0;
        while ((squares.get(startIndex)+"").length() < str1.length())
            startIndex++;
        int endIndex = startIndex;
        while ((squares.get(endIndex)+"").length() == str1.length())
            endIndex++;
        
        for (int i = startIndex; i < endIndex; i++) {
            int result = convert(str1, str2, squares.get(i));
            if (MF.isPerfectSquare(result)) {
                if ( (int)Math.sqrt(squares.get(i)) > greatestSquare || (int)Math.sqrt(result) > greatestSquare) {
                    if ((int)Math.sqrt(squares.get(i)) > (int)Math.sqrt(result) ) {
                        greatestSquare = (int)Math.sqrt(squares.get(i));
                    } else {
                        greatestSquare = (int)Math.sqrt(result);
                    }
                    System.out.print(str1 + " : " + squares.get(i) + " : " + (int)Math.sqrt(squares.get(i)));
                    System.out.print(" --> " + str2 + " : " + result + " : " + (int)Math.sqrt(result));
                    System.out.println("\t\t" + greatestSquare);
                }
                
            }
        }
        
        
    }
    
    public static boolean hasUniqueDigits(int number) {
        String str = ""+number;
        boolean[] digitPresent = new boolean[10];   //0 thru 9
        
        for (char c : str.toCharArray()) {
            int digit = Character.getNumericValue(c);
            if (digit < 0 || digit > 9)
                return false;
            
            if (digitPresent[digit] == true)
                return false;
            
            digitPresent[digit] = true;
        }
        
        return true;
    }
}
//to pick digits, go through all possible squares of that many digits
//permutations of that starting square number is unecessary, 
//  b/c the permutations may/may not be square numbers. Even if the permutations are square numbers, we are already trying all of them.
//  also take out the square numbers that have repeated digits (violates multiple characters sharing same digit value)

//answer: BOARD : 17689 : 133 --> BROAD : 18769 : 137		137