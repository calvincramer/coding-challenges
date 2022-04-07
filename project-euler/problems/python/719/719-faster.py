#!/usr/bin/env python3.7
import math
import time
from typing import Optional


SPLIT = 0
JOIN = 1
UNDECIDED = 2


class Splitter:
    def __init__(self):
        self.target = None
        self.nums = None
        self.split_joins = None
        self.nums_len = None
        self.split_joins_len = None
        self.total_time_1 = 0
        self.total_time_2 = 0

    def split(self, n_sqr, n) -> bool:
        start = time.time()
        self.nums = [int(c) for c in str(n_sqr)]
        self.nums_len = len(self.nums)
        self.split_joins = [UNDECIDED] * (self.nums_len - 1)
        self.target = n
        self.split_joins_len = len(self.split_joins)
        self.total_time_1 += time.time() - start

        start = time.time()
        ans = self._smart_tree_search(split_join_idx=0)
        self.total_time_2 += time.time() - start
        return ans


    def _calculate_total(self, undecided_use=JOIN) -> int:
        """
        Calculate the value of an array of digits with decisions to either join or split each one.
        Can change '?' values to a either join or split
        """
        value = 0
        i = 0
        def is_j(index):
            _tmp = self.split_joins[index]
            return _tmp == JOIN or (_tmp == UNDECIDED and undecided_use == JOIN)

        while i < self.nums_len:
            temp_sum = self.nums[i]
            if i == self.split_joins_len:    # Last number special
                value += temp_sum
                break
            if is_j(i):
                k = i
                while k < self.split_joins_len and is_j(k):
                    k += 1
                    temp_sum = (temp_sum*10) + self.nums[k]
                i = k + 1
            else:   # split -> 's':
                i += 1
            value += temp_sum
        return value

    def _smart_tree_search(self, split_join_idx: int) -> bool:
        """Don't descent down impossible paths"""
        # Leaf
        if split_join_idx + 1 == self.nums_len:
            return self.target == self._calculate_total()


        if self._calculate_total(undecided_use=SPLIT) > self.target:
            self.split_joins[split_join_idx] = UNDECIDED  # Clear index
            return False
        if self._calculate_total(undecided_use=JOIN) < self.target:
            self.split_joins[split_join_idx] = UNDECIDED  # Clear index
            return False

        # Descend
        # Left -> join
        self.split_joins[split_join_idx] = JOIN
        if self._smart_tree_search(split_join_idx=split_join_idx+1):
            return True

        # Right -> split
        self.split_joins[split_join_idx] = SPLIT
        if self._smart_tree_search(split_join_idx=split_join_idx+1):
            return True

        # Failed
        self.split_joins[split_join_idx] = UNDECIDED  # Clear index
        return False


def T(N: int, msg: str, real_answer: Optional[int] = None):
    # Calculate
    start = time.time()
    total = 0
    splitter = Splitter()
    for n in range(2, int(math.sqrt(N)) + 1):
        if splitter.split(n_sqr=n*n, n=n):
            # print(f"{n*n} -> {res_list}")
            total += n ** 2
    total_time = time.time() - start

    # Print answer:
    s = f"{msg} T({N}) = {total}"
    if real_answer is not None:
        s = s + f"\t real answer = {real_answer}"
    s = s + f"\t total time={total_time}s"
    s = s + f"\t time1={splitter.total_time_1}"
    s = s + f"\t time2={splitter.total_time_2}"
    print(s)



def main():
    # n = 82
    # s = Splitter()
    # print(s.split(n_sqr=n*n, n=n))
    # return

    start = time.time()
    T(N=10 ** 4, msg="10^4", real_answer=41333)
    T(10 ** 5, msg="10^5")
    T(10 ** 6, msg="10^6")
    T(10 ** 7, msg="10^7")
    T(10 ** 8, msg="10^8")
    T(10 ** 9, msg="10^9")
    T(10 ** 12, msg="10^12")


if __name__ == "__main__":
    main()
