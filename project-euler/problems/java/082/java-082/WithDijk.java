package problem082;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mathtools.DijkstraAlgorithm;
import mathtools.Edge;
import mathtools.Graph;
import mathtools.MF;
import mathtools.Vertex;


public class WithDijk {

    public static void main(String[] args) {
        
        
        //build matrix
        Scanner reader = null;
        File curDir = new File("");
        List<String> lines = new ArrayList<>();
        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem082/matrix.txt"));
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
        
        //horizontal edges
        
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length - 1; x++) {
                Edge toAdd = new Edge(""+vertexMatrix[y][x+1].getNumber(), vertexMatrix[y][x], vertexMatrix[y][x+1], vertexMatrix[y][x+1].getNumber());
                edges.add(toAdd);
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
        List<Vertex> minPath = null;
        double minPathLength = Double.MAX_VALUE;
        
        for (int start = 0; start < 30; start++) {      //answer starts at (25,0) start < matrix.length
                
                DijkstraAlgorithm dAlg = new DijkstraAlgorithm(graph);
                dAlg.execute(vertexMatrix[start][0]);   //start
                
            for (int end = 0; end < matrix.length; end++) {
                
                List<Vertex> path = dAlg.getPath(vertexMatrix[end][vertexMatrix[0].length-1]);    //end
                double length = 0.0;
                for (Vertex vt : path)
                    length += vt.getNumber();
                
                if (length < minPathLength) {
                    minPathLength = length;
                    minPath = path;
                    System.out.println();
                    printPath(minPath);
                }
                
            }
        }
        
        System.out.println("\n\nminimum");
        printPath(minPath);
        System.out.println("\t"+minPathLength);
        System.out.println("time: " + MF.getElapsedSeconds());
        
        //visual representation
        boolean[][] pathGrid = new boolean[vertexMatrix.length][vertexMatrix[0].length];
        for (Vertex vert : minPath) {
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
/*
minimum
Vertex: [(29,0)][6223.0]
Vertex: [(29,1)][2099.0]
Vertex: [(29,2)][2700.0]
Vertex: [(29,3)][589.0]
Vertex: [(29,4)][4716.0]
Vertex: [(29,5)][8333.0]
Vertex: [(29,6)][1362.0]
Vertex: [(29,7)][5007.0]
Vertex: [(29,8)][2753.0]
Vertex: [(28,8)][478.0]
Vertex: [(27,8)][4341.0]
... (ommitted)
Vertex: [(24,75)][1037.0]
Vertex: [(23,75)][1310.0]
Vertex: [(22,75)][966.0]
Vertex: [(22,76)][828.0]
Vertex: [(22,77)][3274.0]
Vertex: [(22,78)][1712.0]
Vertex: [(22,79)][3446.0]
	260324.0
time: 47.602639698

................................................................................
................................................................................
................................................................................
................................................................................
.................................***.......................................*****
...............................***.*.......................................*....
..............*****......*******...***.....................................*....
.............**...********...........**..................**********...******....
...........***........................****...............*........*****.........
........****.............................********.......**......................
........*.......................................*********.......................
*********.......................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
*/
