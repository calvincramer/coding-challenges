#[COMPLETED]

class Solution:
    min_cost = []
    comp = []
    def trav(self, cost, i) -> int:

        if i >= len(cost):
            return 0
        if i == len(cost) - 1:
            return cost[i]
        if i == len(cost) - 2:
            return cost[i]
        if self.comp[i] == True:
            return self.min_cost[i]

        t = cost[i] + min(self.trav(cost, i+1), self.trav(cost, i+2))
        if t < self.min_cost[i]:
            self.min_cost[i] = t
            self.comp[i] = True

        return t

    def minCostClimbingStairs(self, cost) -> int:
        s = sum(cost)
        self.min_cost = [s * 5] * len(cost)
        self.comp = [False] * len(cost)
        return min(self.trav(cost, 0), self.trav(cost, 1))

    # Use DP

sol = Solution()
print(sol.minCostClimbingStairs([10, 15, 20]), "expected 15")
print(sol.minCostClimbingStairs([1, 100, 1, 1, 1, 100, 1, 1, 100, 1]), "expected 6")