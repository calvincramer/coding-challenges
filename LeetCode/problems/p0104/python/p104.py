#[COMPLETED]

class TreeNode:
	def __init__(self, x):
		self.val = x
		self.left = None
		self.right = None

	def printTrav(self):
		print(str(self.val) + " ")
		if self.left != None:
			self.left.printTrav()
		if self.right != None:
			self.right.printTrav()

	def printTree(self):
		self.printTrav()
		print("")

class Solution:
	def maxDepth(self, root):
		"""
		:type root: TreeNode
		:rtype: int
		"""
		if root == None:
			return 0
		return max(1 + self.maxDepth(root.left), 1 + self.maxDepth(root.right))
		


s = Solution()
root = TreeNode(4)
root.left = TreeNode(2)
root.left.left = TreeNode(1)
root.left.right = TreeNode(3)
root.right = TreeNode(7)
root.right.left = TreeNode(6)
root.right.right = TreeNode(9)

#root.printTree()

d = s.maxDepth(root)
print(d)
#root.printTree()
