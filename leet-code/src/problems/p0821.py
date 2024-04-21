#[COMPLETED]

class Solution:
    def shortestToChar(self, S: str, C: str):
        lst = [100000] * len(S)
        for i in range(len(S)):
            if S[i] == C[0]:
                lst[i] = 0
        n = -1
        for i in range(len(lst)):
            if lst[i] == 0:
                n = 1
                continue
            if n != -1:
                lst[i] = n
                n += 1
        n = -1
        for i in range(len(lst) - 1, -1, -1):
            if lst[i] == 0:
                n = 1
                continue
            if n != -1 and lst[i] > n:
                lst[i] = n
                n += 1
        return lst

sol = Solution()
print(sol.shortestToChar("loveleetcode", "e"))