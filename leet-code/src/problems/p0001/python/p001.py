#[COMPLETED]

class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        hashTable = {}
        for i in range(0, len(nums)):
            hashTable.update({nums[i]: i})
        for i in range(0, len(nums)):
            comp = target - nums[i]
            if comp in hashTable and hashTable.get(comp) != i:
                return [i, hashTable.get(comp)]

        return "No match found"



s = Solution()
print(s.twoSum([3,2,4], 6))

l = [1,2,6,54,2,6,7]
for i,num in enumerate(l):
    print(str(i) + "\t" + str(num))