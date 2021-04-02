package problem081;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mathtools.*;

public class WithDijkstra {
    
    public static void main(String[] args) {
        
        /*
        int[][] matrix =   {{131,673,234,103, 18},
                            {201, 96,342,965,150},
                            {630,803,746,422,111},
                            {537,699,497,121,956},
                            {805,732,524, 37,331}};
        */
        
        
        //build matrix
        Scanner reader = null;
        File curDir = new File("");
        List<String> lines = new ArrayList<>();
        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem081/matrix.txt"));
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
        
        MF.startTimer();
        
        
        Vertex[][] vertexMatrix = new Vertex[matrix.length][matrix[0].length];
        
        List<Vertex> verts = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        
        //verticies
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                Vertex toAdd = new Vertex("("+y+","+x+")", matrix[y][x]);
                verts.add(toAdd);
                vertexMatrix[y][x] = toAdd;
            }
        }
        
        //horizontal edges
        
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length - 1; x++) {
                Edge toAdd = new Edge(""+vertexMatrix[y][x+1].getNumber(), vertexMatrix[y][x], vertexMatrix[y][x+1], vertexMatrix[y][x+1].getNumber());
                edges.add(toAdd);
            }
        }
        
        //vertical edges
        for (int x = 0; x < matrix[0].length; x++) {
            for (int y = 0; y < matrix.length - 1; y++) {
                Edge toAdd = new Edge(""+vertexMatrix[y+1][x].getNumber(), vertexMatrix[y][x], vertexMatrix[y+1][x], vertexMatrix[y+1][x].getNumber());
                edges.add(toAdd);
            }
        }
        
        Graph graph = new Graph(verts, edges);
        DijkstraAlgorithm dAlg = new DijkstraAlgorithm(graph);
        dAlg.execute(vertexMatrix[0][0]);
        List<Vertex> path = dAlg.getPath(vertexMatrix[vertexMatrix.length-1][vertexMatrix[0].length-1]);
        double sum = 0.0;
        for (Vertex vt : path) {
            System.out.println(vt);
            sum += vt.getNumber();
        }
        System.out.println();
        System.out.println(sum);
        
        System.out.println("time: " + MF.getElapsedSeconds());
    }
}

/*
//testing
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        List<Vertex> verts = new ArrayList<>();
        verts.add(a);
        verts.add(b);
        verts.add(c);
        verts.add(d);
        verts.add(e);
        verts.add(f);
        verts.add(g);
        
        Edge ab = new Edge("ab", a, b, 60);
        Edge ad = new Edge("ad", a, d, 30);
        Edge ae = new Edge("ae", a, e, 50);
        Edge bc = new Edge("bc", b, c, 90);
        Edge bd = new Edge("bd", b, d, 10);
        Edge de = new Edge("de", d, e, 5);
        Edge eg = new Edge("eg", e, g, 90);
        Edge ef = new Edge("ef", e, f, 50);
        Edge df = new Edge("df", d, f, 80);
        Edge dc = new Edge("dc", d, c, 25);
        Edge cg = new Edge("cg", c, g, 80);
        Edge cf = new Edge("cf", c, f, 20);
        List<Edge> edges = new ArrayList<>();
        edges.add(ab);
        edges.add(ad);
        edges.add(ae);
        edges.add(bc);
        edges.add(bd);
        edges.add(de);
        edges.add(eg);
        edges.add(ef);
        edges.add(df);
        edges.add(dc);
        edges.add(cg);
        edges.add(cf);
        
        Graph graph = new Graph(verts, edges);
        DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
        da.execute(a);
        List<Vertex> path = da.getPath(g);
        
        for (Vertex vt : path)
            System.out.println(vt);
        

*/