#[COMPLETED]

class TreeNode:
	def __init__(self, val):
		self.val = val
		self.left = None
		self.right = None

	def printTree(self):
		result = []
		self.collectValues(self, result, 0)
		print(result)

	def collectValues(self, node, levels, curDepth):
		if node == None:
			return
		if len(levels) <= curDepth:
			levels.append([])
		levels[curDepth].append(node.val)
		self.collectValues(node.left , levels, curDepth+1)
		self.collectValues(node.right, levels, curDepth+1)


class Solution:
	def isSubtree(self, s, t):
		"""
        :type s: TreeNode
        :type t: TreeNode
        :rtype: bool
        """
		if s == None and t == None:
			return True
		if s == None:
			return False

		if self.treesEqual(s, t):
			return True
		else:
			return (self.isSubtree(s.left, t)) or (self.isSubtree(s.right, t))

	def treesEqual(self, t1, t2):
		if t1 == None and t2 == None:
			return True
		if (t1 == None and t2 != None) or (t1 != None and t2 == None):
			return False
		if t1.val != t2.val:
			return False
		return self.treesEqual(t1.left, t2.left) and self.treesEqual(t1.right, t2.right)


tree1 = TreeNode(1)
tree1.left = TreeNode(2)
tree1.left.left = TreeNode(4)

tree1.left.right = TreeNode(1)
tree1.left.right.left = TreeNode(2)
tree1.left.right.right = TreeNode(3)

tree1.right = TreeNode(3)
tree1.right.left = TreeNode(6)
tree1.right.right = TreeNode(7)

tree2 = TreeNode(1)
tree2.left = TreeNode(2)
tree2.right = TreeNode(3)

s = Solution()
print("is subtree?: " + str(s.isSubtree(tree1, tree2)))

#print("")
#print(s.treesEqual(tree1.left.right, tree2))



