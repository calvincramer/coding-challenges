#[COMPLETED]

class Solution(object):
    """
        :type nums: List[int]
        :rtype: int
        """

    def minMoves2(self, nums):
        # Try sort, take middle element
        # This works, but why?
        nums = sorted(nums)
        middle = nums[len(nums) // 2]
        moves = 0
        for n in nums:
            moves += abs(n - middle)
        return moves


    def minMoves2Bad(self, nums):
        # Guessing the number to set every other number to is the mode of the array
        # (the most occurring element)
        # This doesn't work, so maybe take middle element?
        occur = {}
        mode = None
        max_occur = -1
        for n in nums:
            if n not in occur:
                occur[n] = 1
            else:
                occur[n] += 1
            if occur[n] > max_occur:
                max_occur = occur[n]
                mode = n
        moves = 0
        for n in nums:
            moves += abs(n - mode)
        return moves


sol = Solution()
print(sol.minMoves2([1,2,3]), "expected 2")
