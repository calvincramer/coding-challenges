#[COMPLETED]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def __repr__(self):
        s = ""
        s += str(self.val) + " left: " + (str(self.left.val) if self.left is not None else "None") + \
              " right: " + (str(self.right.val) if self.right is not None else "None") + "\n"
        if self.left is not None:
            s += self.left.__repr__()
        if self.right is not None:
            s += self.right.__repr__()
        return s

class Solution:
    def addOneRow(self, root: TreeNode, v: int, d: int) -> TreeNode:
        if d < 1:
            return root
        elif d == 1:
            new_root = TreeNode(v)
            new_root.left = root
            return new_root
        elif root is None:
            return None
        self.trav(root, v, d)
        return root

    def trav(self, node, v, d):
        if node is None:
            return
        if d == 2:
            # Insert level here
            insert_left = TreeNode(v)
            insert_left.left = node.left
            insert_right = TreeNode(v)
            insert_right.right = node.right
            node.left = insert_left
            node.right = insert_right
        else:
            self.trav(node.left, v, d-1)
            self.trav(node.right, v, d-1)


sol = Solution()
r = TreeNode(4)
r.left = TreeNode(2)
r.left.left = TreeNode(3)
r.left.right = TreeNode(1)
r.right = TreeNode(6)
r.right.left = TreeNode(5)

print(r)

print()

b = sol.addOneRow(r, 1, 2)

print(b)