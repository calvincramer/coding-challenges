import main
from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __repr__(self) -> str:
        s = str(self.val)
        if self.next is not None:
            s = f"{s} -> {repr(self.next)}"
        return s


class Solution:
    def sortLinkedList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        trav = head
        newHead = head
        while trav is not None:
            if trav.next is not None and trav.val > trav.next.val:
                # Move trav.next to proper place on very left
                tail = trav.next.next  # save so don't lose it
                oldHead = newHead  # save to access later
                newHead = trav.next  # new head
                newHead.next = oldHead  # new head points to previous head
                trav.next = tail  # rest of list
                # Don't advance to next since trav sees a new next from the above
            else:
                trav = trav.next
        return newHead


sol = Solution()
print(sol.sortLinkedList(head=ListNode(0, ListNode(2, ListNode(-5, ListNode(5, ListNode(10, ListNode(-10, None))))))))
