#[COMPLETED]

class Solution(object):
    def largeGroupPositions(self, S):
        """
        :type S: str
        :rtype: List[List[int]]
        """
        large_groups = []
        i = 0
        while i < len(S):
            j = i + 1
            while j < len(S) and S[i] == S[j]:
                j += 1
            if j-i >= 3:
                large_groups.append([i, j-1])
            i = j
        return large_groups


sol = Solution()
print(sol.largeGroupPositions("abbxxxxzzy"), "expected [[3,6]]")
print(sol.largeGroupPositions("abc"), "expected []")
print(sol.largeGroupPositions("abcdddeeeeaabbbcd"), "expected [[3,5],[6,9],[12,14]]")
