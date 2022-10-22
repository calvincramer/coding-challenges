from typing import Optional, Tuple
import utils

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def maxAncestorDiff(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        _min, _max = self.helper(root)
        return abs(_max - _min)

    def helper(self, node) -> Tuple[int, int]:
        if node is None:
            return 0, 0
        l_min, l_max = self.helper(node.left)
        r_min, r_max = self.helper(node.right)
        return min(l_min, r_min), max(l_max, r_max)


sol = Solution()
t1 = utils.tree_string_to_tree('[8,3,10,1,6,null,14,null,null,4,7,13]')
print(sol.maxAncestorDiff(t1))
