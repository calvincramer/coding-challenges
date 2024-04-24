import main


class Solution:
    def minimumLines(self, stockPrices: list[list[int]]) -> int:
        if len(stockPrices) == 1:
            return 0
        stockPrices = sorted(stockPrices, key=lambda x: x[0])  # Sort by day
        prevRun = stockPrices[1][0] - stockPrices[0][0]
        prevRise = stockPrices[1][1] - stockPrices[0][1]
        nLines = 1
        for i in range(2, len(stockPrices)):
            run = stockPrices[i][0] - stockPrices[i - 1][0]
            rise = stockPrices[i][1] - stockPrices[i - 1][1]
            # if previous rise/run not the same as this one then add a line
            if prevRise * run != rise * prevRun:
                nLines += 1
            prevRun = run
            prevRise = rise
        return nLines


test = main.UTest()
sol = Solution()
test.test_eq(sol.minimumLines(stockPrices=[[1, 7], [2, 6], [3, 5], [4, 4], [5, 4], [6, 3], [7, 2], [8, 1]]), 3)
test.test_eq(sol.minimumLines(stockPrices=[[3, 4], [1, 2], [7, 8], [2, 3]]), 1)
