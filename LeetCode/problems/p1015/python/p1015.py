#[COMPLETED]

class Solution:
    def smallestRepunitDivByK(self, K: int) -> int:
        if K % 2 == 0 or K % 5 == 0:
            return -1
        N = int("1" * len(str(K)))
        while N % K != 0:
            N = N*10 + 1
        return len(str(N))

