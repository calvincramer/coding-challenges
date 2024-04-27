#[COMPLETED]

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

    def print_ln(self):
        print(self.val, end="")
        if self.next != None:
            print(" -> ", end="")
            self.next.print_ln()
        else:
            print()

class Solution(object):
    """
    :type head: ListNode
    :rtype: ListNode
    """
    def deleteDuplicates(self, head):
        trav = head
        while trav != None and trav.next != None:
            while trav.next != None and trav.val == trav.next.val:
                trav.next = trav.next.next
            trav = trav.next
        return head


sol = Solution()
h = ListNode(1)
h.next = ListNode(1)
h.next.next = ListNode(2)
h.next.next.next = ListNode(3)
h.next.next.next.next = ListNode(3)
h.print_ln()
h = sol.deleteDuplicates(h)
h.print_ln()

