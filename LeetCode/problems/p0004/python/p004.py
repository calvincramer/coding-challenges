#[COMPLETED]

# Credit: dong7: https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2511/Intuitive-Python-O(log-(m%2Bn))-solution-by-kth-smallest-in-the-two-sorted-arrays-252ms

class Solution:
    def find(self, nums1, s1, e1, nums2, s2, e2, k):
        if e1 - s1 < 0:
            return nums2[k + s2]
        if e2 - s2 < 0:
            return nums1[k + s1]
        if k < 1:
            return min(nums1[k + s1], nums2[k + s2])
        ia, ib = (s1 + e1) // 2 , (s2 + e2) // 2
        ma, mb = nums1[ia], nums2[ib]
        if (ia - s1) + (ib - s2) < k:
            if ma > mb:
                return self.find(nums1, s1, e1, nums2, ib + 1, e2, k - (ib - s2) - 1)
            else:
                return self.find(nums1, ia + 1, e1, nums2, s2, e2, k - (ia - s1) - 1)
        else:
            if ma > mb:
                return self.find(nums1, s1, ia - 1, nums2, s2, e2, k)
            else:
                return self.find(nums1, s1, e1, nums2, s2, ib - 1, k)

    def findMedianSortedArrays(self, nums1, nums2):
        l = len(nums1) + len(nums2)
        if l % 2 == 1:
            return self.find(nums1, 0, len(nums1) - 1, nums2, 0, len(nums2) - 1, l // 2)
        else:
            return (self.find(nums1, 0, len(nums1) - 1, nums2, 0, len(nums2) - 1, l // 2) + self.find(nums1, 0, len(nums1) - 1, nums2, 0, len(nums2) - 1, l // 2 - 1)) / 2.0


sol = Solution()
print(sol.findMedianSortedArrays([1, 3], [2]), "expected 2.0")
print(sol.findMedianSortedArrays([1, 2], [3, 4]), "expected 2.5")

print(sol.findMedianSortedArrays([1, 2, 3, 4], [5, 6, 7]), "expected 4")
print(sol.findMedianSortedArrays([5, 6, 7], [1, 2, 3, 4]), "expected 4")
print(sol.findMedianSortedArrays([1, 2, 6], [3, 4, 5, 7]), "expected 4")
print(sol.findMedianSortedArrays([1, 4, 5, 6, 8, 9, 13],  [2, 3, 7, 10, 11, 12]), "expected 7")