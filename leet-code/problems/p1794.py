from main import UTest


class Solution:
    def countQuadruples(self, s: str, t: str) -> int:

        # Second try
        common = set(s) & set(t)
        if len(common) == 0:
            return 0
        distances = [s.find(c) - t.rfind(c) for c in common]
        min_dist = min(distances)
        return distances.count(min_dist)


UTest.exp_eq(Solution().countQuadruples(s="abcd", t="bccda"), 1)
UTest.exp_eq(Solution().countQuadruples(s="ab", t="cd"), 0)


# from operator import eq
# Single letter
# i == j
# a == b
#         commonLetters = set(firstString).intersection(set(secondString))
#         if len(commonLetters) == 0:
#             return 0
#         smallest = min(firstString.find(c) - secondString.find(c) for c in commonLetters)
#
#         if smallest >= 0:
#             firstString = firstString[smallest:]
#         else:
#             secondString = secondString[-smallest]
#
#         return sum(map(eq, firstString, secondString))
