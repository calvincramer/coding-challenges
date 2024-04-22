#[COMPLETED]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

from typing import List

class Solution:
    def binaryTreePaths(self, root: TreeNode) -> List[str]:
        res = []
        self.trav(root, res, "", True)
        return res

    def trav(self, root, collect, path: str, is_root: bool):
        if not root:
            return
        path += str(root.val) if is_root else "->" + str(root.val)
        if not root.left and not root.right:
            collect.append(path)
            return
        if root.left:
            self.trav(root.left, collect, path, False)
        if root.right:
            self.trav(root.right, collect, path, False)

sol = Solution()
r = TreeNode(1)
r.left = TreeNode(2)
r.right = TreeNode(10)
r.left.left = TreeNode(3)
r.left.right = TreeNode(4)
r.right.left = TreeNode(9)
r.right.right = TreeNode(11)

print(sol.binaryTreePaths(r))
