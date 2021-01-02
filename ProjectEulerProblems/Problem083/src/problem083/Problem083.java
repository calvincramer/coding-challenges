package problem083;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mathtools.*;

public class Problem083 {

    /*
    In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by moving left, right, up, and down, is indicated in bold red and is equal to 2297.
    */
    public static void main(String[] args) {

        //build matrix
        
        Scanner reader = null;
        File curDir = new File("");
        List<String> lines = new ArrayList<>();
        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem083/matrix.txt"));
            while (reader.hasNextLine())
                lines.add(reader.nextLine());
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        int[][] matrix = new int[80][80];
        for (int y = 0; y < 80; y++) {
            String line = lines.get(y);
            String[] nums = line.split(",");
            for (int x = 0; x < 80; x++)
                matrix[y][x] = Integer.parseInt(nums[x]);
        }
              
        /*
        int[][] matrix =   {{131,673,234,103, 18},
                            {201, 96,342,965,150},
                            {630,803,746,422,111},
                            {537,699,497,121,956},
                            {805,732,524, 37,331}};
        */
        
        
        MF.startTimer();
        
        
        Vertex[][] vertexMatrix = new Vertex[matrix.length][matrix[0].length];
        
        List<Vertex> verts = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        
        //verticies
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                Vertex toAdd = new Vertex("("+y+","+x+")", matrix[y][x]);
                toAdd.y = y;
                toAdd.x = x;
                verts.add(toAdd);
                vertexMatrix[y][x] = toAdd;
                vertexMatrix[y][x].y = y;
                vertexMatrix[y][x].x = x;
            }
        }
        
        //horizontal edges (left and right)
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length - 1; x++) {
                Edge toAddRight = new Edge(""+vertexMatrix[y][x+1].getNumber(), vertexMatrix[y][x], vertexMatrix[y][x+1], vertexMatrix[y][x+1].getNumber());
                edges.add(toAddRight);
                Edge toAddLeft = new Edge(""+vertexMatrix[y][x].getNumber(), vertexMatrix[y][x+1], vertexMatrix[y][x], vertexMatrix[y][x].getNumber());
                edges.add(toAddLeft);
            }
        }
        
        //vertical edges    (up and down)
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length - 1; y++) {
                Edge toAddDown = new Edge(""+vertexMatrix[y+1][x].getNumber(), vertexMatrix[y][x], vertexMatrix[y+1][x], vertexMatrix[y+1][x].getNumber());
                edges.add(toAddDown);
                Edge toAddUp = new Edge(""+vertexMatrix[y][x].getNumber(), vertexMatrix[y+1][x], vertexMatrix[y][x], vertexMatrix[y][x].getNumber());
                edges.add(toAddUp);
            }
        }
        
        
        Graph graph = new Graph(verts, edges);
        DijkstraAlgorithm dAlg = new DijkstraAlgorithm(graph);
        dAlg.execute(vertexMatrix[0][0]);
        List<Vertex> path = dAlg.getPath(vertexMatrix[vertexMatrix.length-1][vertexMatrix[0].length-1]);
        
        double length = 0.0;
        
        for (Vertex vt : path) {
            length += vt.getNumber();
        }
        
        System.out.println("minimum");
        printPath(path);
        System.out.println("\t"+length);
        System.out.println("time: " + MF.getElapsedSeconds());
        
        //visual representation
        boolean[][] pathGrid = new boolean[vertexMatrix.length][vertexMatrix[0].length];
        for (Vertex vert : path) {
            pathGrid[vert.y][vert.x] = true;
        }
        System.out.println();
        for (int y = 0; y < pathGrid.length; y++) {
            for (int x = 0; x < pathGrid[0].length; x++) {
                if (pathGrid[y][x] == false)
                    System.out.print('.');
                else
                    System.out.print('*');
            }
            System.out.println();
        }
    }
    
    public static void printPath(List<Vertex> path) {
        if (path == null || path.size() == 0)
            System.out.println("empty path");
        for (Vertex v : path)
            System.out.println(v.toString());
    }
}
