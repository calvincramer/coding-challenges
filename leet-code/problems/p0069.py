#[COMPLETED]

class Solution(object):
    """
    :type x: int
    :rtype: int
    """
    def mySqrt(self, x):
        # Try binary search
        low = 0
        high = x + 1
        while not ():
            mid = (high + low) // 2
            mid_val = mid * mid
            next_val = (mid + 1) * (mid + 1)

            if mid_val <= x < next_val:
                break
            if mid_val <= x:    # Take higher
                low = mid
            else:               # Take lower
                high = mid
        return mid


sol = Solution()
for i in range(0, 100):
    pred = sol.mySqrt(i)
    real = int(i**0.5)
    print(i, " - ", pred, " - ", real, end="")
    if pred != real:
        print("WRONG")
    else:
        print()