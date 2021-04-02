package problem107;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mathtools.MF;
import misctools.FH;

public class Problem107 {
    
    /*
    The following undirected network consists of seven vertices and twelve edges with a total weight of 243.

    The same network can be represented by the matrix below.

            A	B	C	D	E	F	G
    A	0	16	12	21	0	0	0
    B	16	0	0	17	20	0	0
    C	12	0	0	28	0	31	0
    D	21	17	28	0	18	19	23
    E	0	20	0	18	0	0	11
    F	0	0	31	19	0	0	27
    G	0	0	0	23	11	27	0
    However, it is possible to optimise the network by removing some edges and still ensure that all points on the network remain connected. 
    The network which achieves the maximum saving is shown below. It has a weight of 93, representing a saving of 243 − 93 = 150 from the original network.


    Using network.txt (right click and 'Save Link/Target As...'), a 6K text file containing a network with forty vertices, and given in matrix form, 
    find the maximum saving which can be achieved by removing redundant edges whilst ensuring that the network remains connected.
    */
    public static void main(String[] args) {
        int[][] test = {{0,	16,     12,	21,	0,	0,	0},
                        {16,	0,	0,	17,	20,	0,	0},
                        {12,	0,	0,	28,	0,	31,	0},
                        {21,	17,	28,	0,	18,	19,	23},
                        {0,	20,	0,	18,	0,	0,	11},
                        {0,	0,	31,	19,	0,	0,	27},
                        {0,	0,	0,	23,	11,	27,	0}};
        
        int origWeight = totalWeight(test);
        minSpanGraph(test);
        int newWeight = totalWeight(test);
        
        System.out.println("orig: " + origWeight);
        System.out.println("new : " + newWeight);
        System.out.println("saved: " + (origWeight - newWeight));
        
        real();
        
    }
    
    public static void real() {
        System.out.println("\n\nreal ->");
        
        String[] lines = FH.readFile(new File("").getAbsolutePath() + "\\src\\problem107\\network.txt");
        int[][] graph = new int[40][40];
        for (int y = 0; y < lines.length; y++) {
            String[] elements = lines[y].split(",");
            for (int x = 0; x < elements.length; x++)
                if (!elements[x].equals("-"))
                    graph[y][x] = Integer.parseInt(elements[x]);
        }
        
        //MF.printGrid(graph);
        
        int origWeight = totalWeight(graph);
        minSpanGraph(graph);
        int newWeight = totalWeight(graph);
        
        System.out.println("orig: " + origWeight);
        System.out.println("new : " + newWeight);
        System.out.println("saved: " + (origWeight - newWeight));
    }
    
    //reverse delete algo
    //only for bidirectional graphs
    public static void minSpanGraph(int[][] graph) {
        List<Edge> edges = new ArrayList<>();
        //add all edges
        for (int y = 0; y < graph.length; y++)
            for (int x = 0; x <= y; x++)
                if (graph[y][x] != 0)
                    edges.add(new Edge(x,y,graph[y][x]));
        
        Collections.sort(edges);
        Collections.reverse(edges);
        
        for (int r = 0; r < edges.size(); r++) {
            //MF.printGrid(graph);
            
            Edge removed = edges.get(r);
            //System.out.print(removed);
            //try removing it
            graph[removed.to][removed.from] = 0;
            graph[removed.from][removed.to] = 0;
            
            if (!isConnected(graph)) {  //if disconnected, add back
                graph[removed.to][removed.from] = removed.weight;
                graph[removed.from][removed.to] = removed.weight;
            }
            else {
                //System.out.print("\tremoved");
            }
            //System.out.println("");
        }
    }
    
    //
    public static boolean isConnected(int[][] graph) {
        boolean[] visited = new boolean[graph.length];
        dfs(graph, visited, 0);
        for (boolean b : visited)
            if (b == false)
                return false;
        return true;
    }
    
    public static void dfs(int[][] graph, boolean[] visited, int current) {
        if (visited[current] == true)
            return;
        visited[current] = true;
        
        //for all neighbors
        for (int x = 0; x < graph.length; x++) {
            if (x == current)
                continue;
            if (graph[current][x] == 0)
                continue;
            if (visited[x] == false)
                dfs(graph, visited, x);
        }
    }
    
    public static int totalWeight(int[][] graph) {
        int total = 0;
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x <= y; x++) 
                total += graph[y][x];
        }
        return total;
    }
    
    public static int numNeighbors(int vertex, int[][] graph) {
        //definitionally (?) number of nonzero entries in graph along the vertex's row or column (assuming it's symmetric along main diagonal)
        int count = 0;
        if (vertex > graph.length)
            return -1;
        for (int x = 0; x < graph[0].length; x++)
            if (graph[vertex][x] != 0)
                count++;
        return count;
    }
    
    public static class Edge implements Comparable{
        int to;
        int from;
        int weight;

        public Edge(int to, int from, int weight) {
            this.to = to;
            this.from = from;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Edge) {
                Edge other = (Edge) o;
                if (this.weight > other.weight)
                    return 1;
                else if (this.weight < other.weight)
                    return -1;
                else 
                    return 0;
            }
            return -1;
        }
        
        @Override
        public String toString() {
            return "(" + this.from + ", " + this.to + ") " + this.weight;
        }
        
        
    }
}