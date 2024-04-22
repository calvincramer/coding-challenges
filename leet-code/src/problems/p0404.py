#[COMPLETED]

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution(object):
    """
    :type root: TreeNode
    :rtype: int
    """
    def sumOfLeftLeaves(self, root):
        return self.trav(root, 0)

    def trav(self, root, sum):
        if root is None:
            return 0
        if root.left is not None and root.left.left is None and root.left.right is None:
            sum += root.left.val
        if root.left is not None:
            sum = self.trav(root.left, sum)
        if root.right is not None:
            sum = self.trav(root.right, sum)
        return sum
