#[COMPLETED]

class NumArray(object):
    """
    :type nums: List[int]
    """
    def __init__(self, nums):
        self.d = {}
        self.arr = nums
        self.c_sum = [0]
        for n in nums:
            self.c_sum.append(self.c_sum[-1] + n)

    """
    :type i: int
    :type j: int
    :rtype: int
    """
    def sumRange(self, i, j):
        return self.c_sum[j+1] - self.c_sum[i]

    """
    :type i: int
    :type j: int
    :rtype: int
    """
    def sumRange_OLD(self, i, j):
        if (i, j) in self.d:
            return self.d[(i, j)]
        # Calculate sum of range
        # Could be better solution if worked off of previous solutions
        if i < 0 or j < i or j >= len(self.arr):
            return None
        sum = 0
        for ind in range(i, j+1):
            sum += self.arr[ind]
        self.d[(i, j)] = sum
        return sum


arr = [-2, 0, 3, -5, 2, -1]
na = NumArray(arr)

assert na.sumRange(0, 2) == 1
assert na.sumRange(2, 5) == -1
assert na.sumRange(0, 5) == -3
print("Passed all")
