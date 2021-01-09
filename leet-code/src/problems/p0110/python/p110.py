#[COMPLETED]

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    """
    :type root: TreeNode
    :rtype: bool
    """
    def isBalanced(self, root):
        if root is None:
            return True
        if abs(self.depth(root.left) - self.depth(root.right)) > 1:
            return False
        return self.isBalanced(root.left) and self.isBalanced(root.right)

    def depth(self, node):
        if node is None:
            return 0
        elif node.left is None and node.right is None:
            return 1
        return 1 + max(self.depth(node.left), self.depth(node.right))


sol = Solution()
t1 = TreeNode(3)
t1.left = TreeNode(9)
t1.right = TreeNode(20)
t1.right.left = TreeNode(15)
t1.right.right = TreeNode(7)
print(sol.isBalanced(t1), "expected true")

t2 = TreeNode(1)
t2.right = TreeNode(2)
t2.left = TreeNode(2)
t2.left.left = TreeNode(3)
t2.left.right = TreeNode(3)
t2.left.left.left = TreeNode(4)
t2.left.left.right = TreeNode(4)
print(sol.isBalanced(t2), "expected false")
