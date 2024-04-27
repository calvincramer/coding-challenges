#[COMPLETED]

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    """
    :type root: TreeNode
    :rtype: List[List[int]]
    """
    def levelOrderBottom(self, root):
        if root is None:
            return []
        collect = []
        self.trav(root, collect, 0)
        return collect[::-1]

    # Node is assumed to be non None
    def trav(self, node, coll, level):
        if len(coll) <= level:
            coll.append([])
        if node.left is not None:
            self.trav(node.left, coll, level + 1)
        if node.right is not None:
            self.trav(node.right, coll, level + 1)

        coll[level].append(node.val)


sol = Solution()
t = TreeNode(3)
t.left = TreeNode(9)
t.right = TreeNode(20)
t.right.left = TreeNode(15)
t.right.right = TreeNode(7)
print(sol.levelOrderBottom(t))

