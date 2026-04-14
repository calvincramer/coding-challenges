#!/usr/bin/env python3

import time
import datetime
import importlib
from argparse import ArgumentParser
import sys

from termcolor import colored


def _try_run_module(moduleName: str) -> bool:
    try:
        start = time.time()
        importlib.import_module(moduleName)
        elapsed = datetime.timedelta(seconds=time.time() - start)
        print("Elapsed: {}".format(elapsed))
    except ModuleNotFoundError:
        return False
    return True


def main():
    parser = ArgumentParser()
    parser.add_argument("mod", nargs="?", help="Python module in 'problems' to run")
    parser.add_argument(
        "-m", required=False, dest="mod", type=str, help="Python module in 'problems' to run", metavar="MODULE"
    )
    args = parser.parse_args()
    mod = str(args.mod)

    # Be relaxed about picking module
    modules_to_try = [mod]
    # No 'p' before module number
    if len(mod) > 0 and mod[0].isdigit():
        modules_to_try.append(f"p{mod}")
    # Left-pad zeros
    if len(mod) < 4 and all(c.isdigit() for c in mod):
        modules_to_try.append(f"p{mod.zfill(4)}")

    modules_to_try = [f"problems.{m}" for m in modules_to_try]

    print(
        colored(
            f"{'#' * 20} PROBLEM {__modPathToProblemNum(modules_to_try[0])} {'#' * 20}", "yellow", attrs=["reverse"]
        )
    )

    # Run problem by importing it
    for m in modules_to_try:
        if _try_run_module(m) == True:
            return

    print(colored(f"No module with names {', '.join(modules_to_try)}", "red"))
    sys.exit(1)


def __modPathToProblemNum(s: str) -> str:
    parts = s.split(".")
    num = "".join(c for c in parts[-1] if c in "0123456789")
    num = num.lstrip("0")
    return num


class UTest:
    """
    Small unit test class for each problem to run a few test cases. Not a replacement for unittest package.
    """

    TEST_NUM = 0

    @classmethod
    def _common_message(cls, message) -> str:
        if message is not None:
            return f"test{cls.TEST_NUM} : {message} :"
        else:
            return f"test{cls.TEST_NUM} :"

    @classmethod
    def _ran_a_test_common(cls):
        UTest.TEST_NUM += 1

    @classmethod
    def exp_true(cls, inp, message=None):
        """Assert input is True"""
        if inp is not True:
            print(colored(f"{cls._common_message(message)} FAILED (should have been true)", "red"))
        else:
            print(colored(f"{cls._common_message(message)} passed", "green"))
        cls._ran_a_test_common()

    @classmethod
    def exp_false(cls, inp, message=None):
        """Assert input is False"""
        if inp is not False:
            print(colored(f"{cls._common_message(message)} FAILED (should have been false)", "red"))
        else:
            print(colored(f"{cls._common_message(message)} passed", "green"))
        cls._ran_a_test_common()

    @classmethod
    def exp_eq(cls, input1, input2, message=None):
        """Assert inputs are equal"""
        if input1 != input2:
            print(
                colored(f"{cls._common_message(message)} FAILED (arg1={input1} and arg2={input2} are not equal)", "red")
            )
        else:
            print(colored(f"{cls._common_message(message)} passed", "green"))
        cls._ran_a_test_common()

    @classmethod
    def exp_almost_eq(cls, input1: float, input2: float, message=None, eps=0.000001):
        if abs(input1 - input2) > eps:
            print(
                colored(
                    f"{cls._common_message(message)} FAILED (arg1={input1} and arg2={input2} are not almost equal to {eps} difference)",
                    "red",
                )
            )
        else:
            print(colored(f"{cls._common_message(message)} passed", "green"))
        cls._ran_a_test_common()

    ### Instance methods for backwards compatibility

    def test_true(self, inp, message=None):
        """Assert input is True"""
        return self.exp_true(inp=inp, message=message)

    def test_false(self, inp, message=None):
        """Assert input is False"""
        return self.exp_false(inp=inp, message=message)

    def test_eq(self, input1, input2, message=None):
        """Assert inputs are equal"""
        return self.exp_eq(input1=input1, input2=input2, message=message)

    def test_almost_eq(self, input1: float, input2: float, message=None, eps=0.000001):
        return self.exp_almost_eq(input1=input1, input2=input2, message=message, eps=eps)


class TreeNode:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __repr__(self):
        return "TreeNode({})".format(self.val)


def unsigned_bin_str_to_dec(s_bin: str) -> int:
    """
    Convert string of 0's and 1's to decimal. Treats binary string as unsigned, left most digit is most significant.

    For example: "10011 -> 16 + 2 + 1 = 19
    """
    if s_bin == "" or s_bin is None:
        return 0
    if set(s_bin).issubset({0, 1}):
        raise ValueError("input string contains something other than zeros and ones")
    s_bin = s_bin[::-1]  # Reverse string
    base = 1
    dec_num = 0
    for bit in s_bin:
        if bit == "1":
            dec_num += base
        base *= 2
    return dec_num


def bin_search(arr: list, search_for: object, left: int, right: int) -> int:
    """Binary search"""
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
    """Leet code tree array string to actual tree"""
    if string == "{}":
        return None
    nodes = [None if val == "null" else TreeNode(int(val)) for val in string.strip("[]{}").split(",")]
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
            t.write(node.val, align="center", font=("Arial", 12, "normal"))
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


if __name__ == "__main__":
    main()
