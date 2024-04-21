#[COMPLETED]

class Solution:
    def fourSum(self, nums, target: int):
        fours = []
        nums = sorted(nums)
        len_nums = len(nums)
        for i in range(len_nums - 3):
            nums_i = nums[i]
            for j in range(i + 1, len_nums - 2):
                nums_j = nums[j]
                l = j + 1
                r = len_nums - 1
                while l < r:
                    nums_l = nums[l]
                    nums_r = nums[r]
                    s = nums_i + nums_j + nums_l + nums_r
                    if s < target:
                        l += 1
                    elif s > target:
                        r -= 1
                    else:
                        temp = [nums_i, nums_j, nums_l, nums_r]
                        if temp not in fours:
                            fours.append(temp)
                        while l < r and nums_l == nums[l + 1]:
                            l += 1
                        while l < r and nums_r == nums[r - 1]:
                            r -= 1
                        l += 1
                        r -= 1
        return fours

sol = Solution()
print(sol.fourSum([0,0,0,0], 0), "expected [[0,0,0,0]]")
print(sol.fourSum([0,0,0,0], 1), "expected []")
print(sol.fourSum([-1,0,1,2,-1,-4], -1), "expected [[-4,0,1,2],[-1,-1,0,1]]")
print(sol.fourSum([0,4,-5,2,-2,4,2,-1,4], 12), "expected [[0,4,4,4],[2,2,4,4]]")
