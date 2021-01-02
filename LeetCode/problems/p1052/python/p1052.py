#[COMPLETED]

from typing import List

class Solution:
    def maxSatisfied(self, customers: List[int], grumpy: List[int], X: int) -> int:
        if not customers:
            return 0
        if not grumpy:
            return sum(customers)
        if X >= len(customers):
            return sum(customers)
        # Opti
        len_c = len(customers)
        # Want to find the best index to maximize the number of customers that would be not satisfied
        max_unsat = -1
        max_unsat_i = -1
        # Initial
        cur_unsat = 0
        cur_i = 0   # The start of being not grumpy for X minutes
        for i in range(0, X):
            cur_unsat += customers[i] * grumpy[i]
        # Stepping
        while True:
            # Check current
            if cur_unsat > max_unsat:
                max_unsat = cur_unsat
                max_unsat_i = cur_i
            # Break?
            if cur_i + X >= len_c:
                break
            # Update
            cur_unsat -= customers[cur_i] * grumpy[cur_i]
            cur_unsat += customers[cur_i + X] * grumpy[cur_i + X]
            # Inc
            cur_i += 1
        # Now have best index
        total = 0
        for i in range(max_unsat_i, max_unsat_i+X):
            total += customers[i]
            customers[i] = 0
        total += sum( [c if g == 0 else 0 for c,g in zip(customers, grumpy)] )
        return total


sol = Solution()
print(sol.maxSatisfied(customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3), "expected 16")
print(sol.maxSatisfied([4,10,10], [1,1,0], 2), "expected 24")
