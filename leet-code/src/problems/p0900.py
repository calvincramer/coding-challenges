#[COMPLETED]

from typing import List

class RLEIterator:
    def __init__(self, A: List[int]):
        self.A = A
        self.ind = 0

    def next(self, n: int) -> int:
        if self.ind >= len(self.A):
            return -1
        while n > 0:
            # Check if index is good if we incremented it
            if self.ind >= len(self.A):
                return -1
            if self.A[self.ind] < n:
                # Consume some of n
                n -= self.A[self.ind]
                self.ind += 2
            else:
                # Consume all of n
                self.A[self.ind] -= n
                n = 0
                ret = self.A[self.ind + 1]
                if self.A[self.ind] == 0:
                    self.ind += 2
                return ret
        return self.A[self.ind + 1]     # Get the actual number


# Your RLEIterator object will be instantiated and called as such:
# obj = RLEIterator(A)
# param_1 = obj.next(n)

l = [3,8,0,9,2,5]
it = RLEIterator([3,8,0,9,2,5])
ret = None
while ret != -1:
    ret = it.next(1)
    print(ret)
