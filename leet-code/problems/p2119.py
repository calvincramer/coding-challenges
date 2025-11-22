import main


class Solution:
    def isSameAfterReversals(self, num: int) -> bool:
        # Simple cases
        if num == 0:
            return True
        if num % 10 == 0:
            return False
        return True

        # General
        # rev = int(str(num)[::-1])
        # back = int(str(rev)[::-1])
        # return num == back


test = main.UTest()
sol = Solution()
test.test_eq(sol.isSameAfterReversals(526), True)
test.test_eq(sol.isSameAfterReversals(1800), False)
test.test_eq(sol.isSameAfterReversals(0), True)
