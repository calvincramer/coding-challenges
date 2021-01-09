//[COMPLETED]

package p021;

public class P021 {

    public static void main(String[] args) {
        new P021().solve();
    }
    
    public void solve() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        
        System.out.println(l1);
        System.out.println(l2);
        
        
        Solution s = new Solution();
        System.out.println(s.mergeTwoLists(l1, l2).toString());
        
        
    }
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        
        @Override public String toString() {
            if (this.next == null)
                return "" + val;
            return "" + val + " -> " + this.next.toString();
        }
    }   
 
 
private class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)  return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        ListNode result;
        
        if (l1.val < l2.val) {
            result = new ListNode(l1.val);
            l1 = l1.next;
        }
        else {
            result = new ListNode(l2.val);
            l2 = l2.next;
        }
        
        ListNode resultIter = result;
        
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                resultIter.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            else if (l2 == null || l1.val < l2.val) {
                resultIter.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            else {
                resultIter.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            resultIter = resultIter.next;  
        }
        
        return result;
    }
}

}
