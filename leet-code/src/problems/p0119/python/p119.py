#[COMPLETED]

class Solution:
    def getRow(self, rowIndex: int):
        if rowIndex < 0:
            return None
        elif rowIndex == 0:
            return [1]
        prev = [1]
        next = []
        i = 1
        while i <= rowIndex:
            next = [0] * (len(prev) + 1)
            next[0] = 1
            next[-1] = 1
            for k in range(1, len(next) - 1):
                next[k] = prev[k-1] + prev[k]
            i += 1
            prev = next

        return prev

sol = Solution()
print(sol.getRow(0), "expected [1]")
print(sol.getRow(1), "expected [1,1]")
print(sol.getRow(2), "expected [1,2,1]")
print(sol.getRow(3), "expected [1,3,3,1]")
print(sol.getRow(4), "expected [1,4,6,4,1]")
print(sol.getRow(5), "expected [1,5,10,10,5,1]")
print(sol.getRow(6), "expected [1,5,15,20,15,6,1]")