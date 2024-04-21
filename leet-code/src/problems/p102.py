#[COMPLETED]

from typing import List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        collect = []
        self.helper(root, 0, collect)
        return collect

    def helper(self, root, level, collect):
        if not root:
            return

        while len(collect) <= level:
            collect.append([])

        collect[level].append(root.val)
        self.helper(root.left, level + 1, collect)
        self.helper(root.right, level + 1, collect)


r = TreeNode(3)
r.left = TreeNode(9)
r.right = TreeNode(20)
r.right.left = TreeNode(15)
r.right.right = TreeNode(7)

sol = Solution()
print(sol.levelOrder(r))
