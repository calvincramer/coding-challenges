#[COMPLETED]

class Solution(object):
    """
    :type n: int
    :rtype: int
    """
    def trailingZeroes(self, n):
        # O(log_5(n))
        num_z = 0
        div = 5
        while div <= n:
            num_z += n // div
            div *= 5
        return num_z

    def fac(self, n):
        if n <= 0:
            return 1
        f = 1
        while n >= 2:
            f *= n
            n -= 1
        return f


sol = Solution()
for n in range(0, 10000):
    f = sol.fac(n)
    copy_f = f
    num_zeros = 0
    while copy_f % 10 == 0:
        num_zeros += 1
        copy_f = copy_f // 10
    #guess = (n // 5) + (n // 25) + (n // 125) + (n // 625) + (n // 3125)
    guess = sol.trailingZeroes(n)
    print(n, num_zeros, guess, end=" ")
    error = abs(num_zeros - guess)
    if error != 0:
        print("ERROR=", error)
    else:
        print()
