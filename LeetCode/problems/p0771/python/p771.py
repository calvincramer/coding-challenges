#[COMPLETED]

class Solution(object):
    """
    :type J: str
    :type S: str
    :rtype: int
    """
    def numJewelsInStones(self, J, S):
        ht = {}
        for j in J:
            ht[j] = None
        count = 0
        for s in S:
            if s in ht:
                count += 1
        return count


sol = Solution()
print(sol.numJewelsInStones("aA", "aAAbbbb"), "expected 3")
