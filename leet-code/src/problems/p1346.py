import main


class Solution:
    def check(self, arr) -> bool:
        s = set(arr)
        s2 = set(2 * n for n in s)
        return len(s.intersection(s2)) >= 1

    def checkIfExist(self, arr: list[int]) -> bool:
        pos = [n for n in arr if n > 0]
        neg = [abs(n) for n in arr if n < 0]
        zeroes = [n for n in arr if n == 0]
        if len(zeroes) > 1:
            return True
        return self.check(pos) or self.check(neg)


sol = Solution()
test = main.UTest()
test.test_true(sol.checkIfExist([10, 2, 5, 3]))
test.test_false(sol.checkIfExist([3, 1, 7, 11]))
test.test_true(sol.checkIfExist([-10, 12, -20, -8, 15]))
test.test_false(sol.checkIfExist([-16, -13, 8]))
