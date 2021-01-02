#[IN PROGRESS]

class Solution:
    def twoSum(self, numbers, target):
        """
        :type numbers: List[int]
        :type target: int
        :rtype: List[int]
        """
        # speed up:
        # create list of unique numbers
        # binary search for second number
        
        for i in range(0, len(numbers)):
            for j in range(0, len(numbers)):
                k = len(numbers) - 1 - j
                if i == k:
                    continue
                if numbers[i] + numbers[k] == target:
                    return [i+1, k+1]
        return None

s = Solution()
print(s.twoSum([2,7,11,15],9))