from typing import Optional, Tuple

import main
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
        return self.helper(node=root, _min=root.val, _max=root.val)

    def helper(self, node: TreeNode, _min: int, _max: int) -> int:
        if node is None:
            return _max - _min
        _max = max(_max, node.val)
        _min = min(_min, node.val)
        return max(self.helper(node.left, _min, _max), self.helper(node.right, _min, _max))


sol = Solution()
test = main.UTest()

t = utils.tree_string_to_tree('[8,3,10,1,6,null,14,null,null,4,7,13]')
test.test_eq(sol.maxAncestorDiff(t), 7)

t = utils.tree_string_to_tree('[1,null,2,null,0,3]')
test.test_eq(sol.maxAncestorDiff(t), 3)
