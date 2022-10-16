#[COMPLETED]

class Solution(object):
    """
    :type haystack: str
    :type needle: str
    :rtype: int
    """
    def strStr(self, haystack, needle):
        if needle == "":
            return 0
        for i in range(len(haystack) - len(needle) + 1):
            if haystack[i:i+len(needle)] == needle:
                return i
        return -1


sol = Solution()
print(sol.strStr("a", "a"), "expected 0")
