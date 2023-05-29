class Solution:
    def checkString(self, s: str) -> bool:
        found_b = False
        for c in s:
            if c == 'a':
                if found_b is True:
                    return False
            else:
                found_b = True
        return True


sol = Solution()
print(sol.checkString("aaabbb"), "expected true")
print(sol.checkString("abab"), "expected false")
print(sol.checkString("bbb"), "expected true")
print(sol.checkString("ba"), "expected false")
