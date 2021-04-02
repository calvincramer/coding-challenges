package problem122;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int num;
    public List<Node> children;
    public Node parent;
    
    public Node(int n, Node parent) {
        this.num = n;
        this.children = new ArrayList<>();
        if (parent != null) {
            parent.addChild(this);
            this.parent = parent;
        }
    }
    
    public void addChild(Node child) {
        this.children.add(child);
        child.parent = this;
    }
    
    public int getDepth() {
        int depth = 0;
        Node trav = this;
        while (trav.parent != null) {
            depth++;
            trav = trav.parent;
        }
        return depth;
    }
    
    @Override
    public String toString() {
        return ""+num;
    }
}
