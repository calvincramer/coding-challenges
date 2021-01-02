#[COMPLETED]

from typing import List

from main import UTest


class Solution:
    def findTheDistanceValue(self, arr1: List[int], arr2: List[int], d: int) -> int:
        num_gt_d = 0
        for num_1 in arr1:
            num_1_good = True
            for num_2 in arr2:
                if abs(num_1 - num_2) <= d:
                    num_1_good = False
                    break
            if num_1_good:
                num_gt_d += 1
        return num_gt_d


sol = Solution()
test = UTest()
test.test_eq(sol.findTheDistanceValue(arr1=[4,5,8], arr2=[10,9,1,8], d=2), 2)
test.test_eq(sol.findTheDistanceValue(arr1=[1,4,2,3], arr2=[-4,-3,6,10,20,30], d=3), 2)
test.test_eq(sol.findTheDistanceValue(arr1=[2,1,100,3], arr2=[-5,-2,10,-3,7], d=6), 1)
