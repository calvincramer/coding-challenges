#[COMPLETED]

class Solution:
    def reverseStr(self, s: str, k: int) -> str:
        s = list(s)     # to char array
        i = 0
        while i + k < len(s):
            s[i:i+k] = s[i:i+k][::-1]
            i += 2*k
        # Special case, reverse remaining characters
        if i < len(s):
            s[i:] = s[i:][::-1]
        return "".join(s)


sol = Solution()
print(sol.reverseStr(s = "abcdefg", k = 2), "expected bacdfeg")
