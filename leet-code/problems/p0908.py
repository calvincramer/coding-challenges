class Solution:
    def smallestRangeI(self, nums: list[int], k: int) -> int:
        score = max(nums) - k - (min(nums) + k)
        score = max(score, 0)
        return score
