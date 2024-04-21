#[COMPLETED]

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

def print_l(node):
    if node is None:
        return
    print(node.val, end="")
    if node.next is not None:
        print(" -> ", end="")
    else:
        print()
    print_l(node.next)

class Solution:
    def oddEvenList(self, head):
        if head is None:
            return None
        #head1_save = head
        head2_save = head.next
        trav1 = head
        trav2 = head.next
        prev1 = trav1
        while trav1 is not None and trav2 is not None:
            next1 = trav1.next.next if trav1.next.next is not None else None
            next2 = trav2.next.next if trav2.next is not None else None
            trav1.next = next1
            prev1 = trav1
            trav1 = next1
            trav2.next = next2
            trav2 = next2

        if trav1 is not None:
            trav1.next = head2_save
        else:
            prev1.next = head2_save
        return head

sol = Solution()
head = ListNode(0)
head.next = ListNode(1)
head.next.next = ListNode(2)
head.next.next.next = ListNode(3)
head.next.next.next.next = ListNode(4)
head.next.next.next.next.next = ListNode(5)
print("Orig ln: ", end="")
print_l(head)
head = sol.oddEvenList(head)
print("Odd even: ", end="")
print_l(head)

