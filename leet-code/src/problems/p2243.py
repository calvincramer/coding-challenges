class Solution:
    def digitSum(self, s: str, k: int) -> str:
        while len(s) > k:
            new_s = ''
            for i in range(0, len(s), k):
                new_s += str(sum([int(c) for c in s[i:i+k]]))
            s = new_s
        return s


sol = Solution()
print(sol.digitSum('11111222223', 3))
