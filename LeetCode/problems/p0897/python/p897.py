#[COMPLETED]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def increasingBST(self, root: TreeNode) -> TreeNode:
        nodes = []
        self.trav(root, nodes)
        if len(nodes) == 0:
            return None
        r = TreeNode(nodes[0])
        trav = r
        for n in nodes[1:]:
            trav.right = TreeNode(n)
            trav = trav.right
        return r

    def trav(self, cur, nodes):
        if cur.left is not None:
            self.trav(cur.left, nodes)
        nodes.append(cur.val)
        if cur.right is not None:
            self.trav(cur.right, nodes)


r = TreeNode(5)
r.left = TreeNode(3)
r.left.left = TreeNode(2)
r.left.left.left = TreeNode(1)
r.left.right = TreeNode(4)
r.right = TreeNode(6)
r.right.right = TreeNode(8)
r.right.right.left = TreeNode(7)
r.right.right.right = TreeNode(9)


sol = Solution()
res = sol.increasingBST(r)
def p(tn):
    if tn is None:
        return
    print(str(tn.val), " ", end="")
    if tn.left is not None:
        print("BAD")
    p(tn.right)
print("Res:")
p(res)

# Can make faster by not storing all of the results in a list but building the tree as we go
# Also, instead of doing recursive try to do iterative (with stack?)
