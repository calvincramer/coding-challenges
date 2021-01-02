package problem102;

import java.awt.Point;
import java.awt.Polygon;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Test2 {

    public static void main(String[] args) {
        Scanner reader = null;              //read file
        File curDir = new File("");
        List<String> lines = new ArrayList<>();
        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem102/triangles.txt"));
            while (reader.hasNextLine())
                lines.add(reader.nextLine());
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        
        List<Polygon> triangles = new ArrayList<>();       //build triangles
        
        for (String line : lines) {
            String[] nums = line.split(",");
            int[] numbers = new int[nums.length];
            for (int i = 0; i < nums.length; i++)
                numbers[i] = Integer.parseInt(nums[i]);
            Polygon temp = new Polygon();
            temp.addPoint(numbers[0], numbers[1]);
            temp.addPoint(numbers[2], numbers[3]);
            temp.addPoint(numbers[4], numbers[5]);
            triangles.add(temp);
        }
        
        int numContainOrigin = 0;
        Point origin = new Point(0,0);
        
        for (Polygon tri : triangles) {
            boolean cont = tri.contains(origin);
            if (cont)
                numContainOrigin++;
            
            System.out.println(tri.toString() + "\t\t" + cont);
        }
        
        System.out.println(numContainOrigin);

    }
}//228 correct answer

/*
java.awt.Polygon@e9e54c2		true
java.awt.Polygon@65ab7765		false
java.awt.Polygon@1b28cdfa		false
java.awt.Polygon@eed1f14		true
java.awt.Polygon@7229724f		false
java.awt.Polygon@4c873330		false
java.awt.Polygon@119d7047		false
java.awt.Polygon@776ec8df		true
java.awt.Polygon@4eec7777		false
java.awt.Polygon@3b07d329		false
java.awt.Polygon@41629346		true
java.awt.Polygon@404b9385		false
java.awt.Polygon@6d311334		false
java.awt.Polygon@682a0b20		false
java.awt.Polygon@3d075dc0		false
java.awt.Polygon@214c265e		false
java.awt.Polygon@448139f0		false
java.awt.Polygon@7cca494b		true
java.awt.Polygon@7ba4f24f		false
java.awt.Polygon@3b9a45b3		false
java.awt.Polygon@7699a589		true
java.awt.Polygon@58372a00		false
java.awt.Polygon@4dd8dc3		false
java.awt.Polygon@6d03e736		true
java.awt.Polygon@568db2f2		false
java.awt.Polygon@378bf509		false
java.awt.Polygon@5fd0d5ae		false
java.awt.Polygon@2d98a335		false
java.awt.Polygon@16b98e56		false
java.awt.Polygon@7ef20235		false
java.awt.Polygon@27d6c5e0		false
java.awt.Polygon@4f3f5b24		false
java.awt.Polygon@15aeb7ab		false
java.awt.Polygon@7b23ec81		false
java.awt.Polygon@6acbcfc0		false
java.awt.Polygon@5f184fc6		false
java.awt.Polygon@3feba861		false
java.awt.Polygon@5b480cf9		false
java.awt.Polygon@6f496d9f		false
java.awt.Polygon@723279cf		true
java.awt.Polygon@10f87f48		false
java.awt.Polygon@b4c966a		true
java.awt.Polygon@2f4d3709		false
java.awt.Polygon@4e50df2e		false
java.awt.Polygon@1d81eb93		true
*/