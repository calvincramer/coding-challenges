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
	def invertTree(self, root):
		"""
		:type root: TreeNode
		:rtype: TreeNode
		"""
		if root == None:
			return None
		temp = root.left
		root.left = root.right
		root.right = temp
		self.invertTree(root.left)
		self.invertTree(root.right)
		return root


s = Solution()
root = TreeNode(4)
root.left = TreeNode(2)
root.left.left = TreeNode(1)
root.left.right = TreeNode(3)
root.right = TreeNode(7)
root.right.left = TreeNode(6)
root.right.right = TreeNode(9)

root.printTree()

root = s.invertTree(root)
root.printTree()

