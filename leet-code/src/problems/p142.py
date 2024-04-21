#[COMPLETED]

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    """
    :type head: ListNode
    :rtype: ListNode
    """
    def detectCycle(self, head):
        if head is None:
            return None
        trav = head
        # O(n) memory, O(n) time
        d = {}
        while trav is not None:
            if trav in d:
                return trav
            else:
                d[trav] = True
            trav = trav.next
        return None


sol = Solution()
h = ListNode(3)
h.next = ListNode(2)
h.next.next = ListNode(0)
h.next.next.next = ListNode(4)
h.next.next.next.next = h.next
print(sol.detectCycle(h).val, "expected 2")