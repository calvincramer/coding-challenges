#[COMPLETED]

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

    def print_ll(self):
        self.print_trav()
        print()

    def print_trav(self):
        print(str(self.val), end="")
        if self.next is not None:
            print(" -> ", end="")
            self.next.print_trav()

class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        # Get length of list
        l = 0
        trav = head
        while trav is not None:
            l += 1
            trav = trav.next

        # Get right half
        half = l // 2
        r_head_i = half + (1 if l % 2 == 1 else 0)
        trav = head
        i = 0
        while i < r_head_i:
            trav = trav.next
            i += 1
        r_head = trav

        # Get left half
        # left half is just first half nodes
        # Reverse right half
        prev = None
        next = None
        curr = r_head
        while curr is not None:
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        r_head = prev

        # Traverse both check for equality
        i = 0
        while i < half:
            if head.val != r_head.val:
                return False
            head = head.next
            r_head = r_head.next
            i += 1
        return True


sol = Solution()
h = ListNode(1)
h.next = ListNode(2)
print(sol.isPalindrome(h), "expected False\n")

h = ListNode(1)
h.next = ListNode(2)
h.next.next = ListNode(2)
h.next.next.next = ListNode(1)
print(sol.isPalindrome(h), "expected True\n")

h = ListNode(1)
h.next = ListNode(2)
h.next.next = ListNode(5)
h.next.next.next = ListNode(2)
h.next.next.next.next = ListNode(1)
print(sol.isPalindrome(h), "expected True\n")

h = ListNode(1)
h.next = ListNode(2)
h.next.next = ListNode(3)
h.next.next.next = ListNode(3)
h.next.next.next.next = ListNode(2)
h.next.next.next.next.next = ListNode(1)
print(sol.isPalindrome(h), "expected True\n")

h = ListNode(1)
h.next = ListNode(2)
h.next.next = ListNode(3)
h.next.next.next = ListNode(4)
h.next.next.next.next = ListNode(3)
h.next.next.next.next.next = ListNode(2)
h.next.next.next.next.next.next = ListNode(1)
print(sol.isPalindrome(h), "expected True\n")

h = ListNode(1)
h.next = ListNode(2)
h.next.next = ListNode(3)
h.next.next.next = ListNode(4)
h.next.next.next.next = ListNode(3)
h.next.next.next.next.next = ListNode(2)
h.next.next.next.next.next.next = ListNode(5)
print(sol.isPalindrome(h), "expected False\n")
