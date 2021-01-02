#[COMPLETED]

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

    def __str__(self):
        return str(self.val)

# Solution is O(1) memory, O(n) time
# Doesn't change the actual value of the nodes
class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        if head is None:
            return None
        save_head = head if head.next is None else head.next
        cur = head
        prev = None
        while cur is not None and cur.next is not None:
            tmp = cur.next
            cur.next = cur.next.next
            tmp.next = cur
            if prev is not None:
                prev.next = tmp
            prev = cur
            cur = cur.next

        return save_head



def print_LL(head):
    if head is None:
        return
    print(head, end="")
    if head.next is not None:
        print(" -> ", end="")
        print_LL(head.next)

sol = Solution()
head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)

new_head  = sol.swapPairs(head)
print_LL(new_head)
print()

head2 = ListNode(1)
head2.next = ListNode(2)
head2.next.next = ListNode(3)
head2.next.next.next = ListNode(4)
head2.next.next.next.next = ListNode(5)
head2.next.next.next.next.next = ListNode(6)
head2.next.next.next.next.next.next = ListNode(7)

new_head2  = sol.swapPairs(head2)
print_LL(new_head2)
print()