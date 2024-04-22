def reverseNum(n: int) -> int:
    return int(str(n)[::-1])


class Solution:
    def sumOfNumberAndReverse(self, num: int) -> bool:
        for n in range(0, num + 1):  # (num // 2)
            if n + reverseNum(n) == num:
                return True
        return False


print(Solution().sumOfNumberAndReverse(443), "expect True")
print(Solution().sumOfNumberAndReverse(63), "expect False")
print(Solution().sumOfNumberAndReverse(181), "expect True")
