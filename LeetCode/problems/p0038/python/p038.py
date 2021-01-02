#[COMPLETED]

class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        s = "1"
        l = 2
        while l <= n:
            next = ""
            i = 0
            while i < len(s):
                char = s[i]
                j = i + 1
                while j < len(s) and s[j] == char:
                    j += 1
                next = next + str(j - i) + char
                i = j
            s = next
            l += 1
        return s


sol = Solution()
for lev in range(1,30):
    print(sol.countAndSay(lev))