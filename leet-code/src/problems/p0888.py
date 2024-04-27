import main


class Solution:
    def fairCandySwap(self, aliceSizes: list[int], bobSizes: list[int]) -> list[int]:
        a = sum(aliceSizes)
        b = sum(bobSizes)
        diff = b - a
        assert diff % 2 == 0  # Guaranteed a solution in one swap
        searchDiff = diff // 2
        B = set(bobSizes)
        # Search for a both in each with diff / 2
        for boxA in aliceSizes:
            if searchDiff + boxA in B:
                return [boxA, searchDiff + boxA]
        raise RuntimeError("No solution found")


sol = Solution()
test = main.UTest()
test.test_eq(sol.fairCandySwap(aliceSizes=[1, 1], bobSizes=[2, 2]), [1, 2])
test.test_eq(sol.fairCandySwap(aliceSizes=[1, 2], bobSizes=[2, 3]), [1, 2])
test.test_eq(sol.fairCandySwap(aliceSizes=[2], bobSizes=[1, 3]), [2, 3])
