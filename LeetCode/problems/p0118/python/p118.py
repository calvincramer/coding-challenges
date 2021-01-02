#[COMPLETED]

class Solution(object):
    def generate(self, numRows):
        """
        :type numRows: int
        :rtype: List[List[int]]
        """
        if numRows <= 0:
            return []
        tri = [[1]]
        rowI = 1    # Row index
        while rowI < numRows:
            nrow = range(len(tri[rowI-1]) + 1)
            nrow = [tri[rowI-1][i-1] + tri[rowI-1][i] for i in nrow if i != 0 and i != rowI]
            nrow.insert(0,1)
            nrow.append(1)
            tri.append(nrow)
            rowI += 1
        return tri

sol = Solution()
print(sol.generate(10))