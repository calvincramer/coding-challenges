#[COMPLETED]

class Solution:
    def transpose(self, A):
        t = []
        for i in range(len(A[0])):
            t.append([None] * len(A))
        for y in range(len(A)):
            for x in range(len(A[0])):
                t[x][y] = A[y][x]
        return t


sol = Solution()
m = [[1,2],[3,4],[5,6]]
print(m)
print()
print(sol.transpose(m))
