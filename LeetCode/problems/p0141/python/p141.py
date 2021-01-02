#[COMPLETED]

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    """
    :type head: ListNode
    :rtype: bool
    """
    def hasCycle(self, head):
        if head is None or head.next is None:
            return False
        trav1 = head
        trav2 = head.next
        trav1_skip = True   # Only step trav1 every other iteration
        while trav1 is not None and trav2 is not None:
            if trav1 is trav2:
                return True
            if trav1_skip:
                trav1 = trav1.next
            trav1_skip = not trav1_skip
            trav2 = trav2.next
        return False


sol = Solution()
h = ListNode(3)
h.next = ListNode(2)
h.next.next = ListNode(0)
h.next.next.next = ListNode(4)
h.next.next.next.next = h.next
print(sol.hasCycle(h), "expected true")