#[COMPLETED]

class Solution(object):
    """
    :type s: str
    :rtype: int
    """
    def longestPalindrome(self, s):
        dic = {}
        length = 0
        for c in s:
            if c in dic:
                length += 2
                del dic[c]
            else:
                dic[c] = True
        return length + 1 if len(s) != length else length


sol = Solution()
print(sol.longestPalindrome("abccccdd"), "expected 7")
