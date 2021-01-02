package mathtools;


public class Edge {

    private String name;
    private Vertex start;
    private Vertex end;
    private double weight;
    
    public Edge(String name, Vertex start, Vertex end, double weight) {
        this.name = name;
        if (this.name.isEmpty())
            this.name = null;
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        String s = "Edge ";
        if (this.name != null)
            s += "[" + this.name + "]";
        if (this.start == null)
            s += " null -> ";
        else 
            s += " " + start.getName() + " -> ";
        if (this.end == null)
            s += "null";
        else 
            s += this.end.getName();
        return s;
    }

    public String getName() {
        return name;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }
    
    
}
