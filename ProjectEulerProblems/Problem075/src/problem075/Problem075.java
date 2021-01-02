package problem075;

import java.util.ArrayList;
import java.util.List;

public class Problem075 {

    /*
    It turns out that 12 cm is the smallest length of wire that can be bent to form an integer sided right angle triangle in exactly one way, but there are many more examples.

    12 cm: (3,4,5)
    24 cm: (6,8,10)
    30 cm: (5,12,13)
    36 cm: (9,12,15)
    40 cm: (8,15,17)
    48 cm: (12,16,20)

    In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle, and other lengths allow more than one solution to be found; for example, using 120 cm it is possible to form exactly three different integer sided right angle triangles.

    120 cm: (30,40,50), (20,48,52), (24,45,51)

    Given that L is the length of the wire, for how many values of L ≤ 1,500,000 can exactly one integer sided right angle triangle be formed?
    */
    public static void main(String[] args) {
        
        int only1 = 0;
        int count = 0;
        
        for (long l = 12; l <= 1500000; l += 2) {
            
            int numGood = 0;
            
            for (long a = 1; a <= l / 4; a++) {
                double b = (l*l - (2*l*a))*1.0/(2*l-2*a);
                double c = l - a - b;
                
                //System.out.println(l + " : " + a + ", " + b + ", " + c);
                
                if ((long)(b) - b == 0 && (long)(c) - c == 0) {
                    //System.out.println(l + " : " + a + ", " + b + ", " + c);
                    numGood++;
                    if (numGood > 1)
                        break;
                }
                
                /*
                if (a + b + c == l && (a*a) + (b*b) == (c*c)) {
                    numGood++;
                    if (numGood > 1)
                        break;
                }
                */

                
            }
            if (numGood == 1) {
                //passed.add(l);
                
                only1++;
                count++;
                if (count > 1000) {
                    count = 0;
                    System.out.println(l);
                }
            }
        }
        System.out.println("Total: " + only1);
        
        
        
        //for (long n : passedA) 
        //    System.out.println(n);
    }
        
    public static boolean onlyOneRightTri(int l) {
        int numIntRight = 0;
            
        for (int a = 1; a < l; a++) {
        for (int b = 1; b < a; b++) {
                int c = l - a - b;
                if (a + b + c == l && (a*a)+(b*b)==(c*c) ) {
                    //System.out.println(l + " : " + a + ", " + b + ", " + c);
                    numIntRight++;
                    if (numIntRight > 1)
                        return false;
                }
        }}
        return numIntRight == 1;
            
    }
    
}
//answer (wrong) 154903
//odd length perimeter right triangles appear to never have only one way to form an integer sided right tri
