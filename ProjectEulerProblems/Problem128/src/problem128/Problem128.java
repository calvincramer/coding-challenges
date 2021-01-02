package problem128;

import java.util.ArrayList;
import java.util.List;

public class Problem128 {

    /*
    A hexagonal tile with number 1 is surrounded by a ring of six hexagonal tiles, starting at "12 o'clock" and numbering the tiles 2 to 7 in an anti-clockwise direction.

    New rings are added in the same fashion, with the next rings being numbered 8 to 19, 20 to 37, 38 to 61, and so on. The diagram below shows the first three rings.

    By finding the difference between tile n and each of its six neighbours we shall define PD(n) to be the number of those differences which are prime.

    For example, working clockwise around tile 8 the differences are 12, 29, 11, 6, 1, and 13. So PD(8) = 3.

    In the same way, the differences around tile 17 are 1, 17, 16, 1, 11, and 10, hence PD(17) = 2.

    It can be shown that the maximum value of PD(n) is 3.

    If all of the tiles for which PD(n) = 3 are listed in ascending order to form a sequence, the 10th tile would be 271.

    Find the 2000th tile in this sequence.
    */
    
    public static List<Entry[]> data;
    
    public static void main(String[] args) {
        data = new ArrayList<>();
        Entry[] levelZero = new Entry[1];
        levelZero[0] = new Entry(1,0);
        data.add(levelZero);
        
        int n = 2;
        for (int level = 1; level <= 6; level++) {
            int size = level * 6;
            Entry[] temp = new Entry[size];
            for (int i = 0; i < size; i++) {
                temp[i] = new Entry(n, i);
                n++;
            }
            data.add(temp);
        }
        
        for (Entry[] ring : data) {
            for (int i = 0; i < ring.length; i++) {
                System.out.print(ring[i].n + "  ");
            }
            System.out.println("");
        }
        
        System.out.println("");
        printList(getNeighbors(8));
    }
    
    
    public static List<Integer> getNeighbors(int num) {
        
        if (num < 1) {
            return new ArrayList<>();
        }
        
        List<Integer> neighbors = new ArrayList<>();
        
        //always 2 neighbors in same ring
        int ring = 1;
        while (data.get(ring)[0].n < num)
            ring++;
        ring--;
        
        int low = num-1;
        if (low < data.get(ring)[0].n)
            low = data.get(ring)[data.get(ring).length-1].n;
        int high = num+1;
        if (high > data.get(ring)[data.get(ring).length-1].n)
            high = data.get(ring)[0].n;
        
        neighbors.add(low);
        neighbors.add(high);
        
        
        //if in middle of ring segment:
            //always 2 neighbors in lower ring, 2 in upper ring
            
        //if in ring corner:
            //always 1 neighbor in lower ring
            //always 3 neighbors in upper ring
        
        
        return neighbors;
    }
    
    
    public static class Entry {
        public int n;
        public int nInRing;
        
        public Entry (int n, int nInRing) {
            this.n = n;
            this.nInRing = nInRing;
        }
    }
    
    
    public static <T> void printList(List<T> list) {
        System.out.print("[");
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i).toString() + ", ");
        }
        System.out.print(list.get(list.size()-1) + "]");
    }
}
