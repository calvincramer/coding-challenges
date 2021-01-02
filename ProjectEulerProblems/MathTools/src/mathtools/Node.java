package mathtools;

public class Node <T extends Comparable> {
    T data;
    Node<T> left;
    Node<T> right;
    
    public Node (T data) {
        this.data = data;
    }
    
    
    @Override 
    public String toString() {
        return data.toString();
    }
}
