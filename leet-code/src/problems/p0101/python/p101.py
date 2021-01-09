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
    :rtype: bool
    """
    def isSymmetric(self, root):
        if root is None:
            return True
        return self.isSymmetricTrav(root.left, root.right)

    def isSymmetricTrav(self, left, right):
        if left is None and right is None:
            return True
        if left is None or right is None:
            return False
        if left.val != right.val:
            return False
        if left.left is not None:
            if right.right is not None:
                if left.left.val != right.right.val:
                    return False
            else:
                return False
        else:
            if right.right is not None:
                return False
        if left.right is not None:
            if right.left is not None:
                if left.right.val != right.left.val:
                    return False
            else:
                return False
        else:
            if right.left is not None:
                return False
        # Symmetric on this level
        return self.isSymmetricTrav(left.left, right.right) and self.isSymmetricTrav(left.right, right.left)


sol = Solution()
t = TreeNode(1)
t.left = TreeNode(2)
t.left.left = TreeNode(3)
t.left.right = TreeNode(4)
t.left.left.left = TreeNode(6)
t.left.left.right = TreeNode(7)
t.left.right.left = TreeNode(0)
t.left.right.right = TreeNode(2)

t.right = TreeNode(2)
t.right.left = TreeNode(4)
t.right.right = TreeNode(3)
t.right.left.left = TreeNode(2)
t.right.left.right = TreeNode(0)
t.right.right.left = TreeNode(7)
t.right.right.right = TreeNode(6)

print(sol.isSymmetric(t), "expected True")
