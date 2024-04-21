#[COMPLETED]

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

    def print_ln(self):
        print(self.val, end="")
        if self.next is not None:
            print(" -> ", end="")
            self.next.print_ln()
        else:
            print()

class Solution(object):
    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if l1 is None:
            return l2
        elif l2 is None:
            return l1

        head = None
        if l1.val < l2.val:
            head = l1
            l1 = l1.next
        else:
            head = l2
            l2 = l2.next

        trav = head

        while l1 is not None and l2 is not None:
            if l1.val < l2.val:
                trav.next = l1
                l1 = l1.next
            else:
                trav.next = l2
                l2 = l2.next
            trav = trav.next

        if l1 is not None:
            trav.next = l1
        elif l2 is not None:
            trav.next = l2

        return head


sol = Solution()
l1 = ListNode(1)
l1.next = ListNode(2)
l1.next.next = ListNode(4)
l1.next.next.next = ListNode(10)

l2 = ListNode(1)
l2.next = ListNode(3)
l2.next.next = ListNode(4)

ret = sol.mergeTwoLists(l1, l2)
if ret is not None:
    ret.print_ln()
else:
    print("None")
print("Expected 1->1->2->3->4->4")
