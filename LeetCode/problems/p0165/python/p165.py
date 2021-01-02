#[COMPLETED]

import re
class Solution:
    def compareVersion(self, version1: str, version2: str) -> int:
        nums1 = [int(x) for x in re.split("\.", version1)]
        nums2 = [int(x) for x in re.split("\.", version2)]
        minLength = nums1 if len(nums1) < len(nums2) else nums2
        minLength.extend([0] * abs(len(nums1) - len(nums2)))
        for i in range(len(nums1)):
            if nums1[i] == nums2[i]:
                continue
            return 1 if nums1[i] > nums2[i] else -1
        return 0



sol = Solution()
print(sol.compareVersion("0.1", "1.1"), " expected -1")
print(sol.compareVersion("1.0.1", "1"), " expected 1")
print(sol.compareVersion("7.5.2.4", "7.5.3"), " expected -1")
print(sol.compareVersion("1.01", "1.001"), " expected 0")
print(sol.compareVersion("1.0", "1.0.0"), " expected 0")





