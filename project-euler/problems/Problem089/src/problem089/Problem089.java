package problem089;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import misctools.FH;

public class Problem089 {

    /*
    For a number written in Roman numerals to be considered valid there are basic rules which must be followed. Even though the rules allow some numbers to be expressed in more than one way there is always a "best" way of writing a particular number.
    For example, it would appear that there are at least six ways of writing the number sixteen:

    IIIIIIIIIIIIIIII
    VIIIIIIIIIII
    VVIIIIII
    XIIIIII
    VVVI
    XVI

    However, according to the rules only XIIIIII and XVI are valid, and the last example is considered to be the most efficient, as it uses the least number of numerals.
    The 11K text file, roman.txt (right click and 'Save Link/Target As...'), contains one thousand numbers written in valid, but not necessarily minimal, Roman numerals; see About... Roman Numerals for the definitive rules for this problem.
    Find the number of characters saved by writing each of these in their minimal form.
    Note: You can assume that all the Roman numerals in the file contain no more than four consecutive identical units.
    */
    public static void main(String[] args) {
        
        String[] romanNumbers = FH.readFile(new File("").getAbsolutePath() + "/src/problem089/roman.txt");
        
        int totalChars = 0;
        int charSaved = 0;
        
        for (String s : romanNumbers)
            totalChars += s.length();
        
        System.out.println(totalChars);
        
        for (String s : romanNumbers) {
            int intVal = romanNumeralToInt(s);
            String rnC = intToRomanNumeral(intVal);
            System.out.print(fixedWidthString(s, 20) + "\t= " + intVal + "\t" + rnC + "\t\t");
            if (!s.equals(rnC)) {
                System.out.println("Different");
                charSaved += s.length() - rnC.length();
            }
            else
                System.out.println();
            
            if (rnC.length() > s.length()) {
                System.out.println("ERRRRRRRORRRRRRRRRRRR");
                System.exit(0);
            }
        }

        System.out.println("Chars saved: ");
        System.out.println(charSaved);
    }
    
    public static int romanNumeralToInt(String rn) {
        
        if (rn == null || rn.length() == 0)
            return 0;
        
        //convert to romanchar string
        List<RomanChar> rcStr = new ArrayList<>();
        for (int i = 0; i < rn.length(); i++) {
            rcStr.add(new RomanChar(rn.charAt(i)));
        }
        
        int value = 0;
        
        while (rcStr.size() > 0) {
            if (rcStr.size() == 1) {
                value += rcStr.get(0).getValue();
                rcStr.remove(0);
            }
            else if (rcStr.get(0).getValue() >= rcStr.get(1).getValue()) {
                value += rcStr.get(0).getValue();
                rcStr.remove(0);
            }
            else {
                value += rcStr.get(1).getValue() - rcStr.get(0).getValue();
                rcStr.remove(0);
                rcStr.remove(0);
            }
        }
        return value;
    }
    
    public static String intToRomanNumeral(int n) {
        
        String s = "";
        while (n >= 1000) {
            s += "M";
            n -= 1000;
        }
        while (n >= 900) {
            s += "CM";
            n -= 900;
        }
        while (n >= 500) {
            s += "D";
            n -= 500;
        }
        while (n >= 400) {
            s += "CD";
            n -= 400;
        }
        while (n >= 100) {
            s += "C";
            n -= 100;
        }
        while (n >= 90) {
            s += "XC";
            n -= 90;
        }
        while (n >= 50) {
            s += "L";
            n -= 50;
        }
        while (n >= 40) {
            s += "XL";
            n -= 40;
        }
        while (n >= 10) {
            s += "X";
            n -= 10;
        }
        while (n >= 9) {
            s += "IX";
            n -= 9;
        }
        while (n >= 5) {
            s += "V";
            n -= 5;
        }
        while (n >= 4) {
            s += "IV";
            n -= 4;
        }
        while (n >= 1) {
            s += "I";
            n -= 1;
        }
        return s;
    }
    
    public static class RomanChar {
        

        private final int value;
        
        public RomanChar(final int value) {
            this.value = value;
        }
        
        public RomanChar(char ch) {
            switch (ch) {
                case 'I': this.value = 1; break;
                case 'V': this.value = 5; break;
                case 'X': this.value = 10; break;
                case 'L': this.value = 50; break;
                case 'C': this.value = 100; break;
                case 'D': this.value = 500; break;
                case 'M': this.value = 1000; break;
                default : this.value = 0; break;
            }
        }
        
        public int getValue() {
            return this.value;
        }
    }
    
    public static String fixedWidthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }

}
/*
Chars saved: 
743
*/