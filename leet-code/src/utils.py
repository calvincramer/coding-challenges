from typing import List


class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __repr__(self):
        return 'TreeNode({})'.format(self.val)


def unsigned_bin_str_to_dec(s_bin: str) -> int:
    """
    Convert string of 0's and 1's to decimal. Treats binary string as unsigned, left most digit is most significant.

    For example: "10011 -> 16 + 2 + 1 = 19
    """
    if s_bin == '' or s_bin is None:
        return 0
    if set(s_bin).issubset({0, 1}):
        raise ValueError('input string contains something other than zeros and ones')
    s_bin = s_bin[::-1]     # Reverse string
    base = 1
    dec_num = 0
    for bit in s_bin:
        if bit == '1':
            dec_num += base
        base *= 2
    return dec_num


def bin_search(arr: List, search_for: object, left: int, right: int) -> int:
    """ Binary search"""
    if right < left:
        return -1
    mid = left + ((right - left) // 2)
    mid_n = arr[mid]
    if mid_n == search_for:
        return mid
    elif mid_n > search_for:
        return bin_search(search_for, left, mid - 1)
    return bin_search(search_for, mid + 1, right)


def tree_string_to_tree(string):
    """Leet code tree array string to actual tree """
    if string == '{}':
        return None
    nodes = [None if val == 'null' else TreeNode(int(val))
             for val in string.strip('[]{}').split(',')]
    kids = nodes[::-1]
    root = kids.pop()
    for node in nodes:
        if node:
            if kids:
                node.left = kids.pop()
            if kids:
                node.right = kids.pop()
    return root


def draw_tree(root):
    def height(_root):
        return 1 + max(height(_root.left), height(_root.right)) if _root else -1

    def jumpto(x, y):
        t.penup()
        t.goto(x, y)
        t.pendown()

    def draw(node, x, y, dx):
        if node:
            t.goto(x, y)
            jumpto(x, y - 20)
            t.write(node.val, align='center', font=('Arial', 12, 'normal'))
            draw(node.left, x - dx, y - 60, dx / 2)
            jumpto(x, y - 20)
            draw(node.right, x + dx, y - 60, dx / 2)

    import turtle
    t = turtle.Turtle()
    t.speed(0)
    turtle.delay(0)
    h = height(root)
    jumpto(0, 30 * h)
    draw(root, 0, 30 * h, 40 * h)
    t.hideturtle()
    turtle.mainloop()
