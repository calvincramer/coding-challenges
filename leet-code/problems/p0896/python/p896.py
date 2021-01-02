#[COMPLETED]

class Solution:
    def isMonotonic(self, A):
        if A is None:
            return False
        if len(A) <= 2:
            return True
        minc = True
        for i in range(len(A) - 1):
            if A[i] > A[i+1]:
                minc = False
                break
        if minc:
            return True
        mdec = True
        for i in range(len(A) - 1):
            if A[i] < A[i+1]:
                mdec = False
                break
        if mdec:
            return True
        return False
