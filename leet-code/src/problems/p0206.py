#[COMPLETED]

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

    def print_ll(self):
        print(self.val, sep="", end="")
        if self.next:
            print(" -> ", sep="", end="")
            self.next.print_ll()
        else:
            print()

class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        return self.reverseListIterative(head)

    def reverseListIterative(self, head: ListNode) -> ListNode:
        prev = None
        cur = head
        while cur:
            nextTemp = cur.next
            cur.next = prev
            prev = cur
            cur = nextTemp
        return prev

    def reverseListRecursive(self, head: ListNode) -> ListNode:
        if not head or not head.next:
            return head
        reversed = self.reverseListRecursive(head.next)
        head.next.next = head
        head.next = None
        return reversed


head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)
head.next.next.next.next = ListNode(5)

head.print_ll()

sol = Solution()

rev = sol.reverseList(head)

if rev:
    rev.print_ll()
else:
    print(None)