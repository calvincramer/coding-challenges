import main


class Solution:
    def countPoints(self, rings: str) -> int:
        # 10 rods, 0 to 9
        # n = num rings
        # len(rings) = 2n
        rods = [set() for _ in range(10)]
        for i in range(0, len(rings), 2):
            color = rings[i]
            rod = int(rings[i + 1])
            rods[rod].add(color)
        return sum(1 for rod in rods if len(rod) == 3)


test = main.UTest()
sol = Solution()
test.test_eq(sol.countPoints(rings="B0B6G0R6R0R6G9"), 1)
test.test_eq(sol.countPoints(rings="B0R0G0R9R0B0G0"), 1)
test.test_eq(sol.countPoints(rings="G4"), 0)
