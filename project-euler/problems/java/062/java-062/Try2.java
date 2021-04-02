package problem062;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;
import static problem062.Problem062.quickSort;

public class Try2 {
    
    
    public static void main(String[] args) {
        MF.startTimer();
        
        List<String> lowPerms = new ArrayList<>();
        ArrayList<ArrayList<Long>> cubes = new ArrayList<ArrayList<Long>>();
        int max = 0;
        
        for (long cbroot = 1; cbroot < Integer.MAX_VALUE; cbroot++) {
            long perm = cbroot * cbroot * cbroot;
            String lowPerm = lowestPermutation(perm);
            int index = lowPerms.indexOf(lowPerm);
            if (index == -1) {
                lowPerms.add(lowPerm);
                cubes.add(new ArrayList<>());
                cubes.get(cubes.size()-1).add(perm);
            }
            else {
                cubes.get(index).add(perm);
                
                if (cubes.get(index).size() > max) {
                    System.out.println(lowPerms.get(index) + "\t" + cubes.get(index).size());
                    for (Long n : cubes.get(index))
                        System.out.println(n + "\t" + (int)Math.cbrt(n) + "^3");
                    System.out.println();
                    max = cubes.get(index).size();
                }
            }
        }
        
        
    }
    
    public static class LongArr {
        public Long num;
        public List<Long> arr;
        
        public LongArr(Long num, List<Long> arr) {
            this.num = num;
            this.arr = arr;
        }
    }
    
    public static String lowestPermutation(long num) {
        char[] ch = (num+"").toCharArray();
        quickSort(ch, 0, ch.length-1);
        return new String(ch);
    }
}
/*answer:
012334556789	5
127035954683	5027^3
352045367981	7061^3
373559126408	7202^3
569310543872	8288^3
589323567104	8384^3
*/
