import main


class Solution:
    def maximumSetSize(self, nums1: list[int], nums2: list[int]) -> int:
        s = set()
        len1 = len(nums1)
        len1End = len1 // 2
        len2 = len(nums2)
        len2End = len2 // 2

        # Step 1: save one element from each array that's not in the other array
        s1 = set(nums1)
        s2 = set(nums2)
        s1Unique = s1.difference(s2)
        s2Unique = s2.difference(s1)
        for n in s1Unique:
            s.add(n)
            len1 -= 1
            if len1 == len1End:
                break
        for n in s2Unique:
            s.add(n)
            len2 -= 1
            if len2 == len2End:
                break

        # Step 2: What's left to choose from are elements that are present in both arrays (or
        # duplicates of what we've already chosen). Choose one of them between the arrays.
        # Remove from 1 first, then 2
        common = s1.intersection(s2)
        for c in common:
            if len1 != len1End:
                s.add(c)
                len1 -= 1
            elif len2 != len2End:
                s.add(c)
                len2 -= 1
            else:
                break

        # Step 3: all the remaining numbers we can use are already present in s. Wasted work to add
        # them again
        return len(s)


test = main.UTest()
sol = Solution()
test.test_eq(sol.maximumSetSize(nums1=[1, 2, 1, 2], nums2=[1, 1, 1, 1]), 2)
test.test_eq(sol.maximumSetSize(nums1=[1, 2, 3, 4, 5, 6], nums2=[2, 3, 2, 3, 2, 3]), 5)
test.test_eq(sol.maximumSetSize(nums1=[1, 1, 2, 2, 3, 3], nums2=[4, 4, 5, 5, 6, 6]), 6)
test.test_eq(sol.maximumSetSize(nums1=[10, 8, 7, 9], nums2=[7, 9, 9, 5]), 4)
