#[COMPLETED]

# Definition for a Node.
class Node(object):
    def __init__(self, val, children):
        self.val = val
        self.children = children


class Solution(object):
    """
    :type root: Node
    :rtype: List[int]
    """
    def preorder(self, root):
        lst = []
        self.collect(root, lst)
        return lst

    def collect(self, root, lst):
        if root is None:
            return
        lst.append(root.val)
        for n in root.children:
            self.collect(n, lst)
