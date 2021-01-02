#[COMPLETED]

# The isBadVersion API is already defined for you.
# @param version, an integer
# @return a bool
def isBadVersion(version):
    return version >= 2

class Solution:
    def firstBadVersion(self, n):
        """
        :type n: int
        :rtype: int
        """
        low = 0
        high = n-1
        while True:
            mid = (low + high) // 2  # Index, not version, add 1 to get version
            if mid == low and mid == high:
                return mid + 1
            # If current is bad version, take low half, try again
            if isBadVersion(mid + 1) == True:
                high = mid
                continue
            # If next is also bad version, take upper half
            if isBadVersion(mid + 2) == False:
                low = mid
                continue
            return mid + 2

# Just do binary search
# Versions = [1,2,3,4....n]
sol = Solution()
for i in range(1,10):
    vers = []
    for k in range(1, i+1):
        vers.append(isBadVersion(k))
    print("Versions: ", vers)
    print(sol.firstBadVersion(i), "expected 2\n")