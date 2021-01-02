#[COMPLETED]

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def __repr__(self):
        return str(self.val)


class Solution(object):
    """
    :type root: TreeNode
    :type target: TreeNode
    :type K: int
    :rtype: List[int]
    """
    def distanceK(self, root, target, K):
        path = self.find_path(root, target)
        #print(path)
        matches = []

        def trav(node, k):
            if node is None or k > K:
                return
            if k == K:
                matches.append(node.val)
                return
            if node.left is not None:
                trav(node.left, k+1)
            if node.right is not None:
                trav(node.right, k+1)

        for i, (node, direction) in enumerate(path):
            if i == K:
                matches.append(node.val)
            if direction != "l":
                trav(node.left, i+1)
            if direction != "r":
                trav(node.right, i+1)

        return matches


    def find_path(self, node, target):
        if node is target:
            return [(target, None)]

        res = None
        if node.left is not None:
            res = self.find_path(node.left, target)
            if res is not None:
                res.append((node, "l"))
        if res is None and node.right is not None:
            res = self.find_path(node.right, target)
            if res is not None:
                res.append((node, "r"))

        return res


root = TreeNode(3)
root.left = TreeNode(5)
root.left.left = TreeNode(6)
root.left.right = TreeNode(2)
root.left.right.left = TreeNode(7)
root.left.right.right = TreeNode(4)
root.right = TreeNode(1)
root.right.left = TreeNode(0)
root.right.right = TreeNode(8)

sol = Solution()
print(sol.distanceK(root, root.left.right.right, 2), "expected [7,5]")
print(sol.distanceK(root, root.left, 2), "expected [7,4,1]")


root = TreeNode(0)
root.left = TreeNode(1)
root.left.left = TreeNode(3)
root.left.right = TreeNode(2)
print(sol.distanceK(root, root.left.right, 1), "expected [1]")
