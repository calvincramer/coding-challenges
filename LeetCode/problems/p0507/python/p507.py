#[COMPLETED]

class Solution(object):
    # Returns a list of divisors for a positive number except for itself
    def divisors(self, num):
        divs = [1]
        if num % 2 == 0:
            divs.extend([2, num // 2])
        for d in range(3, int(num**0.5) + 1, 1):
            if num % d == 0:
                divs.extend([d, num // d])
        return divs

    """
    :type num: int
    :rtype: bool
    """
    def checkPerfectNumber(self, num):
        if num < 0:
            return False
        elif num == 1:
            return False
        return sum(self.divisors(num)) == num


sol = Solution()
print(sol.checkPerfectNumber(28), "expected True")
print(sol.divisors(28))
print(sol.checkPerfectNumber(-6), "expected False")