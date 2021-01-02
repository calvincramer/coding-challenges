#[COMPLETED]

class Solution:
    def checkPossibility(self, nums) -> bool:
        if len(nums) <= 2:
            return True
        num_moves = 0
        i = 0
        if nums[0] > nums[1]:
            nums[0] = nums[1]
            num_moves += 1

        while i < len(nums) - 2:
            if nums[i+1] > nums[i+2]:
                if nums[i] <= nums[i+2]:
                    nums[i+1] = nums[i]
                else:
                    nums[i+2] = nums[i+1]
                num_moves += 1
            i += 1
        return num_moves < 2


sol = Solution()
print(sol.checkPossibility([4,2,3]), "expected True")
print(sol.checkPossibility([4,2,1]), "expected False")
print(sol.checkPossibility([-1,4,2,3]), "expected True")
print(sol.checkPossibility([1,5,4,6,7,10,8,9]), "expected False")
print(sol.checkPossibility([3,4,2,3]), "expected False")
print(sol.checkPossibility([1,3,2]), "expected True")
print(sol.checkPossibility([2,3,3,2,4]), "expected True")

