import main


class DSU:
    def __init__(self, N: int):
        self.p = list(range(N))

    def find(self, x):
        if self.p[x] != x:
            self.p[x] = self.find(self.p[x])
        return self.p[x]

    def union(self, x, y):
        xr = self.find(x)
        yr = self.find(y)
        self.p[xr] = yr


class Solution:
    def friendRequests(self, n: int, restrictions: list[list[int]], requests: list[list[int]]) -> list[bool]:
        dsu = DSU(n)
        ans = []
        for x, y in requests:
            x_p = dsu.find(x)
            y_p = dsu.find(y)
            canBeFriends = True
            for a, b in restrictions:
                a_p = dsu.find(a)
                b_p = dsu.find(b)
                if set([a_p, b_p]) == set([x_p, y_p]):
                    canBeFriends = False
                    break
            ans.append(canBeFriends)
            if canBeFriends:
                dsu.union(x, y)

        return ans


sol = Solution()
test = main.UTest()
test.test_eq(sol.friendRequests(n=3, restrictions=[[0, 1]], requests=[[0, 2], [2, 1]]), [True, False])
test.test_eq(sol.friendRequests(n=3, restrictions=[[0, 1]], requests=[[1, 2], [0, 2]]), [True, False])
test.test_eq(
    sol.friendRequests(n=5, restrictions=[[0, 1], [1, 2], [2, 3]], requests=[[0, 4], [1, 2], [3, 1], [3, 4]]),
    [True, False, True, False],
)
