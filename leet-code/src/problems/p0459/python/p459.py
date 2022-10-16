#[COMPLETED]

class Solution:
    def repeatedSubstringPattern(self, s):
        for l in range(1, len(s) // 2 + 1):
            if len(s) % l != 0:
                continue
            good = True
            for i in range(l, len(s)):
                if s[i % l] != s[i]:
                    good = False
                    break
            if good:
                return True
        return False

sol = Solution()
print(sol.repeatedSubstringPattern("abcabcabc"), " expected true")
print(sol.repeatedSubstringPattern("abac"), " expected false")
print(sol.repeatedSubstringPattern("abab"), " expected true")
