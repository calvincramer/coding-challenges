package mathtools;

import java.util.List;


public class Graph {

    private final List<Vertex> verticies;
    private final List<Edge> edges;
    
    public Graph(List<Vertex> verticies, List<Edge> edges) {
        this.verticies = verticies;
        this.edges = edges;
    }

    public List<Vertex> getVerticies() {
        return verticies;
    }

    public List<Edge> getEdges() {
        return edges;
    }
    
    
}
