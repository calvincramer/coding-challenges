#[IN PROGRESS]

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):
    def __init__(self):
        self.max_v = 0

    """
    :type root: TreeNode
    :rtype: int
    """
    def longestUnivaluePath(self, root):
        self.max_v = 0
        self.trav(root, 0)
        return self.max_v

    def trav(self, root, cur_v):
        if root is None:
            return
        if root.left is not None:
            if root.val == root.left.val:
                cur_v += 1
                self.max_v = max(cur_v, self.max_v)
                cur_v = self.trav(root.left, cur_v)
            else:
                self.trav(root.left, cur_v)

        if root.right is not None:
            if root.val == root.right.val:
                cur_v += 1
                self.max_v = max(cur_v, self.max_v)
                cur_v = self.trav(root.right, cur_v)
            else:
                self.trav(root.right, cur_v)

        return cur_v


sol = Solution()
t = TreeNode(5)
t.left = TreeNode(1)
t.left.left = TreeNode(4)
t.left.right = TreeNode(4)
t.right = TreeNode(5)
t.right.right = TreeNode(5)
print(sol.longestUnivaluePath(t), "expected 2")

t2 = TreeNode(1)
t2.left = TreeNode(4)
t2.left.left = TreeNode(4)
t2.left.right = TreeNode(4)
t2.right = TreeNode(5)
t2.right = TreeNode(5)
print(sol.longestUnivaluePath(t2), "expected 2")

t3 = TreeNode(4)
t3.left = TreeNode(-7)
t3.right = TreeNode(-3)
t3.right.left = TreeNode(-9)
t3.right.right = TreeNode(-3)
t3.right.left.left = TreeNode(9)
t3.right.left.right = TreeNode(-7)
t3.right.right.left = TreeNode(-4)
t3.right.left.left.left = TreeNode(6)
t3.right.left.right.left = TreeNode(-6)
t3.right.left.right.right = TreeNode(-6)
t3.right.left.left.left.left = TreeNode(0)
t3.right.left.left.left.right = TreeNode(6)
t3.right.left.right.left.left = TreeNode(5)
t3.right.left.right.right.left = TreeNode(9)
print(sol.longestUnivaluePath(t3), "expected 1")

"""
       4
 -7             -3
_  _      -9             -3 
        9      -7     -4    _
      6  _  -6   -6  _   _
     0 6   5 _  9 _
  _ -1 -4 _ 
"""
t4 = TreeNode(1)
t4.left = TreeNode(2)
t4.left.left = TreeNode(2)
t4.left.right = TreeNode(2)
t4.right = TreeNode(2)
t4.right.left = TreeNode(2)
t4.right.right = TreeNode(2)
t4.left.left.left = TreeNode(2)
print(sol.longestUnivaluePath(t4), "expected 3")



# Need to do a bottom up approach