#[COMPLETED]

class Solution(object):
    def gcd(self, a, b):
        while b != 0:
            r = a % b
            a = b
            b = r
        return a

    """
    :type nums: List[int]
    :type k: int
    :rtype: None Do not return anything, modify nums in-place instead.
    """
    def rotate(self, nums, k):
        # In place solution
        # number of passes == gcd(len, k)
        # A better way to do it would be to just keep track of the original position
        # and go until there is current == original position, in that case we go to
        # a new "pass", which is just original position + 1, no need to do gcd stuff
        k %= len(nums)
        if k == 0 or len(nums) < 2:
            return nums
        num_passes = self.gcd(len(nums), k)
        for start in range(0, num_passes):
            temp_prev = nums[start]  # Holds the previous number when swapping
            i = start
            num_swaps = 0  # Stop when there is len(nums) swaps
            while num_swaps < len(nums) // num_passes:
                next = (i+k) % len(nums)
                temp_next = nums[next]
                nums[next] = temp_prev
                temp_prev = temp_next
                i = next
                num_swaps += 1

    def rotateInPlace(self, nums, k):
        # Easy not in-place solution
        rotated = [0] * len(nums)
        for i in range(len(nums)):
            rotated[(i+k) % len(nums)] = nums[i]
        for i in range(len(nums)):
            nums[i] = rotated[i]

    def rotateBAD(self, nums, k):
        for i in range(0, len(nums)):
            i_to_swap = (i + k) % len(nums)
            temp = nums[i]
            nums[i] = nums[i_to_swap]
            nums[i_to_swap] = temp
        # Wrong approach, need something else


sol = Solution()
arr = [1,2,3,4,5,6,7,8,9]
sol.rotate(arr, 3)
print(arr, "expected [7,8,9,1,2,3,4,5,6]")

arr = [1,2,3,4,5,6,7]
sol.rotate(arr, 3)
print(arr, "expected [5,6,7,1,2,3,4]")

arr = [1,2]
sol.rotate(arr, 1)
print(arr, "expected [2,1]")

arr = [1,2,3,4,5,6]
sol.rotate(arr, 2)
print(arr, "expected [5,6,1,2,3,4]")

arr = [1,2,3,4,5,6]
sol.rotate(arr, 4)
print(arr, "expected [3,4,5,6,1,2]")
