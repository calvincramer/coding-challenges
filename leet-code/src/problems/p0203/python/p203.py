#[COMPLETED]

import sys

class ListNode:
	def __init__(self, x):
		self.val = x
		self.next = None


	def listNodePrint(self):
		sys.stdout.write(str(self.val))
		if self.next != None:
			sys.stdout.write(" -> ")
			self.next.listNodePrint()

	def printLN(self):
		self.listNodePrint()
		sys.stdout.write("\n")


    

class Solution:
    def removeElements(self, head, val):
        """
        :type head: ListNode
        :type val: int
        :rtype: ListNode
        """
        if head == None:
        	return None
        actualHead = head
        while head != None:
	        while head.next != None and head.next.val == val:
	        	head.next = head.next.next
	        head = head.next

        if actualHead.val == val:
        	return actualHead.next
        return actualHead



head = ListNode(1)
head.next = ListNode(2)
head.next.next = ListNode(3)
head.next.next.next = ListNode(4)
head.next.next.next.next = ListNode(5)
head.next.next.next.next.next = ListNode(4)
head.next.next.next.next.next.next = ListNode(3)
head.next.next.next.next.next.next.next = ListNode(2)
head.next.next.next.next.next.next.next.next = ListNode(1)
head.printLN()
sol = Solution()
head = sol.removeElements(head, 1)
head.printLN() 
        