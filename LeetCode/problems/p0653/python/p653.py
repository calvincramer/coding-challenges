#[COMPLETED]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def __repr__(self):
        return "TN: " + str(self.val)

class Solution:
    def findTarget(self, root: TreeNode, k: int) -> bool:
        s = {}
        def trav(node):
            if node is None:
                return
            if node.val not in s:
                s[node.val] = 1
            else:
                s[node.val] += 1
            trav(node.left)
            trav(node.right)
        trav(root)
        # Check if k is even, s contains two of k // 2
        if k % 2 == 0 and k // 2 in s and s[k // 2] >= 2:
            return True
        for i, n in enumerate(s):
            if (k - n) in s and k - n != n: # k - n = n case already covered
                return True
        return False

root = TreeNode(1)
sol = Solution()
print(sol.findTarget(root, 2), "expected False")
