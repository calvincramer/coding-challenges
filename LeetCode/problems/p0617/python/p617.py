#[COMPLETED]

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def print_tree(self):
        self.print_tree_trav(0)

    def print_tree_trav(self, level):
        print(" " * level, self.val, sep="", end="\n")
        if self.left is not None:
            self.left.print_tree_trav(level + 1)
        if self.right is not None:
            self.right.print_tree_trav(level + 1)


class Solution(object):
    """
    :type t1: TreeNode
    :type t2: TreeNode
    :rtype: TreeNode
    """
    def mergeTrees(self, t1, t2):
        if t1 is None and t2 is None:
            return None
        elif t1 is None:
            return t2
        elif t2 is None:
            return t1

        combined = TreeNode(t1.val + t2.val)
        combined.left = self.mergeTrees(t1.left, t2.left)
        combined.right = self.mergeTrees(t1.right, t2.right)
        return combined


t1 = TreeNode(1)
t1.left = TreeNode(3)
t1.left.left = TreeNode(5)
t1.right = TreeNode(2)

t2 = TreeNode(2)
t2.left = TreeNode(1)
t2.left.right = TreeNode(4)
t2.right = TreeNode(3)
t2.right.right= TreeNode(7)

print("T1=")
t1.print_tree()
print("\nT2=")
t2.print_tree()

sol = Solution()
ans = sol.mergeTrees(t1, t2)
print("\nMerged=")
ans.print_tree()

assert ans.val == 3
assert ans.left.val == 4
assert ans.left.left.val == 5
assert ans.left.right.val == 4
assert ans.right.val == 5
assert ans.right.right.val == 7
print("Passed all")
