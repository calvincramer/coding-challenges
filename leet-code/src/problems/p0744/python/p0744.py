from typing import List

import main


class Solution:
    def nextGreatestLetter(self, letters: List[str], target: str) -> str:
        """Binary search"""
        if target >= letters[-1] or target < letters[0]:
            return letters[0]

        low = 0
        high = len(letters) - 1
        while low <= high:
            mid = (low + high) // 2
            if target >= letters[mid]:
                low = mid + 1
            else:
                high = mid - 1
        return letters[low]

    def nextGreatestLetter_slow(self, letters: List[str], target: str) -> str:
        """log(N)"""
        idx = 0
        _len = len(letters)
        while idx < _len and letters[idx] <= target:
            idx += 1
        if idx >= _len:
            return letters[0]
        return letters[idx]


sol = Solution()
test = main.UTest()
test.test_eq(sol.nextGreatestLetter(letters=["c", "f", "j"], target="a"), 'c')
test.test_eq(sol.nextGreatestLetter(letters=["c", "f", "j"], target="d"), 'f')
test.test_eq(sol.nextGreatestLetter(letters=["c", "f", "j"], target="c"), 'f')
test.test_eq(sol.nextGreatestLetter(letters=["x", "x", "y", "y"], target="z"), 'x')
