#[COMPLETED]

class Solution:
    def reverseBits(self, n: int) -> int:
        new_n = 0
        for i in range(32):
            bit = 1 if n & (1 << i) else 0
            new_n += bit << (31 - i)
        return new_n

sol = Solution()
print(sol.reverseBits(43261596), "expected 964176192")
print(sol.reverseBits(4294967293), "expected 3221225471")
