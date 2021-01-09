#[COMPLETED]

class Solution(object):
    """
    :type nums1: List[int]
    :type nums2: List[int]
    :rtype: List[int]
    """
    def intersection(self, nums1, nums2):
        hash1 = {}
        for n in nums1:
            hash1[n] = None
        inter = []
        hash_inter = {}
        for n in nums2:
            if n in hash1 and n not in hash_inter:
                hash_inter[n] = None
                inter.append(n)
        return inter



nums1 = [4,9,5]
nums2 = [9,4,9,8,4]
sol = Solution()
print(sol.intersection(nums1, nums2), "expected [9,4]")
