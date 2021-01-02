#[COMPLETED]

from typing import List

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        lenN = len(nums)
        if not nums:
            return -1
        elif lenN == 1:
            return 0 if nums[0] == target else -1
        # Find pivot
        if nums[0] < nums[-1]:
            pivot = 0
        else:
            low = 0
            high = lenN - 1
            while True:
                mid = (high + low) // 2
                if nums[mid] > nums[mid+1]:
                    pivot = mid + 1
                    break
                midN = nums[mid]
                if midN > nums[low]:
                    low = mid
                else:   # midN < nums[low]
                    high = mid
        # print("pivot is: ", pivot, ": ", nums[pivot])
        # bsearch in modulo space
        low = pivot
        high = pivot + lenN
        while low < high:
            mid = (low + high) // 2
            midN = nums[mid % lenN]
            if midN == target:
                return mid % lenN
            if mid == low:
                return -1
            if midN < target:
                low = mid
            else:
                high = mid
        return -1


sol = Solution()
l = [0,1,2,3,4,5,6,7,8,9]
target = 10
for i in range(len(l)):
    print(l)
    ret = sol.search(l, target)
    print("\tfound ", target, " at index ", ret, sep="")

    l.append(l.pop(0))
    print()

sol.search([0], 0)
