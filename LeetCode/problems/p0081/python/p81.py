#[COMPLETED]

from typing import List

class Solution:
    def search(self, nums: List[int], target: int) -> bool:
        lenN = len(nums)
        if not nums:
            return False
        elif lenN == 1:
            return True if nums[0] == target else False
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
                elif midN < nums[low]:
                    high = mid
                else:   # midN == nums[low]
                    low += 1    # Don't know if there could be something inbetween low and mid
                    if low >= lenN - 1:
                        pivot = 0
                        break
        # print("pivot is: ", pivot, ": ", nums[pivot])
        # bsearch in modulo space
        low = pivot
        high = pivot + lenN
        while low < high:
            mid = (low + high) // 2
            midN = nums[mid % lenN]
            if midN == target:
                return True
            if mid == low:
                return False
            if midN < target:
                low = mid
            else:
                high = mid
        return False


sol = Solution()
l = [0,1,1,2,2,3,3,4,4,5]
target = 3
for i in range(0, len(l)):
    print(l)
    ret = sol.search(l, target)
    print("\tfound ", target, " at index ", ret, sep="")

    l.append(l.pop(0))
    print()

print(sol.search([0], 0), "expected True")
print(sol.search([], 5), "expected False")
print(sol.search([1,1], 0), "expected False")
print(sol.search([1,1,3,1], 3), "expected True")