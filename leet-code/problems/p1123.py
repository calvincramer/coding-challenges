# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


from typing import Tuple, Optional


class Solution:
    def lcaDeepestLeaves(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        # Find the deepest node/s
        # If multiple nodes, find the common ancestor of all the nodes
        root, deepestLevel = self.helper(root=root, curDepth=0)
        return root

    def helper(self, root: TreeNode, curDepth: int) -> Tuple[TreeNode, int]:
        if root is None:
            return None
        if root.left is None and root.right is None:
            return root, curDepth
        elif root.left is not None and root.right is not None:
            l_root, l_depth = self.helper(root.left, curDepth + 1)
            r_root, r_depth = self.helper(root.right, curDepth + 1)
            if l_depth > r_depth:
                return l_root, l_depth
            elif r_depth > l_depth:
                return r_root, r_depth
            else:
                return root, l_depth  # Equal, we are common ancestor
        elif root.left is not None:
            return self.helper(root.left, curDepth + 1)
        elif root.right is not None:
            return self.helper(root.right, curDepth + 1)
