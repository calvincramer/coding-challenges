#[COMPLETED]

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

    def printL(self):
        trav = self
        while trav != None:
            print(str(trav.val) + ", ", end="")
            trav = trav.next
        print("")

class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if length(l1) < length(l2):
            temp = l1
            l1 = l2
            l2 = temp
        trav1 = l1
        trav2 = l2
        while trav2 != None:
            trav1.val = trav1.val + trav2.val
            if trav1.val > 9:
                carry = trav1.val // 10
                trav1.val = trav1.val % 10
                if trav1.next != None:
                    trav1.next.val = trav1.next.val + carry
                else:
                    trav1.next = ListNode(carry)
            trav1 = trav1.next
            trav2 = trav2.next

        while trav1 != None:
            if trav1.val > 9:
                carry = trav1.val // 10
                trav1.val = trav1.val % 10
                if trav1.next != None:
                    trav1.next.val = trav1.next.val + carry
                else:
                    trav1.next = ListNode(carry)
            trav1 = trav1.next

        return l1



def length(ln):
    trav = ln
    count = 0
    while trav != None:
        count = count+1
        trav = trav.next
    return count

#dont need
def reverse(ln):
    stack = []
    trav = ln
    while trav != None:
        stack.append(trav)
        trav = trav.next

    for i in range(0, len(stack)):
        stack[i].next = None

    newHead = stack[-1]
    trav = newHead
    for i in range(2, len(stack) + 1):
        trav.next = stack[-i]
        trav = trav.next

    return newHead
        

head = ListNode(2)
head.next = ListNode(4)
head.next.next = ListNode(3)

head2 = ListNode(5)
head2.next = ListNode(6)
head2.next.next = ListNode(4)

s = Solution()
sol = s.addTwoNumbers(head, head2)
sol.printL()