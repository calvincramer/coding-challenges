#[COMPLETED]

class Solution(object):
    """
    :type A: List[int]
    :rtype: bool
    """
    def validMountainArray(self, A):
        if len(A) < 3:
            return False
        foundPeak = False
        for i in range(len(A) - 1):
            if not foundPeak:
                # Should be ascending
                if A[i] > A[i+1]:
                    if i == 0:
                        return False    # Need to have at least one ascending part
                    foundPeak = True
                elif A[i] == A[i+1]:
                    return False
            else:
                # Should be descending
                if not A[i] > A[i+1]:
                    return False
        return foundPeak


sol = Solution()
print(sol.validMountainArray([3,5,5]), "expected False")
print(sol.validMountainArray([0,1,2,3,4,5,6,7,8,9]), "expected False")
print(sol.validMountainArray([9,8,7,6,5,4,3,2,1,0]), "expected False")
