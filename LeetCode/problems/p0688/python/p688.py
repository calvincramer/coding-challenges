#[COMPLETED]

class Solution:
    # Y offset, x offset
    moves = [(-1, -2), (-2, -1), (-2, 1), (-1, 2), (1, 2), (2, 1), (2, -1), (1, -2)]

    # N x N checkboard
    # K moves
    # r, c = position start
    def knightProbability(self, N, K, r, c):
        dp = [[0] * N for _ in range(N)]
        dp[r][c] = 1
        for _ in range(K):
            dp2 = [[0] * N for _ in range(N)]
            for r, row in enumerate(dp):
                for c, val in enumerate(row):
                    for dr, dc in self.moves:
                        if 0 <= r + dr < N and 0 <= c + dc < N:
                            dp2[r + dr][c + dc] += val / 8.0
            dp = dp2

        return sum(map(sum, dp))


sol = Solution()
print(sol.knightProbability(3, 2, 0, 0), "expected 0.0625")
print(sol.knightProbability(8,30, 6, 4), "expected ?")
