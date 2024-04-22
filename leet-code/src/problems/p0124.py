from main import UTest


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


from typing import Tuple, Optional

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
        if root is None:
            return 0
        return max(*self.helper(node=root))

    def helper(self, node: TreeNode) -> Tuple[int, int]:
        """returns max path with parent, max path without parent"""
        if node.left is None and node.right is None:
            return (node.val, node.val)

        res_l_par = -999999999
        res_l_no_par = -999999999
        res_r_par = -999999999
        res_r_no_par = -999999999
        if node.left is not None:
            res_l_par, res_l_no_par = self.helper(node.left)
        if node.right is not None:
            res_r_par, res_r_no_par = self.helper(node.right)

        max_par = max(node.val + res_l_par, node.val + res_r_par, node.val)
        max_no_par = max(res_l_par, res_r_par, res_l_no_par, res_r_no_par, node.val + res_l_par + res_r_par, node.val)
        return (max_par, max_no_par)


sol = Solution()
test = UTest()

r1 = TreeNode(val=1)
r1.left = TreeNode(val=2)
r1.right = TreeNode(val=3)
test.test_eq(sol.maxPathSum(root=r1), 6)

r2 = TreeNode(val=-10)
r2.left = TreeNode(val=9)
r2.right = TreeNode(val=20)
r2.right.left = TreeNode(val=15)
r2.right.right = TreeNode(val=7)
test.test_eq(sol.maxPathSum(root=r2), 42)

r3 = TreeNode(val=-1)
r3.left = TreeNode(val=-2)
r3.left.left = TreeNode(val=-6)
r3.right = TreeNode(val=10)
r3.right.left = TreeNode(val=-3)
r3.right.right = TreeNode(val=-6)
test.test_eq(sol.maxPathSum(root=r3), 10)

r4 = TreeNode(val=5)
r4.left = TreeNode(val=4)
r4.left.left = TreeNode(val=11)
r4.left.left.left = TreeNode(val=7)
r4.left.left.right = TreeNode(val=2)
r4.right = TreeNode(val=8)
r4.right.left = TreeNode(val=13)
r4.right.right = TreeNode(val=4)
r4.right.right.right = TreeNode(val=1)
test.test_eq(sol.maxPathSum(root=r4), 48)

r5 = TreeNode(-6)
r5.right = TreeNode(3)
r5.right.left = TreeNode(2)
test.test_eq(sol.maxPathSum(root=r5), 5)


# import utils
# utils.draw_tree(utils.tree_string_to_tree('[-6,null,3,2]'))
