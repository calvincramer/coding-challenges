import main


class Solution:
    def helper(self, prev: int, prevProbInvHere: int, curr: int, t: int) -> None:
        if t > self.tMax:
            return
        if curr != prev:
            self.invProbs[prev] = None  # can't ever get back to previous node since tree
        unvisitedEdges = self.edges[curr].difference(self.visited)
        if prev is None:
            self.invProbs[curr] = 1
        else:
            self.invProbs[curr] = prevProbInvHere
        self.visited.add(curr)
        probForNext = self.invProbs[curr] * len(unvisitedEdges)
        for c in unvisitedEdges:
            self.helper(prev=curr, prevProbInvHere=probForNext, curr=c, t=t + 1)
        return

    def frogPosition(self, n: int, edges: list[list[int]], t: int, target: int) -> float:
        if len(edges) == 0:
            return 1.0
        self.edges: dict[int, set] = dict()
        for edge in edges:
            a, b = edge
            if a not in self.edges.keys():
                self.edges[a] = set()
            if b not in self.edges.keys():
                self.edges[b] = set()
            self.edges[a].add(b)
            self.edges[b].add(a)
        self.tMax = t
        self.invProbs = dict()
        for edge in edges:
            a, b = edge
            if a not in self.invProbs.keys():
                self.invProbs[target] = None
            if b not in self.invProbs.keys():
                self.invProbs[target] = None
        self.visited = set()
        self.helper(prev=None, prevProbInvHere=None, curr=1, t=0)
        # Done
        if self.invProbs[target] == None:
            return 0.0
        return 1.0 / self.invProbs[target]


sol = Solution()
test = main.UTest()
test.test_almost_eq(sol.frogPosition(n=7, edges=[[1, 2], [1, 3], [1, 7], [2, 4], [2, 6], [3, 5]], t=2, target=4), 0.16666666666666666)
test.test_almost_eq(sol.frogPosition(n=7, edges=[[1, 2], [1, 3], [1, 7], [2, 4], [2, 6], [3, 5]], t=1, target=7), 0.3333333333333333)
test.test_almost_eq(sol.frogPosition(n=8, edges=[[2, 1], [3, 2], [4, 1], [5, 1], [6, 4], [7, 1], [8, 7]], t=7, target=7), 0.0)
test.test_almost_eq(sol.frogPosition(n=1, edges=[], t=1, target=1), 1)
