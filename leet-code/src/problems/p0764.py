import main


class Solution:
    def orderOfLargestPlusSign(self, n: int, mines: list[list[int]]) -> int:
        # For each row, column, find ranges of all ones, then grow like 12345654321
        M = [[False] * n for _ in range(n)]  # true = has a mine
        for mineCoord in mines:
            M[mineCoord[0]][mineCoord[1]] = True
        LR = [[0] * n for _ in range(n)]
        UD = [[0] * n for _ in range(n)]
        # LR
        for y in range(n):
            x = 0
            while x < n:
                if M[y][x] is True:
                    x += 1
                    continue
                end = x + 1
                while end < n and M[y][end] is False:
                    end += 1
                dist = end - x
                for i in range(dist):
                    LR[y][x + i] = min(i + 1, dist - i)
                x = end
        # UD
        for x in range(n):
            y = 0
            while y < n:
                if M[y][x] is True:
                    y += 1
                    continue
                end = y + 1
                while end < n and M[end][x] is False:
                    end += 1
                dist = end - y
                for i in range(dist):
                    UD[y + i][x] = min(i + 1, dist - i)
                y = end

        # Biggest star
        ans = 0
        for y in range(n):
            for x in range(n):
                ans = max(ans, min(LR[y][x], UD[y][x]))
        return ans


test = main.UTest()
sol = Solution()
test.test_eq(sol.orderOfLargestPlusSign(n=5, mines=[[4, 2]]), 2)
