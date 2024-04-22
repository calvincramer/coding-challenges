#[COMPLETED]

import random

class RandomizedSet:

    def __init__(self):
        self.nums = []
        self.idxs = {}

    def insert(self, val: int) -> bool:
        if val in self.idxs:
            return False
        self.nums.append(val)
        self.idxs[val] = len(self.nums) - 1
        return True

    def remove(self, val: int) -> bool:
        if val not in self.idxs:
            return False
        index = self.idxs[val]
        last = self.nums[-1]
        self.nums[index] = last
        self.idxs[last] = index
        self.nums.pop()
        self.idxs.pop(val, 0)
        return True

    def getRandom(self) -> int:
        return self.nums[random.randint(0, len(self.nums) - 1)]
