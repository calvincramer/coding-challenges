#[COMPLETED]

class Solution:
    def projectionArea(self, grid) -> int:
        tran = list(zip(*grid))
        s = sum([max(r) for r in grid])
        s += sum([max(r) for r in tran])
        s += len(grid) * len(grid[0])
        s -= sum(r.count(0) for r in grid)
        return s


sol = Solution()
print(sol.projectionArea([[1,2],[3,4]]), "expected 17")
print(sol.projectionArea([[2]]), "expected 5")
print(sol.projectionArea([[1,0],[0,2]]), "expected 8")

#l = [[1,2],[3,4],[5,6],[7,8]]
#print([max(r) for r in l])
