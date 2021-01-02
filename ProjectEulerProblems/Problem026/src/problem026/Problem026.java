package problem026;

import java.math.BigDecimal;

public class Problem026 {

    /*
    A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:

    1/2	= 	0.5
    1/3	= 	0.(3)
    1/4	= 	0.25
    1/5	= 	0.2
    1/6	= 	0.1(6)
    1/7	= 	0.(142857)
    1/8	= 	0.125
    1/9	= 	0.(1)
    1/10	= 	0.1
    Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.

    Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
    */
    public static void main(String[] args) {
        BigDecimal res;
        int largestCycle = 0;
        int largestCycleDenom = 0;
        String strLargest = "";
        
        for (int d = 592; d < 1000; d++) {
            
            res = BigDecimal.ONE.divide(new BigDecimal(d), 2000, BigDecimal.ROUND_DOWN);
            
            System.out.println("1/" + d + " = " + res.toString());
            String largest = findLargestCycle(res.toString().substring(2));      //strip off the 1.###### to get fractional part
            System.out.println("Largest cycle: " + largest);
            System.out.println();
            
            if (largest.length() > largestCycle) {
                largestCycle = largest.length();
                largestCycleDenom = d;
                strLargest = largest;
            }
        }
        
        System.out.println();
        System.out.println("Largest of them all: 1/" + largestCycleDenom);
        System.out.println("Length: " + largestCycle);
        System.out.println(strLargest);
        
    }
    //answer : 1/983 has largest repeating cycle (length 982)
    //cycle is:
    //0010172939979654120040691759918616480162767039674465920651068158697863682604272634791454730417090539165818921668362156663275686673448626653102746693794506612410986775178026449643947100712105798575788402848423194303153611393692777212614445574771108850457782299084435401831129196337741607324516785350966429298067141403865717192268565615462868769074262461851475076297049847405900305188199389623601220752797558494404883011190233977619532044760935910478128179043743641912512716174974567650050864699898270600203458799593082400813835198372329603255340793489318413021363173957273652085452695829094608341810783316378433367243133265513733468972533062054933875890132248219735503560528992878942014242115971515768056968463886063072227873855544252288911495422177009155645981688708036622583926754832146490335707019328585961342828077314343845371312309257375381485249237029501525940996948118006103763987792472024415055951169888097660223804679552390640895218718209562563580874872838250254323499491353
    
    public static String findLargestCycle(String str) {
        
        String largestCycle = str;
        int largest = 0;
        
        //efficeniency
        char testChar = str.charAt(str.length()-1);
        int d = str.length() - 1;
        for (; d >= 0; d--) {
            if (testChar != str.charAt(d))
                break;
        }
        if (d < str.length() / 2)
            return "" + testChar;
        
        for (int start = 0; start < str.length(); start++) {
            for (int length = 1; length <= str.length() - start; length++) {
                
                if (start + length > str.length() - 1 - length)
                    continue;
                
                String test = str.substring(start, start+length);
                
                
                //test to see if test is a repeating pattern
                //only check right of start
                boolean isRepeating = true;
                int testIndex = 0;
                
                for(int i = start; i < str.length(); i++) {
                    if (test.charAt(testIndex) != str.charAt(i)) {
                        isRepeating = false;
                        break;
                    }
                    testIndex = (testIndex + 1) % test.length(); //cycle
                }
                
                if (isRepeating) 
                {
                    //check if its a multiple of the largest cycle
                    if (test.length() > largestCycle.length()) {
                        boolean testIsUnique = false;
                        for (int i = 0; i < largestCycle.length(); i++) {
                            if (test.charAt(i) != largestCycle.charAt(i)) {
                                testIsUnique = true;
                                break;
                            }
                        }
                        if (testIsUnique) {
                            //System.out.println(test + "\t" + "REPEATS");
                            largestCycle = test;
                            largest = test.length();
                        }
                        //else is just a multiple
                    }
                    else if (test.length() == largestCycle.length()) {
                        //we dont care
                    }
                    else {
                        //System.out.println(test + "\t" + "REPEATS");
                        largestCycle = test;
                        largest = test.length();
                    }
                } 
                
            }
        }
        
        if (largestCycle.equals(str))   //return nothing if no cycle was found
            return "";
        
        return largestCycle;
    }

}
