package problem013;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Problem013 {

    //Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
    //numbers.txt
    public static void main(String[] args) {
        
        String userDir = System.getProperty("user.dir");
        List<BigInteger> numbers = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(new File(userDir + "\\numbers.txt")))) {
            String line;
            while ((line = br.readLine()) != null)
                numbers.add(new BigInteger(line));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        BigInteger sum = new BigInteger("0");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
            sum = sum.add(numbers.get(i));
        }
        System.out.println("Sum: " + sum);
    }

}   //answer : 5537376230
    //(5537376230390876637302048746832985971773659831892672)
