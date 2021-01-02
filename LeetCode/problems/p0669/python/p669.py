#[IN PROGRESS]

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def printTree(self):
        if self == None:
            return
        print(self.val)
        self.left.printTree()
        self.right.printTree()


class Solution:
    def trimBST(self, root, L, R):
        """
        :type root: TreeNode
        :type L: int
        :type R: int
        :rtype: TreeNode
        """

root = TreeNode(5)
root.printTree()