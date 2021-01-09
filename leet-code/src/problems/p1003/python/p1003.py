#[COMPLETED]

class Solution:
    def isValid(self, S: str) -> bool:
        while True:
            ind = S.find("abc")
            if ind == -1:
                break
            S = S[:ind] + S[ind+3:]
        return len(S) == 0


sol = Solution()
print(sol.isValid("aabcbc"), "expected True")
print(sol.isValid("abcabcababcc"), "expected True")
print(sol.isValid("abccba"), "expected False")
print(sol.isValid("cababc"), "expected False")
