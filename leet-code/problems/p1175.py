import main


class Solution:
    def numPrimeArrangements(self, n: int) -> int:
        # k = number of prime numbers <= n
        # get number of permutations -> k!
        # number of permutations of rest of numbers (n-k)!
        mod = (10**9) + 7

        # Sieve of Eratosthenes
        primes = [True] * (n + 1)
        primes[0] = False
        primes[1] = False
        for i in range(2, n + 1):
            if primes[i] is False:
                continue
            for j in range(i * 2, n + 1, i):
                primes[j] = False

        k = sum([1 for p in primes if p])
        ans = 1
        for i in range(1, k + 1):
            ans = (ans * i) % mod
        for i in range(1, n - k + 1):
            ans = (ans * i) % mod
        return ans


sol = Solution()
test = main.UTest()
test.test_eq(sol.numPrimeArrangements(n=5), 12)
test.test_eq(sol.numPrimeArrangements(n=100), 682289015)
