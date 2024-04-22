#[IN PROGRESS]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def print_tree(self):
        print(str(self.val), "\t: ", end = "")
        if self.left is not None:
            print("l=", str(self.left.val), end="")
        else:
            print("l= None ", end="")
        if self.right is not None:
            print(" r=", str(self.right.val), end="")
        else:
            print(" r= None ", end="")
        print()

        if self.left is not None:
            self.left.print_tree()
        if self.right is not None:
            self.right.print_tree()

    def __repr__(self):
        s = str(self.val)
        if self.left is not None:
            s = s + " l= " + str(self.left.val) + " "
        else:
            s = s + " l=N "
        if self.right is not None:
            s = s + " r=" + str(self.right.val)
        else:
            s = s + " r=N"
        return s

class Solution:
    def buildTree(self, preorder, inorder) -> TreeNode:
        if len(preorder) != len(inorder) or len(preorder) < 1:
            return None
        pre_i = 0
        ino_i = 0
        root = TreeNode(preorder[pre_i])
        path = [root]               # Stack of TreeNodes
        path_set = set([root.val])  # Set of values
        #path_set = set([])  # Set of values
        pre_i += 1

        while pre_i < len(preorder) and ino_i < len(inorder):
            temp = TreeNode(preorder[pre_i])
            if path[-1].left is None:
                path[-1].left = temp
            else:
                path[-1].right = temp

            path.append(temp)
            path_set.add(temp.val)
            pre_i += 1

            # Problem here
            while inorder[ino_i] in path_set:
                ino_i += 1
                if ino_i >= len(inorder) or inorder[ino_i] not in path_set:
                    break
                else:
                    path_set.remove(path.pop().val)

        return root


sol = Solution()

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
t = sol.buildTree(preorder, inorder)
t.print_tree()
print()

# Wrong answer for thiscdcd
preorder = [0,1,2,3,4,5,6]
inorder = [4,3,2,1,5,0,6]
t = sol.buildTree(preorder, inorder)
t.print_tree()
print()
