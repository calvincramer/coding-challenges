#[COMPLETED]

from typing import List

class Solution:
    def circularArrayLoop(self, nums: List[int]) -> bool:
        # O(n^2)
        # Cycle:
        #   1. start and end in same index
        #   2. length of cycle >= 2
        #   3. Cycle can only be comprised of forward, or only backward movement
        if not nums or len(nums) <= 1:
            return False

        for start_ind in range(len(nums)):
            # Can't make loop with a zero
            if nums[start_ind] == 0:
                continue
            direction = 1 if nums[start_ind] > 0 else -1  # Rightwards = 1, Leftwards = -1
            i = start_ind   # Current place
            num_steps = 0
            cycled = False
            while num_steps < len(nums):
                # Step
                i = (i + nums[i]) % len(nums)
                num_steps += 1
                # Check good or bad
                if nums[i] * direction <= 0:    # Opposite direction
                    break
                if i == start_ind:
                    if num_steps >= 2 and cycled is False:
                        return True
                    cycled = True
        return False


sol = Solution()
print(sol.circularArrayLoop([2,-1,1,2,2]), "expected True")
print(sol.circularArrayLoop([-1,2]), "expected False")
print(sol.circularArrayLoop([-2,1,-1,-2,-2]), "expected False")
