#[IN PROGRESS]

class Solution(object):

    def __init__(self):
        self.arr = [0] * 10
        sum = 0
        for i in range(1,10):   # only care about numbers 10 digits long
            num_digits = i * (10 ** i - 10 ** (i-1))
            sum += num_digits
            self.arr[i] = sum
        print(self.arr)

    """
    :type n: int
    :rtype: int
    """
    def findNthDigit(self, n):
        # find number of digit to look for
        dig = 0
        start = 0
        next = 10
        while dig < 10 and self.arr[dig] < n:
            dig += 1
            start = next
            next *= 10
        start = start // 10
        next = next // 10



        n -= self.arr[dig-1]
        if n < 10:
            return n
        # not get nth digit of the
        return self.findNthDigit(n)



sol = Solution()
print(sol.findNthDigit(3), "expected 3")
print(sol.findNthDigit(11), "expected 0")
print(sol.findNthDigit(110), "expected 6")
print(sol.findNthDigit(111), "expected 0")

