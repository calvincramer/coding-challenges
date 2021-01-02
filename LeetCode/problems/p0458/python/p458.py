#[IN PROGRESS]

import math

class Solution:
    def poorPigs(self, buckets: int, minutesToDie: int, minutesToTest: int) -> int:
        if buckets <= 1:
            return 0        # No testing required
        if minutesToTest < minutesToDie:
            return False    # Can't figure out answer in time allowed

        # Try one pig first, then increase to more pigs
        for num_pigs in range(1, buckets):
            base = num_pigs + 1
            # Figure out range that num_pigs reduces the next step to
            num_buckets_left = int(math.pow(base, math.ceil(math.log(buckets, base)))) // base
            result = self.poorPigs(num_buckets_left, minutesToDie, minutesToTest - minutesToDie)
            if result is False:
                continue
            else:
                return result + num_pigs
        return False

# Current solution doesn't increment the first level of pigs, but the lowest level
# Is it true that the minimum # of pigs will use the largest at the start and less in next trials?



sol = Solution()
print(sol.poorPigs(1000, 15, 60), "expected 5")
