from typing import List


class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        queries_sorted = sorted(queries)
        num_idx = 0
        num_len = len(nums)
        partial_sum = 0
        answers = dict()
        for target in queries_sorted:
            if target in answers:
                continue
            while num_idx < num_len and partial_sum <= target:
                partial_sum += nums[num_idx]
                num_idx += 1
            if partial_sum > target:
                answers[target] = num_idx - 1
            else:
                # Did not exceed and no more numbers, take what is left
                answers[target] = num_idx
        for i in range(len(queries)):
            queries[i] = answers[queries[i]]
        return queries


sol = Solution()
print(sol.answerQueries(nums=[4, 5, 2, 1], queries=[3, 10, 21]), "expected [2,3,4]")
print(sol.answerQueries(nums=[2, 3, 4, 5], queries=[1]), "expected [0]")
