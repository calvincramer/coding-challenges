package problem076;

import java.util.ArrayList;
import java.util.List;

public class Try2 {
    
    public static List[] nums;
    
    public static void main(String[] args) {
        nums = new List[101];
        nums[0] = null;

        for (int i = 2; i <= 100; i++) {
            nums[i] = genSums(i);
            System.out.println(i + ":\tways: " + (nums[i].size()-1));
            
            
            //System.out.println("");
        }
        for (int i = 0; i < nums[100].size(); i++) {
            printList((List)nums[100].get(i));
            System.out.println("");
        }
        System.out.println("Num sums 100: " + nums[100].size());
        
        
    }
    
    public static void printList(List arr) {
        System.out.print("[");
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i).toString());
            if (i < arr.size() - 1)
                System.out.print(" ");
        }
        System.out.print("]");
    }
    
    public static List copyList(List arr) {
        List newList = new ArrayList();
        for (Object obj : arr)
            newList.add(obj);
        return newList;
    }
    
    public static List<List<Integer>> genSums(int num) {
        if (num < 2)
            return null;
        
        List<List<Integer>> sums = new ArrayList<>();
        sums.add(new ArrayList());
        sums.get(0).add(num);
        sums.add(new ArrayList());
        sums.get(1).add(num-1);
        sums.get(1).add(1);
        
        int subtracting = 2;
        
        while (sums.get(sums.size()-1).get(0) > 1) {
            
            List<List<Integer>> toAdd = nums[subtracting];
            int startNum = num - subtracting;
            int startIndex = 0;
            
            for (int i = 0; i < toAdd.size(); i++) {
                boolean allLess = true;
                for (int j = 0; j < toAdd.get(i).size(); j++) {
                    if (toAdd.get(i).get(j) > startNum) {
                        allLess = false;
                        break;
                    }
                }
                if (allLess) {
                    startIndex = i;
                    break;
                }
            }
            
            for (int i = startIndex; i < toAdd.size(); i++) {
                List<Integer> temp = copyList(toAdd.get(i));
                temp.add(0, startNum);
                sums.add(copyList(temp));
            }
            
            subtracting++;
        }
        
        
        return sums;
    }
}
