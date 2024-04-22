#[IN PROGRESS]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
        node, l, r = self.trav(root, p, q)
        return node if l and r else None

    def trav(self, root, p, q):
        pass    # TODO
