#[IN PROGRESS]

class Solution(object):
    """
    :type A: List[int]
    :rtype: str
    """
    def largestTimeFromDigits(self, A):
        res = ""
        ind_1 = self.range_max(A, 2)
        if ind_1 is None:
            return ""
        num1 = A[ind_1]
        res += str(num1)
        A[ind_1] = 20

        ind_2 = self.range_max(A, 9) if num1 != 2 else self.range_max(A, 3)
        if ind_2 is None:
            return ""
        num2 = A[ind_2]
        res += str(num2)
        A[ind_2] = 20

        res += ":"

        ind_3 = self.range_max(A, 5)
        if ind_3 is None:
            return ""
        num3 = A[ind_3]
        res += str(num3)
        A[ind_3] = 20

        ind_4 = self.range_max(A, 9)
        if ind_4 is None:
            return ""
        res += str(A[ind_4])

        return res

    def range_max(self, lst, max):
        max_n = lst[0] if lst[0] <= max else None
        ind = 0 if max_n is not None else None
        for i,n in enumerate(lst):
            if max_n is None:
                if n <= max:
                    max_n = n
                    ind = i
            elif max_n < n <= max:
                max_n = n
                ind = i
        return ind


sol = Solution()
print(sol.largestTimeFromDigits([1,2,3,4]), "expected 23:41")
print(sol.largestTimeFromDigits([5,5,5,5]), "expected ")
print(sol.largestTimeFromDigits([0,0,0,0]), "expected 00:00")
print(sol.largestTimeFromDigits([4,2,4,4]), "expected ")
print(sol.largestTimeFromDigits([2,0,6,6]), "expected 06:26")
