package misctools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

/**
 * FH (File Helper) has methods to make dealing with files easier.
 * @author Calvin
 */
public class FH {

    public static String[] readFile(String url) {
        Scanner reader;
        List<String> lines = new ArrayList<>();

        try {
            reader = new Scanner(new File(url));
            while (reader.hasNextLine())
                lines.add(reader.nextLine());
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        return lines.toArray(new String[lines.size()]);
    }
    
    public static String[] readFile(File f) {
        Scanner reader;
        List<String> lines = new ArrayList<>();

        try {
            reader = new Scanner(f);
            while (reader.hasNextLine())
                lines.add(reader.nextLine());
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        return lines.toArray(new String[lines.size()]);
    }
    
    public static boolean writeFile(String data, String url) {
        
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(url), StandardCharsets.UTF_8);
            writer.write(data);
            writer.close();
            
        } catch (FileNotFoundException ex) { 
            ex.printStackTrace(); 
            return false;
        } catch (IOException ex) {
            ex.printStackTrace(); 
            return false;
        }
        return true;
    }
    
    public static boolean writeFile(String data[], String url) {
        
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(url), StandardCharsets.UTF_8);
            for (String line : data) {
                writer.write(line);
                writer.write("\n");
            }
            writer.close();
            
        } catch (FileNotFoundException ex) { 
            ex.printStackTrace(); 
            return false;
        } catch (IOException ex) {
            ex.printStackTrace(); 
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        String url = "C:\\Users\\Calvin\\Documents\\NetBeansFiles\\ChineseEnglishDictionary\\test.txt";
        String[] data = new String[] {"a","b","cde"};
        writeFile(data,url);
    }
}
