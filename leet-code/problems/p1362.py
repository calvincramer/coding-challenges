import main


class Solution:
    def closestDivisors(self, num: int) -> list[int]:
        num_plus_1 = num + 1
        num_plus_2 = num + 2
        for d in range(int((num + 2) ** 0.5), 0, -1):
            if num_plus_1 % d == 0:
                return [d, num_plus_1 // d]
            if num_plus_2 % d == 0:
                return [d, num_plus_2 // d]
        raise ValueError()


test = main.UTest()
sol = Solution()
test.test_eq(sol.closestDivisors(8), [3, 3])
test.test_eq(sol.closestDivisors(123), [5, 25])
test.test_eq(sol.closestDivisors(999), [25, 40])
