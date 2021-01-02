#[COMPLETED]

class Solution(object):
    """
    :type s: str
    :rtype: bool
    """
    def isValid(self, s):
        if s == "":
            return True
        stack = []
        opposite_chars = {'[': ']', '(': ')', '{': '}'}
        push_list = ['[', '(', '{']
        for c in s:
            if c in push_list:
                stack.append(c)
            elif len(stack) == 0:
                return False
            elif c != opposite_chars[stack[-1]]:
                return False
            else:
                stack.pop()
        return len(stack) == 0


sol = Solution()
print(sol.isValid("()"), "expected True")
print(sol.isValid("()[]{}"), "expected True")
print(sol.isValid("(]"), "expected False")
print(sol.isValid("([)]"), "expected False")
print(sol.isValid("{[]}"), "expected True")
print(sol.isValid("]"), "expected False")
