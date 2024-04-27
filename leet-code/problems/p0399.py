#[COMPLETED]

class Solution(object):
    """
    :type equations: List[List[str]]
    :type values: List[float]
    :type queries: List[List[str]]
    :rtype: List[float]
    """
    def calcEquation(self, equations, values, queries):
        # Build map
        map = {}
        # Keep track of variables
        vars = {}
        for i in range(len(equations)):
            if equations[i][0] not in map:
                map[equations[i][0]] = [(equations[i][1], values[i])]
            else:
                map[equations[i][0]].append((equations[i][1], values[i]))
            if equations[i][1] not in map:
                map[equations[i][1]] = [(equations[i][0], 1 / values[i])]
            else:
                map[equations[i][1]].append((equations[i][0], 1 / values[i]))
            vars[equations[i][0]] = None
            vars[equations[i][1]] = None

        answers = []
        for query in queries:
            if query[0] not in vars or query[1] not in vars:
                answers.append(-1.0)
                continue
            val = self.tree_search(map, query[0], query[1], [])
            answers.append(-1 if val is None else val)
        return answers

    # Returns value of search, or None if not found
    def tree_search(self, map, cur, end, already_seen):
        if cur == end:
            return 1.0
        already_seen.append(cur)
        for next, mult in map[cur]:
            if next in already_seen:
                continue
            res = self.tree_search(map, next, end, already_seen)
            if res is not None:
                return mult * res
        return None


sol = Solution()
eqs = [["a", "b"], ["b", "c"]]
vals = [2.0, 3.0]
queries = [["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"]]
print(sol.calcEquation(eqs, vals, queries))
