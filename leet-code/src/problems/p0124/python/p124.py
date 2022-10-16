# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        # Solve recursively
        # Two types of recursion:
        #   1. Path includes parent
        #   2. Path doesn't include parent - path only in child tree
        #
        # Path could stop anywhere,
        #   don't necessarily include parent,
        #   don't necessarily include both children
        pass
    