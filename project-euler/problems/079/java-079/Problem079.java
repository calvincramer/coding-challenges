package problem079;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem079 {

    /*
    A common security method used for online banking is to ask the user for three random characters from a passcode. For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.
    The text file, keylog.txt, contains fifty successful login attempts.
    Given that the three characters are always asked for in order, analyse the file so as to determine the shortest possible secret passcode of unknown length.
    */
    public static void main(String[] args) {
        Scanner reader = null;
        File curDir = new File("");
        List<String> logins = new ArrayList<>();

        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem079/keylog.txt"));
            while (reader.hasNextLine())
                logins.add(reader.nextLine());
        
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        
        //did it by hand
        
    }

}
//answer: 73162890
