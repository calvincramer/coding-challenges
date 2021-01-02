#[COMPLETED]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def largestValues(self, root: TreeNode):
        maxl = []
        self.trav(root, 0, maxl)
        return maxl

    def trav(self, node, level, maxl):
        if node is None:
            return
        while len(maxl) <= level:
            maxl.append(float("-inf"))
        maxl[level] = max(maxl[level], node.val)
        self.trav(node.left, level + 1, maxl)
        self.trav(node.right, level + 1, maxl)


r = TreeNode(1)
r.left = TreeNode(3)
r.left.left = TreeNode(5)
r.left.right = TreeNode(3)
r.right = TreeNode(2)
r.right.right = TreeNode(9)

sol = Solution()
print(sol.largestValues(r))

