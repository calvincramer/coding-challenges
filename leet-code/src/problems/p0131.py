#[IN PROGRESS]

class Solution:
    def partition(self, s):
        pals = []
        for length in range(len(s), 0, -1):
            pal_of_len = []
            # Get all palindromes of certain length
            for start in range(0, len(s) - length + 1):
                    # Check if palindome
                    substr = s[start: start+length]
                    lower_half = substr[0: len(substr)//2]
                    upper_half = substr[len(s): len(substr)//2 - 1: -1]
                    if len(substr) % 2 == 1:
                        upper_half = upper_half[0:-1]
                    if lower_half == upper_half:
                        pal_of_len.append(substr)
                    print(substr, lower_half, upper_half)
            if len(pal_of_len) > 0:
                pals.append(pal_of_len)
        return pals

# Not doing paritioning, what exactly is partitioning?

sol = Solution()
print(sol.partition("aab"))
s = "abcd"
print()
print(s)
print(s[0:len(s)//2])
print(s[len(s):(len(s)//2) -1:-1])