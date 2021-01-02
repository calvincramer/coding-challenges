#[COMPLETED]

class Solution(object):
    """
    :type s: List[str]
    :rtype: None Do not return anything, modify s in-place instead.
    """
    def reverseString(self, s):
        for i in range(0, len(s) // 2):
            end = len(s) - i - 1
            tmp = s[i]
            s[i] = s[end]
            s[end] = tmp


sol = Solution()
inp = ["h","e","l","l","o"]
exp = ["o","l","l","e","h"]
print("Input", inp)
sol.reverseString(inp)
print("Out", inp)
print("Expected", exp)