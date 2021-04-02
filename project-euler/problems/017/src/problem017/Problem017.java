package problem017;

public class Problem017 {

    public static void main(String[] args) {
        int totalCharacters = 0;
        for (int i = 1; i <= 1000; i++) {
            String str = numberToString(i);
            int numAlphaChars = countAlphaChars(str);
            System.out.println(str + "    \t" + numAlphaChars);
            totalCharacters += numAlphaChars;
        }
        System.out.println("\nTotal chars: " + totalCharacters);
    }
    
    private static int countAlphaChars(String str) {
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i)) && str.charAt(i) != '-')
                total += 1;
        }
        return total;
    }
    
    private static String numberToString(int n) {
        if (n < 10)
            return singleDigitToString(n);
        if (n < 20)
            return tensToString(n);
        if (n < 100)
            return lessThan100ToString(n);
        if (n < 1000)
            return lessThan1000ToString(n);
        if (n == 1000)
            return "one thousand";
            
        return "NOT SUPPORTED YET";
    }
    
    private static String lessThan1000ToString(int n) {
        int hundredDigit = n / 100;
        String s = "";
        switch (hundredDigit) {
           case 1: s = "one hundred"; break;
           case 2: s = "two hundred"; break;
           case 3: s = "three hundred"; break;
           case 4: s = "four hundred"; break;
           case 5: s = "five hundred"; break;
           case 6: s = "six hundred"; break;
           case 7: s = "seven hundred"; break;
           case 8: s = "eight hundred"; break;
           case 9: s = "nine hundred"; break;
           default: s = "ERROR HUNDRED"; break;
       }
       if (n % 100 != 0) //one hundred and something
       {
           s += " and ";
           s += lessThan100ToString(n % 100);
       }
       return s.trim();
    }
    
    private static String lessThan100ToString(int n) {
        int onesDigit = n % 10;
        int tensDigit = n / 10;
        
       String s = "";
       switch (tensDigit) {
           case 0: return singleDigitToString(onesDigit);
           case 1: return tensToString(n);
           case 2: s = "twenty"; break;
           case 3: s = "thirty"; break;
           case 4: s = "forty"; break;
           case 5: s = "fifty"; break;
           case 6: s = "sixty"; break;
           case 7: s = "seventy"; break;
           case 8: s = "eighty"; break;
           case 9: s = "ninety"; break;
           default: s = "ERROR"; break;
       }
       switch (onesDigit) {
            case 0: s += ""; break;
            case 1: s += "-one"; break;
            case 2: s += "-two"; break;
            case 3: s += "-three"; break;
            case 4: s += "-four"; break;
            case 5: s += "-five"; break;
            case 6: s += "-six"; break;
            case 7: s += "-seven"; break;
            case 8: s += "-eight"; break;
            case 9: s += "-nine"; break;
            default:s += "-errrrrrrror"; break;
        }
       return s.trim();
    }
    private static String tensToString(int n) {
        String s;
        switch (n) {
            case 10: s = "ten"; break;
            case 11: s = "eleven"; break;
            case 12: s = "twelve"; break;
            case 13: s = "thirteen"; break;
            case 14: s = "fourteen"; break;
            case 15: s = "fifteen"; break;
            case 16: s = "sixteen"; break;
            case 17: s = "seventeen"; break;
            case 18: s = "eighteen"; break;
            case 19: s = "nineteen"; break;
            default:s = null; break;
        }
        return s;
    }
    private static String singleDigitToString(int n) {
        String s;
        switch (n) {
            case 0: s = "zero"; break;
            case 1: s = "one"; break;
            case 2: s = "two"; break;
            case 3: s = "three"; break;
            case 4: s = "four"; break;
            case 5: s = "five"; break;
            case 6: s = "six"; break;
            case 7: s = "seven"; break;
            case 8: s = "eight"; break;
            case 9: s = "nine"; break;
            default:s = null; break;
        }
        return s;
    }
}
//answer : 21124
