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
    def isValidBST(self, root):
        # First try: just traverse whole tree and check if left < cur, right > cur
        # Fails because we need to keep track of the range
        return self.trav(root, -999999999999, 999999999999)

    """
    isValidBST trav, keeps track of valid interval that root should be in INCLUSIVE
    """
    def trav(self, root, low, high):
        if root is None:
            return True
        if root.left is not None:
            if root.left.val >= root.val or root.left.val < low or root.left.val > high:
                return False
        if root.right is not None:
            if root.right.val <= root.val or root.right.val < low or root.right.val > high:
                return False
        return self.trav(root.left, low, root.val - 1) and self.trav(root.right, root.val + 1, high)


sol = Solution()
t = TreeNode(10)
t.left = TreeNode(5)
t.right = TreeNode(15)
t.right.left = TreeNode(6)
t.right.right = TreeNode(20)
print(sol.isValidBST(t), "expected false")
