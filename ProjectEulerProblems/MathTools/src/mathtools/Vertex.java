package mathtools;


public class Vertex {
    private String name;
    private double number;
    
    public int y;
    public int x;
    
    public int numerator;
    public int denomonator;
    
    public Vertex(String name) {
        this.name = name;
        this.number = 0.0;
    }
    
    public Vertex(double number) {
        this.number = number;
        this.name = null;
    }
    
    public Vertex(String name, double number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }
    
    @Override
    public String toString() {
        if (name == null && number == 0.0)
            return "undefined";
        
        String s = "Vertex: ";
        if (name != null)
            s += "[" + this.name + "]";
        if (number != 0.0)
            s += "[" + this.number + "]";
        return s;
    }

}
