#[COMPLETED]

class Solution(object):
    """
    :type s: str
    :rtype: int
    """
    def lengthOfLastWord(self, s):
        i = len(s) - 1
        while i >= 0 and s[i] == " ":
            i -= 1
        if i == -1:
            return 0
        start = i
        while i >= 0 and s[i] != " ":
            i -= 1
        return start - i


sol = Solution()
print(sol.lengthOfLastWord("Hello World"), "expected 5")
print(sol.lengthOfLastWord("a"), "expected 1")
print(sol.lengthOfLastWord("   "), "expected 0")
print(sol.lengthOfLastWord("a "), "expected 1")
