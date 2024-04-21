#[COMPLETED]

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
	def isSameTree(self, t1, t2):
		"""
		:type p: TreeNode
		:type q: TreeNode
		:rtype: bool
		"""
		if t1 == None and t2 == None:
			return True
		if (t1 == None and t2 != None) or (t1 != None and t2 == None):
			return False
		if t1.val != t2.val:
			return False
		return self.isSameTree(t1.left, t2.left) and self.isSameTree(t1.right, t2.right)