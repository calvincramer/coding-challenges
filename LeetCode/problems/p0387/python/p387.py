#[COMPLETED]

class Solution:
    def firstUniqChar(self, s: str) -> int:
        count = [False] * 26
        for i in range(len(s)):
            count[ord(s[i]) - ord('a')] += 1
        for i in range(len(s)):
            if count[ord(s[i]) - ord('a')] == 1:
                return i
        return -1

sol = Solution()
print(sol.firstUniqChar("leetcode"), "expected 0")
print(sol.firstUniqChar("loveleetcode"), "expected 2")
print(sol.firstUniqChar("aabbcc"), "expected -1")