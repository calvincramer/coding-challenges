#[COMPLETED]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def lowestCommonAncestor(self, root, p, q):
        if root is None:
            return None
        # Find both nodes first
        p_path = []
        self.find_path(root, p, p_path)
        q_path = []
        self.find_path(root, q, q_path)

        # Reduce longest path to shorter path length
        while len(p_path) > len(q_path):
            p_path.pop()
        while len(q_path) > len(p_path):
            q_path.pop()

        # Walk back both paths one level at a time until they meet (at root at the end, or before)
        while p_path[-1].val != q_path[-1].val:
            p_path.pop()
            q_path.pop()

        return p_path[-1]

    def find_path(self, current, target, cur_path):
        cur_path.append(current)
        if current.val == target.val:
            return True
        if current.left is not None:
            if self.find_path(current.left, target, cur_path):
                return True
        if current.right is not None:
            if self.find_path(current.right, target, cur_path):
                return True
        cur_path.pop()
        return False


sol = Solution()
root = TreeNode(3)
p = TreeNode(5)
q = TreeNode(1)

root.left = p
root.left.left = TreeNode(6)
root.left.right = TreeNode(2)
root.left.right.left = TreeNode(7)
root.left.right.right = TreeNode(4)
root.right = q
root.right.left = TreeNode(0)
root.right.right = TreeNode(8)

print(sol.lowestCommonAncestor(root, p, q).val)
