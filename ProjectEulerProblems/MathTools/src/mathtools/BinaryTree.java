package mathtools;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree <T extends Comparable> {
    Node<T> root;
    
    public BinaryTree() {
        this.root = null;
    }
    
    /**
     * Inserts a node into the tree
     * @param key the node to insert
     * @return whether or not the node was inserted. False means a duplicate was found (No duplicated allowed).
     */
    public boolean insert(Node<T> key) {
        if (this.contains(key))
            return false;
        
        if (this.root == null) {
            this.root = key;
            return true;
        }
        
        Node<T> currentNode = this.root;
        while (true) {
            if (key.data.compareTo(currentNode.data) < 0) {
                if (currentNode.left == null) {
                    currentNode.left = key;
                    return true;
                }
                else {
                    currentNode = currentNode.left;
                }
            }
            else {
                if (currentNode.right == null) {
                    currentNode.right = key;
                    return true;
                }
                else {
                    currentNode = currentNode.right;
                }
            }
        }
    }
    
    /**
     * Determines whether a specific value is within the tree
     * @param check the node to check for
     * @return whether the value is present
     */
    public boolean contains(Node<T> check) {
        if (this.root == null)
            return false;
        
        Node<T> currentNode = root;
        while (true) {
            if (currentNode.data.equals(check.data))
                return true;
            else if (check.data.compareTo(currentNode.data) > 0)
                currentNode = currentNode.right;
            else 
                currentNode = currentNode.left;
            if (currentNode == null)
                return false;
        }
    }
    
    public void printPreorder() {
        System.out.print("[");
        if (this.root != null)
            this.printPreorderTrav(this.root);
        System.out.println("]");
    }
    
    public void printPreorderTrav(Node<T> node) { 
        System.out.print(node.toString() + ", ");
        if (node.left != null)
            this.printPreorderTrav(node.left);
        if (node.right != null)
            this.printPreorderTrav(node.right);
    }
    
    public void printInorder() {
        System.out.print("[");
        if (this.root != null)
            this.printInorderTrav(this.root);
        System.out.println("]");
    }
    
    public void printInorderTrav(Node<T> node) { 
        if (node.left != null)
            this.printInorderTrav(node.left);
        System.out.print(node.toString() + ", ");
        if (node.right != null)
            this.printInorderTrav(node.right);
    }
    
    public void printPostorder() {
        System.out.print("[");
        if (this.root != null)
            this.printPostorderTrav(this.root);
        System.out.println("]");
    }
    
    public void printPostorderTrav(Node<T> node) { 
        if (node.left != null)
            this.printPostorderTrav(node.left);
        if (node.right != null)
            this.printPostorderTrav(node.right);
        System.out.print(node.toString() + ", ");
    }
    
    
    private List<Node<T>> stack = new ArrayList<>();
    private List<Boolean> lrstack = new ArrayList<>();  //false = left, true = right
    
    public void initPost() {
        this.stack = new ArrayList<>();
        this.lrstack = new ArrayList<>();
        this.descentLRrecurse(root, false);
    }
    
    public Node<T> nextPost() {
        
        //System.out.println(stack);
        
        if (this.root == null || this.stack.isEmpty())
            return null;
        
        if (stack.size() == 1) {
            Node<T> toReturn = stack.remove(0);
            lrstack.remove(0);
            return toReturn;
        }
        
        Node<T> toReturn = stack.remove(stack.size() - 1);
        boolean lr = lrstack.remove(lrstack.size() - 1);
        
        if (lr == false && stack.get(stack.size() - 1).right != null)
            this.descentLRrecurse(stack.get(stack.size() - 1).right, true);
        
        return toReturn;
    }
    
    public void descentLRrecurse(Node<T> node, boolean lastLR) {
        this.stack.add(node);
        this.lrstack.add(lastLR);
        
        if (node.left != null)
            descentLRrecurse(node.left, false);
        else if (node.right != null)
            descentLRrecurse(node.right, true);
        
        
    }
    
    
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        
        tree.insert(new Node<Integer>(10));
        tree.insert(new Node<Integer>(7));
        tree.insert(new Node<Integer>(8));
        tree.insert(new Node<Integer>(4));
        tree.insert(new Node<Integer>(6));
        tree.insert(new Node<Integer>(5));
        tree.insert(new Node<Integer>(15));
        tree.insert(new Node<Integer>(20));
        tree.insert(new Node<Integer>(13));
        
        /*
        tree.insert(new Node<Integer>(3));
        tree.insert(new Node<Integer>(2));
        tree.insert(new Node<Integer>(1));
        tree.insert(new Node<Integer>(5));
        tree.insert(new Node<Integer>(4));
        tree.insert(new Node<Integer>(6));
        tree.insert(new Node<Integer>(7));
        */
        
        tree.printPreorder();
        tree.printInorder();
        tree.printPostorder();
        
        tree.descentLRrecurse(tree.root, false);
        System.out.println(tree.stack);
        System.out.println(tree.lrstack);
        
        System.out.println("");
        tree.initPost();
        Node<Integer> print = tree.nextPost();
        do {
            System.out.println(print);
            print = tree.nextPost();
        } while (print != null);
    }
}
