#[COMPLETED]

import time

class Solution:
    def countPrimes(self, n):
        if n < 2: return 0
        prime = [True] * n  #true means prime
        for i in range(2, n):
            if prime[i] == False:
                continue
            for j in range(2*i, n, i):
                prime[j] = False
        count = 0

        for b in prime:
            if b:
                count += 1
        return count - 2    #don't count 0 and 1


start = time.clock()

s = Solution()
print(s.countPrimes(1200000))

end = time.clock()
print(end - start)
