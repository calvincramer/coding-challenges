#[COMPLETED]

class Solution(object):
    """
    :type str: str
    :rtype: str
    """
    def toLowerCase(self, str):
        return str.lower()


sol = Solution()
print(sol.toLowerCase("Hello"), "expected hello")
