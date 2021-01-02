package problem122;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Problem122 {

    /*
    The most naive way of computing n15 requires fourteen multiplications:

    n × n × ... × n = n^15

    But using a "binary" method you can compute it in six multiplications:

    n × n = n^2
    n^2 × n^2 = n^4
    n^4 × n^4 = n^8
    n^8 × n^4 = n^12
    n^12 × n^2 = n^14
    n^14 × n = n^15

    However it is yet possible to compute it in only five multiplications:

    n × n = n^2
    n^2 × n = n^3
    n^3 × n^3 = n^6
    n^6 × n^6 = n^12
    n^12 × n^3 = n^15

    We shall define m(k) to be the minimum number of multiplications to compute nk; for example m(15) = 5.

    For 1 ≤ k ≤ 200, find ∑ m(k).
    */
    public static void main(String[] args) {
        /*
        Node root = new Node(1, null);
            Node n2 = new Node(2, root);
                Node n3 = new Node(3, n2);
                    Node n44 = new Node(4, n3);
                    Node n5 = new Node(5, n3);
                    Node n6 = new Node(6, n3);
                Node n4 = new Node(4, n2);
                    Node n55 = new Node(5, n4);
                    Node n66 = new Node(6, n4);
                    Node n8 = new Node(8, n4);
                
        
        Tree tree = new Tree(root);
        tree.printTree(root);
        System.out.println("");
        System.out.println("Num nodes: " + tree.countNodes(tree.root));
        System.out.println("depth of 8: " + n8.getDepth());
        
        System.out.println("\n\n");
        //test();
        System.out.println("m(15) = " + m(15));
        
        System.out.println("\n\n");
        */

        long sum = 0;
        for (int k = 2; k <= 200; k++) {
            MF.startTimer();
            long temp = m(k);
            System.out.println("m("+k+") = " + temp + "\tt="+MF.getElapsedSeconds());
            sum += m(k);
        }
        
        System.out.println("sum: " + sum);
    }
    
    public static void test() {
        for (int stop = 2; stop < 50; stop++) {
            Tree tree = new Tree(new Node(1, null));

            List<Node> leaves = new ArrayList<>();
            leaves.add(tree.root);

            while(leaves.size() != 0) {

                List<Node> newLeaves = new ArrayList<>();

                for (Node n : leaves) { //grow each leaf
                    //tree.printTree(tree.root);
                    //System.out.println("-------------------------------------------------------");
                    Node trav = n;
                    do {
                        if (trav.num + n.num <= stop) {
                            Node temp = new Node(trav.num + n.num, null);
                            n.addChild(temp);
                            newLeaves.add(temp);
                        } else {
                            break;
                        }
                        trav = trav.parent;
                    } while (trav != null);
                }
                leaves = newLeaves;
            }

            //tree.printTree(tree.root);
            System.out.println("max=" + stop + "\tnum nodes: " + tree.countNodes(tree.root));
        }
        
    }
    
    
    public static int m(int k) {
        Tree tree = new Tree(new Node(1, null));

        List<Node> leaves = new ArrayList<>();
        leaves.add(tree.root);

        while(leaves.size() != 0) {

            List<Node> newLeaves = new ArrayList<>();

            for (Node n : leaves) { //grow each leaf
                //tree.printTree(tree.root);
                //System.out.println("-------------------------------------------------------");
                Node trav = n;
                do {
                    Node temp = new Node(trav.num + n.num, null);
                    n.addChild(temp);
                    newLeaves.add(temp);
                    trav = trav.parent;

                    if (temp.num == k) {
                        return temp.getDepth();
                    }

                } while (trav != null);
            }
            leaves = newLeaves;
        }
        System.out.println("Something went wrong!");
        return -1;
            
    }
}
//answer
//sum: 1582

//another way could be to build the tree until every number in [1,200] occurs at least once
//then bfs for each number
