#!/usr/bin/env python3


import main

import math


class Solution:
    def minScoreTriangulation(self, values: list[int]) -> int:
        n = len(values)
        dp = [[0] * n for _ in range(n)]  # Table
        for d in range(2, n):  # diagonal number
            for i in range(n - d):  # y
                j = i + d  # x
                dp[i][j] = math.inf
                for k in range(i + 1, j):  # third vertex to make i -> j -> k
                    # triangles i->k->something, k->j->something, i->j->k
                    dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[j] * values[k])
        return dp[0][n - 1]

    def minScoreTriangulationSlow(self, values: list[int]) -> int:
        if len(values) < 3:
            return 0
        if len(values) == 3:
            return values[0] * values[1] * values[2]
        score = math.inf
        for a in range(len(values)):
            for b in range(a + 1, len(values)):
                for c in range(b + 1, len(values)):
                    temp = values[a] * values[b] * values[c]
                    if b - a >= 2:
                        temp += self.minScoreTriangulationSlow(values[a : b + 1])
                    if c - b >= 2:
                        temp += self.minScoreTriangulationSlow(values[b : c + 1])
                    if len(values) + a - c >= 2:
                        temp += self.minScoreTriangulationSlow(values[c:] + values[0 : a + 1])
                    score = min(score, temp)
        return score


sol = Solution()
test = main.UTest()
test.test_eq(sol.minScoreTriangulation([1, 2, 3]), 6)
test.test_eq(sol.minScoreTriangulation([3, 7, 4, 5]), 144)
test.test_eq(sol.minScoreTriangulation([3, 7, 4, 5]), 144)
test.test_eq(sol.minScoreTriangulation([1, 3, 1, 4, 1, 5]), 13)
test.test_eq(sol.minScoreTriangulation([22, 99, 31, 99, 75, 67, 95, 54, 31, 48]), 731390)
