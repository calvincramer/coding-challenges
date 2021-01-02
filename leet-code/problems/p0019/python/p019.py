#[COMPLETED]

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

    def printList(self):
        print(str(self.val), " ", end="")
        if self.next is not None:
            self.next.printList()
        else:
            print()

class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        def len_ll(head):
            if head is None:
                return 0
            return 1 + len_ll(head.next)
        length = len_ll(head)
        if n == length:
            return head.next
        trav = head
        for i in range(length - n - 1):
            trav = trav.next
        trav.next = None if trav.next is None else trav.next.next
        return head




head = ListNode(1)
# head.next = ListNode(2)
# head.next.next = ListNode(3)
# head.next.next.next = ListNode(4)
# head.next.next.next.next = ListNode(5)

sol = Solution()
head.printList()
result = sol.removeNthFromEnd(head, 1)
if result is not None:
    result.printList()
else:
    print("[]")
