#[COMPLETED]

class Solution:
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        s = str(x)
        for i in range(0, (len(s) // 2) + 1):
            #print(str(s[i]) + str(s[-i - 1]))
            if s[i] != s[-i - 1]:
                return False
        return True

s = Solution()
for i in range(0, 40):
    st = str(i) + " "
    if s.isPalindrome(i):
        st = st + " pal"
    print(st)
