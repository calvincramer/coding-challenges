#[COMPLETED]

class Solution(object):
    """
        :type nums: List[int]
        :rtype: bool
        """

    def canJump(self, nums):
        min_yes_index = len(nums) - 1
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] + i >= min_yes_index:
                min_yes_index = i
        return min_yes_index == 0

    """
    :type nums: List[int]
    :rtype: bool
    """
    def canJumpBad(self, nums):
        # It's a different problem: we need to see if we can reach the len(nums)-1 index
        if nums is None or len(nums) == 1:
            return True
        max_jump_index = -1
        i = 0
        n = nums[i]
        while True:
            if n == 0:
                if i >= max_jump_index:
                    return False
                else:
                    i = max_jump_index
            elif nums[i] + i > max_jump_index:
                max_jump_index = nums[i] + i

            i += 1
            if i >= len(nums):
                break;
            n = nums[i]
        return True


sol = Solution()
print(sol.canJump([2,3,1,1,4]), "expected true")
print(sol.canJump([3,2,1,0,4]), "expected false")
print(sol.canJump([0]), "expected true")
print(sol.canJump([1,1,1,0]), "expected true")


"""
[2,3,1,1,4]
[_,_,_,_,y]
[_,-,-,y,y]
[_,_,y,y,y]
[_,y,y,y,y]
[y,y,y,y,y]

[3,2,1,0,4]
[_,_,_,_,y]
[_,_,_,n,y]
[_,_,n,n,y]
[_,n,n,n,y]
[n,n,n,n,y]

Set minimum yes to length - 1
Go from end of array to beginning
Keep track of minimum yes index
return minimum yes == 0
"""