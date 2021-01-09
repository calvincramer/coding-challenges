#[COMPLETED]

class Solution:
    def plusOne(self, digits):
        index = len(digits) - 1
        digits[index] = digits[index] + 1
        while digits[index] == 10:
            digits[index] = 0
            if index - 1 < 0:
                digits.insert(0, 1)
            else:
                digits[index-1] = digits[index-1] + 1
            index = index - 1
        return digits


s = Solution()
lst = [9,9]
print(lst)
lst = s.plusOne(lst)
print(lst)
l = []


