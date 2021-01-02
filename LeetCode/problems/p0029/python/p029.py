#[IN PROGRESS]

class Solution(object):
    """
    :type dividend: int
    :type divisor: int
    :rtype: int
    """
    def divide(self, dividend, divisor):
        pos = (dividend > 0 and divisor > 0) or (dividend < 0 and divisor < 0)
        dividend = abs(dividend)
        divisor = abs(divisor)
        count = 0
        if divisor == 1:
            return self.bound(dividend) if pos else self.bound(-1 * dividend)
        while dividend >= divisor:
            dividend -= divisor
            count += 1
        return self.bound(count) if pos else self.bound(-1 * count)

    def bound(self, num):
        if num < -2**31:
            return -2**31
        elif num >= 2**31:
            return 2**31 - 1
        return num
