#[COMPLETED]

class Solution(object):
    """
    :type num: int
    :rtype: int
    """
    def addDigits(self, num):
        while num >= 10:
            num = self.sumDigits(num)
        return num

    def sumDigits(self, num):
        s = 0
        while True:
            s += num % 10
            num = num // 10
            if num == 0:
                return s


sol = Solution()
print(sol.addDigits(38), "expected 2")