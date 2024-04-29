import main


class Solution:
    def sumZero(self, n: int) -> list[int]:
        even = True if n % 2 == 0 else False
        if even is False:
            n -= 1
        n_half = n // 2
        res = [i for i in range(-n_half, n_half + 1) if i != 0]
        if even is False:
            res.append(0)
        return res


sol = Solution()
test = main.UTest()
test.test_eq(sol.sumZero(5), None)
test.test_eq(sol.sumZero(7), None)
test.test_eq(sol.sumZero(1), None)
