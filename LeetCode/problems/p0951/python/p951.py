#[COMPLETED]

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    """
    :type root1: TreeNode
    :type root2: TreeNode
    :rtype: bool
    """
    def flipEquiv(self, root1, root2):
        if root1 is None and root2 is None:
            return True
        elif root1 is None or root2 is None:
            return False
        elif root1.val != root2.val:
            return False

        cond1 = root1.left is None and root2.left is None
        cond2 = root1.left is not None and root2.left is not None and root1.left.val == root2.left.val
        if cond1 or cond2:
            return self.flipEquiv(root1.left, root2.left) and self.flipEquiv(root1.right, root2.right)
        else:
            return self.flipEquiv(root1.left, root2.right) and self.flipEquiv(root1.right, root2.left)

t1 = TreeNode(1)
t1.left = TreeNode(2)
t1.left.left = TreeNode(4)
t1.left.right = TreeNode(5)
t1.left.right.left = TreeNode(7)
t1.left.right.right = TreeNode(8)
t1.right = TreeNode(3)
t1.right.left = TreeNode(6)

t2 = TreeNode(1)
t2.left = TreeNode(3)
t2.left.right = TreeNode(6)
t2.right = TreeNode(2)
t2.right.left = TreeNode(4)
t2.right.right = TreeNode(5)
t2.right.right.left = TreeNode(8)
t2.right.right.right = TreeNode(7)

sol = Solution()
print(sol.flipEquiv(t1, t2))
