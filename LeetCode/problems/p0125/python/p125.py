#[COMPLETED]

class Solution(object):
    """
    :type s: str
    :rtype: bool
    """
    def isPalindrome(self, s):
        start = 0
        end = len(s) - 1
        while True:
            while start < len(s) and not s[start].isalnum():
                start += 1
            while end >= 0 and not s[end].isalnum():
                end -= 1
            if start >= end:
                break
            if s[start].lower() != s[end].lower():
                return False
            start += 1
            end -= 1
        return True


sol = Solution()
print(sol.isPalindrome("A man, a plan, a canal: Panama"), "expected true")
print(sol.isPalindrome("race a car"), "expected false")
