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
    :type sum: int
    :rtype: bool
    """
    def hasPathSum(self, root, sum):
        if root is None:
            return False
        if root.left is None and root.right is None and sum == root.val:
            return True
        return self.hasPathSum(root.left, sum - root.val) or self.hasPathSum(root.right, sum - root.val)


sol = Solution()
t = TreeNode(-2)
t.right = TreeNode(-3)
print(sol.hasPathSum(t, -2), "expected False")