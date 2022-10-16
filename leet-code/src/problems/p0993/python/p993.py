#[COMPLETED]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    # Returns parent of target and target depth, else None if not found
    def find_parent(self, trav: TreeNode, cur_depth: int, target: int) -> (TreeNode, int):
        if not trav:
            return None
        for child in [trav.left, trav.right]:
            if child:
                if child.val == target:
                    return trav, cur_depth + 1     # Return the parent and depth
                ret = self.find_parent(child, cur_depth + 1, target)
                if ret:     # If already found target, return result
                    return ret
        return None     # Target not found

    def isCousins(self, root: TreeNode, x: int, y: int) -> bool:
        if x == y:
            return False
        if not root:
            return False
        ret_x = self.find_parent(root, 0, x)
        if not ret_x:
            return False
        ret_y = self.find_parent(root, 0, y)
        if not ret_y:
            return False
        return ret_x[0] is not ret_y[0] and ret_x[1] == ret_y[1]

