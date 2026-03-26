class Solution:
    def maxDistance(self, arrays: list[list[int]]) -> int:
        res = 0
        _min = arrays[0][0]
        _max = arrays[0][-1]
        for arr in arrays[1:]:
            res = max(res, abs(_max - arr[0]), abs(arr[-1] - _min))
            _min = min(_min, arr[0])
            _max = max(_max, arr[-1])
        return res


from main import UTest

UTest.exp_eq(Solution().maxDistance([[1, 2, 3], [4, 5], [1, 2, 3]]), 4)
UTest.exp_eq(Solution().maxDistance([[1], [1]]), 0)
