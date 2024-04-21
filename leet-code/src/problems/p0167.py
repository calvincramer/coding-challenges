#[COMPLETED]

from typing import List

from main import UTest


class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        solution = self.real_solution(numbers, target)
        return [i + 1 for i in solution]    # 1-index the solution

    def real_solution(self, numbers: List[int], target: int) -> List[int]:
        def bin_search(search_for: int, l: int, r: int) -> int:
            """ Binary search """
            if r < l:
                return -1
            mid = l + ((r - l) // 2)
            mid_n = numbers[mid]
            if mid_n == search_for:
                return mid
            elif mid_n > search_for:
                return bin_search(search_for, l, mid - 1)
            return bin_search(search_for, mid + 1, r)

        # Corner case for target numbers being the same
        # if we eliminate this corner case, then we can assume the two target numbers are different
        if target % 2 == 0:
            to_find = target // 2
            to_find_i = bin_search(to_find, 0, len(numbers))
            if to_find_i != -1:
                if to_find_i > 0 and numbers[to_find_i - 1] == to_find:
                    return [to_find_i - 1, to_find_i]
                elif to_find_i < len(numbers) - 1 and numbers[to_find_i + 1] == to_find:
                    return [to_find_i, to_find_i + 1]
                # else: target indices will NOT be the same value number. Don't need to care about duplicates

        s = set(numbers)
        for i, n in enumerate(numbers):
            if (target - n) in s:
                other = target - n
                # Find index of other
                other_i = bin_search(other, 0, len(numbers))
                return [i, other_i] if i < other_i else [other_i, i]

        return [-2, -2]  # Valid solution doesn't exist


test = UTest()
s = Solution()
test.test_eq(s.twoSum([2, 7, 11, 15], 9), [1, 2])
test.test_eq(s.twoSum([2, 3, 4], 6), [1, 3])
test.test_eq(s.twoSum([-1, 0], -1), [1, 2])
test.test_eq(s.twoSum([0, 0, 3, 4], 0), [1, 2])


"""
Possible strategies:

1. Sum of each pair of numbers creates a ^ (carat) like shape (2D). Straddle the line to find target

2. Create set, find index. Corner case for solutions where target values are the same

3. Could still use "two pointer" method?
"""