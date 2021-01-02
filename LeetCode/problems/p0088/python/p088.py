#[COMPLETED]

class Solution:
    def merge(self, nums1, m, nums2, n):
        """
        :type nums1: List[int]
        :type m: int
        :type nums2: List[int]
        :type n: int
        :rtype: void Do not return anything, modify nums1 in-place instead.
        """
        while m < len(nums1):
            nums1.pop(m)
        while n < len(nums2):
            nums2.pop(n)

        index1 = 0
        index2 = 0
        while len(nums2) != 0:
            if index1 >= len(nums1):
                break
            if nums1[index1] < nums2[index2]:
                index1 = index1 + 1
            else:
                nums1.insert(index1, nums2.pop(0))
                index1 = index1 + 1

        # dump rest of nums2
        while len(nums2) != 0:
            nums1.append(nums2.pop(0))


s = Solution()
l1 = [1,3,5,7,9]
l2 = [2,4,6,8,20,30]
s.merge(l1, len(l1)-1, l2, len(l2))
print(l1)
print(l2)
