#[COMPLETED]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
    def __repr__(self):
        return str(self.val) \
               + " l=" + (str(self.left.val) if self.left is not None else " ") \
               + " r=" + (str(self.right.val) if self.right is not None else " ")

class Solution:
    def searchBST(self, root: TreeNode, val: int) -> TreeNode:
        while root is not None and root.val != val:
            if root.left is not None and root.right is not None:
                root = root.left if val < root.val else root.right
            else:
                root = root.left if root.left is not None else root.right
        return root

    # This is just general tree search, does not exploit binary search tree order
    # def searchBST(self, root: TreeNode, val: int) -> TreeNode:
    #     if root is None:
    #         return None
    #     if root.val == val:
    #         return root
    #     lft = self.searchBST(root.left, val)
    #     if lft is not None:
    #         return lft
    #     rht = self.searchBST(root.right, val)
    #     if rht is not None:
    #         return rht



def print_node(node):
    if node is None:
        print("None")
    else:
        print(node)
sol = Solution()
root = TreeNode(4)
root.left = TreeNode(2)
root.left.left = TreeNode(1)
root.left.right = TreeNode(3)
root.right = TreeNode(7)

print_node(sol.searchBST(root, 4))
print()
print_node(sol.searchBST(root, 2))
print()
print_node(sol.searchBST(root, 1))
print()
print_node(sol.searchBST(root, 3))
print()
print_node(sol.searchBST(root, 7))