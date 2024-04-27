from typing import List
from collections import deque


class Solution:
    def advantageCount(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1 = deque(sorted(nums1))
        for n2, n2_orig_idx in reversed(sorted([(n2, i) for i, n2 in enumerate(nums2)], key=lambda arr: arr[0])):
            nums2[n2_orig_idx] = nums1.pop() if nums1[-1] > n2 else nums1.popleft()
        return nums2


sol = Solution()
print(sol.advantageCount(nums1=[2, 7, 11, 15], nums2=[1, 10, 4, 11]), "expected [2, 11, 7, 15]")
print(sol.advantageCount(nums1=[12, 24, 8, 32], nums2=[13, 25, 32, 11]), "expected [24, 32, 8, 12]")
print(sol.advantageCount([5621, 1743, 5532, 3549, 9581], [913, 9787, 4121, 5039, 1481]), "expected [1743, 9581, 5532, 5621, 3549]")



"""
First attempt: sort both lists, maintain mapping between original and sorted lists, take advantaged positions greedily

class Solution:
    def advantageCount(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1_sorted = sorted(nums1)
        nums2_sorted = sorted(nums2)

        # Mapping from sorted to orig
        nums1_sorted_to_orig_idxs = dict()
        for i in range(len(nums1)):
            i_map_to = nums1.index(nums1_sorted[i])     # Slow
            nums1_sorted_to_orig_idxs[i] = i_map_to
            nums1[i_map_to] = None  # enforce 1-to-1 mapping

        nums2_sorted_to_orig_idxs = dict()
        for i in range(len(nums2)):
            i_map_to = nums2.index(nums2_sorted[i])     # Slow
            nums2_sorted_to_orig_idxs[i] = i_map_to
            nums2[i_map_to] = None  # enforce 1-to-1 mapping

        # Find the next greatest element in nums2 for each element in nums1 until running out of num2
        i1 = 0
        i2 = 0
        _len = len(nums1)
        while i1 < _len and i2 < _len:
            if nums1_sorted[i1] > nums2_sorted[i2]:
                nums1[nums2_sorted_to_orig_idxs[i2]] = nums1_sorted[i1]  # Move result to advantage spot
                del nums1_sorted_to_orig_idxs[i1]
                del nums2_sorted_to_orig_idxs[i2]
                i1 += 1
                i2 += 1
                continue
            # Skip over this num1, we need a larger num1
            i1 += 1

        # Fill in any remaining num1 spots
        for missing_idx, idx_num1 in zip(nums2_sorted_to_orig_idxs.values(), nums1_sorted_to_orig_idxs.keys()):
            nums1[missing_idx] = nums1_sorted[idx_num1]

        return nums1
"""