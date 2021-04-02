package problem040;

public class Problem040 {

    /*
    0.12345678910111213141516171819202122232425262728293031323334353637383940414243...
    
    An irrational decimal fraction is created by concatenating the positive integers:

    0.123456789101112131415161718192021...

    It can be seen that the 12th digit of the fractional part is 1.

    If dn represents the nth digit of the fractional part, find the value of the following expression.

    d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
    */
    public static void main(String[] args) {
        
        String longString = "";
        int num = 1;
        
        while(longString.length() < 1000020) {
            longString += num;
            num++;
        }
        
        long sum = 1;
        sum *= Character.getNumericValue(longString.charAt(1-1));
        sum *= Character.getNumericValue(longString.charAt(10-1));
        sum *= Character.getNumericValue(longString.charAt(100-1));
        sum *= Character.getNumericValue(longString.charAt(1000-1));
        sum *= Character.getNumericValue(longString.charAt(10000-1));
        sum *= Character.getNumericValue(longString.charAt(100000-1));
        sum *= Character.getNumericValue(longString.charAt(1000000-1));
        
        System.out.println("Sum: " + sum);
        
    }
    //Sum: 210
    //run-time: 1 minute
    
}
