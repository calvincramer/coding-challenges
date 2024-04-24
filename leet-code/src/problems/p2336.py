class SmallestInfiniteSet:
    def __init__(self):
        self.arr = []  # low to high in order of numbers added back
        self.next = 1  # from this number to infinity pretend like it's in self.arr

    def popSmallest(self) -> int:
        if len(self.arr) > 0:
            return self.arr.pop(0)
        ret = self.next
        self.next += 1
        return ret

    def addBack(self, num: int) -> None:
        if num >= self.next:
            return None
        if len(self.arr) == 0:
            self.arr.append(num)
            return None
        self._helperAdd(num=num, low=0, high=len(self.arr))
        return None

    def _helperAdd(self, num: int, low: int, high: int) -> None:
        if high <= low:
            self.arr.insert(low, num)
            return None
        mid = low + ((high - low) // 2)
        if num > self.arr[mid]:
            self._helperAdd(num=num, low=mid + 1, high=high)
        elif num < self.arr[mid]:
            self._helperAdd(num=num, low=low, high=mid)
        # else they are equal -> don't add since this is a set
        return None


# Your SmallestInfiniteSet object will be instantiated and called as such:
# obj = SmallestInfiniteSet()
# param_1 = obj.popSmallest()
# obj.addBack(num)

sol = SmallestInfiniteSet()
sol.addBack(2)
print(sol.popSmallest())
print(sol.popSmallest())
print(sol.popSmallest())
sol.addBack(1)
print(sol.popSmallest())
print(sol.popSmallest())
print(sol.popSmallest())
