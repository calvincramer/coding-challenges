#[COMPLETED]

from typing import List
from collections import defaultdict

def prime_factors(n):
    i = 2
    while i * i <= n:
        if n % i == 0:
            n /= i
            yield i
        else:
            i += 1
    if n > 1:
        yield int(n)

def prime_divisors_unique(n):
    return set(prime_factors(n))

class Solution:
    def hasGroupsSizeX(self, deck: List[int]) -> bool:
        freq = defaultdict(int)
        for n in deck:
            freq[n] += 1
        if len(freq) == 0:
            return False
        elif len(freq) == 1:
            return list(freq.values())[0] >= 2
        smallest_group = min(freq.values())
        if smallest_group == 1:
            return False
        # Determine if can make groups from smallest_group or any divisors of smallest_group
        divs = prime_divisors_unique(smallest_group)
        for div in divs:
            if all( (n % div == 0 for n in freq.values()) ):
                return True
        return False


print(list(prime_factors(100)))

sol = Solution()
assert sol.hasGroupsSizeX([1,2,3,4,4,3,2,1])
assert not sol.hasGroupsSizeX([1,1,1,2,2,2,3,3])
assert not sol.hasGroupsSizeX([1])
assert sol.hasGroupsSizeX([1,1])
assert sol.hasGroupsSizeX([1,1,2,2,2,2])
assert sol.hasGroupsSizeX([1,1,1,1,2,2,2,2,2,2])
print('good')
