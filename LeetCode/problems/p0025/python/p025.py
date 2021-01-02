#[COMPLETED]

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

    def print_ll(self):
        print(str(self.val), " ", end="")
        if self.next:
            self.next.print_ll()
        else:
            print()

    def __repr__(self):
        return str(self.val) + " -> " + (self.next.__repr__() if self.next else "END")

class Solution:
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        def reverse_ll(h, depth, tail, preceding) -> ListNode:
            prev, cur, n, cur_depth = None, h, None, 0
            while cur and cur_depth < depth:
                n = cur.next
                cur.next = prev if cur_depth != 0 else tail
                prev = cur
                cur = n
                cur_depth += 1
            if preceding:
                preceding.next = prev
            return prev
        # Iterate to find head of next group
        head_save = head
        next_head = head
        first_run = True
        trav = head
        tail = head
        prev_tail = None
        while True:
            cur_trav_save = trav
            count = k
            while count > 0 and next_head:
                next_head = next_head.next
                count -= 1
            continue_with_reverse = True if count == 0 else False
            if not continue_with_reverse:
                return head_save
            trav = reverse_ll(trav, k, next_head, prev_tail)
            if first_run:
                head_save = trav
                first_run = False
            trav = next_head
            prev_tail = cur_trav_save
        return None

head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)
head.next.next.next.next = ListNode(5)
head.next.next.next.next.next = ListNode(6)
head.next.next.next.next.next.next = ListNode(7)

head.print_ll()
sol = Solution()
new_head = sol.reverseKGroup(head, 3)
new_head.print_ll()
