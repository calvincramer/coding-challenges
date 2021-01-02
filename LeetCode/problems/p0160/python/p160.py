#[COMPLETED]

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    """
    :type head1, head1: ListNode
    :rtype: ListNode
    """
    def getIntersectionNode(self, headA, headB):
        trav_A = headA
        trav_B = headB
        len_A = 0
        len_B = 0
        while trav_A:
            len_A += 1
            trav_A = trav_A.next
        while trav_B:
            len_B += 1
            trav_B = trav_B.next
        trav_long = headA if len_A > len_B else headB
        trav_short = headB if trav_long is headA else headA
        for i in range(0, abs(len_A - len_B)):
            trav_long = trav_long.next
        while trav_long and trav_short:
            if trav_long is trav_short:
                return trav_long
            trav_long = trav_long.next
            trav_short = trav_short.next
        return None


sol = Solution()
a = ListNode(4)
a.next = ListNode(1)
a.next.next = ListNode(8)
a.next.next.next = ListNode(4)
a.next.next.next.next = ListNode(5)
b = ListNode(5)
b.next = ListNode(0)
b.next.next = ListNode(1)
b.next.next.next = a.next.next

inter = sol.getIntersectionNode(a, b)
inter = "None" if inter is None else str(inter.val)
print(inter, "expected 8")


