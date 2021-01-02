#[COMPLETED]

from typing import List

class Solution:
    def isIdealPermutation(self, A: List[int]) -> bool:
        for i in range(len(A)):
            if abs(A[i] - i) > 1:
                return False
        return True

    def isIdealPermutationSlow(self, A: List[int]) -> bool:
        glob = 0
        loca = 0
        for i in range(len(A) - 1):
            if A[i] > A[i+1]:
                loca += 1
        for j in range(len(A)):
            for i in range(j):
                if A[i] > A[j]:
                    glob += 1
        return glob == loca