#[COMPLETED]

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def preorderTraversal(self, root):
        if root == None:
            return []
        cur = root
        stack = [root]
        lst = []
        while len(stack) != 0:
            cur = stack.pop(0)
            lst.append(cur.val)
            if cur.right is not None:
                stack.insert(0, cur.right)
            if cur.left is not None:
                stack.insert(0, cur.left)
        return lst

    def preorderTraversalSimpleRecursion(self, root):
        if root == None:
            return []
        l = [root.val]
        l.extend(self.preorderTraversal(root.left))
        l.extend(self.preorderTraversal(root.right))
        return l

sol = Solution()
root = TreeNode(1)
root.right = TreeNode(2)
root.right.left = TreeNode(3)
print(sol.preorderTraversal(root))