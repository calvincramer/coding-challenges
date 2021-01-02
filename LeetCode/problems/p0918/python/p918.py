#[IN PROGRESS]

from typing import List

class Solution:
    def maxSubarraySumCircular(self, A: List[int]) -> int:
        return self.trav(A, 0, 0, 0)

    def trav(self, A, i, cur_length, prev_sum):
        # Either add i to end of current list
        # or make new new list starting here
        # or don't take current, continue with next index
        if 0 > i >= len(A):
            return 0
        # starting from here (either singleton or longer)
        here = max(A[i], A[i] + self.trav(A, i+1, cur_length+1))
        # continue from previous
        cont = prev_sum + A[i]

sol = Solution()
print(sol.maxSubarraySumCircular([1,-2,3,-2]), "expected 3")
print(sol.maxSubarraySumCircular([5,-3,5]), "expected 10")
print(sol.maxSubarraySumCircular([3,-1,2,-1]), "expected 4")
print(sol.maxSubarraySumCircular([3,-2,2,-3]), "expected 3")
print(sol.maxSubarraySumCircular([-2,-3,-1]), "expected -1")
