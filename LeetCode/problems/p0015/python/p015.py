#[COMPLETED]

class Solution:
    def threeSum(self, nums):
        trips = []
        nums = sorted(nums)
        len_nums = len(nums)
        for i in range(len_nums):
            nums_i = nums[i]
            if i > 0 and nums_i == nums[i-1]:
                continue
            if nums_i > 0:
                break
            l = i + 1
            r = len_nums - 1
            while l < r:
                nums_l = nums[l]
                nums_r = nums[r]
                s = nums_i + nums_l + nums_r
                if s < 0:
                    l += 1
                elif s > 0:
                    r -= 1
                else:
                    trips.append([nums_i, nums_l, nums_r])
                    while l < r and nums_l == nums[l + 1]:
                        l += 1
                    while l < r and nums_r == nums[r - 1]:
                        r -= 1
                    l += 1
                    r -= 1
        return trips


    def threeSum_bad(self, nums):
        trips = []
        len_nums = len(nums)
        # Brute force
        for a in range(0, len_nums):
            for b in range(a + 1, len_nums):
                for c in range(b + 1, len_nums):
                    if nums[a] + nums[b] + nums[c] == 0:
                        temp = sorted([nums[a], nums[b], nums[c]])
                        if temp not in trips:
                            trips.append(temp)
        return trips


