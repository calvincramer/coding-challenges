package problem122;

public class Tree {
    
    public Node root;
    
    
    public Tree (Node root) {
        this.root = root;
    }
    
    public void printTree(Node startNode) {
        printTree(startNode, 0);
    }
    
    private void printTree(Node startNode, int level) {
        String s = "";
        for (int i = 1; i <= level; i++)
            s += "\t";
        
        System.out.println(s+startNode.toString());
        for (Node n : startNode.children)
            printTree(n, level+1);
    }
    
    public long countNodes(Node startNode) {
        long numNodes = 1;
        for (Node child : startNode.children)
            numNodes += countNodes(child);
        return numNodes;
    }
}
