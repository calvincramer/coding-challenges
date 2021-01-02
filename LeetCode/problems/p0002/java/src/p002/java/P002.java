//[COMPLETED]

package p002;

import java.math.BigInteger;

public class P002 {

    public static void main(String[] args) {
        ListNode i1 = Solution.numToListNode(new BigInteger("9"));
        ListNode i2 = Solution.numToListNode(new BigInteger("9999999991"));
        
        Solution s = new Solution();
        ListNode ans = s.addTwoNumbers(i1, i2);
        
        i1.print();
        System.out.println();
        i2.print();
        System.out.println();
        ans.print();
        
    }

}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger n1 = getNum(l1);
        BigInteger n2 = getNum(l2);
        
        return numToListNode(n1.add(n2));
    }
    
    public static BigInteger getNum(ListNode l) {
        BigInteger base = BigInteger.ONE;
        BigInteger num = BigInteger.ZERO;
        while (l != null) {
            
            num = num.add(base.multiply(new BigInteger(""+l.val)));
            base = base.multiply(BigInteger.TEN);
            
            l = l.next;
        }
        return num;
    }
    
    public static ListNode numToListNode(BigInteger num) {
        ListNode head = new ListNode(num.mod(BigInteger.TEN).intValue());
        num = num.divide(BigInteger.TEN);
        ListNode iter = head;
        while (num.compareTo(BigInteger.ZERO) > 0) {
            iter.next = new ListNode(num.mod(BigInteger.TEN).intValue());
            iter = iter.next;
            num = num.divide(BigInteger.TEN);
        }
        return head;
    }
    
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    
    public void print() {
        System.out.print(val);
        if (this.next != null) {
            System.out.print(" -> ");
            this.next.print();
        }
    }
 }