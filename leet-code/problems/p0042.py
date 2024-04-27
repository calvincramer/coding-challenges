from typing import List


class Solution:
    def trap(self, height: List[int]) -> int:
        # Answer from mcrystal on leet code
        l = 0
        r = len(height) - 1
        l_max = 0
        r_max = 0
        ans = 0

        while l < r:
            if height[l] <= height[r]:
                if height[l] > l_max:
                    l_max = height[l]
                else:
                    ans += l_max - height[l]
                l += 1
            else:
                if height[r] > r_max:
                    r_max = height[r]
                else:
                    ans += r_max - height[r]
                r -= 1
        return ans


sol = Solution()
print(sol.trap([0,1,0,2,1,0,1,3,2,1,2,1]), "expected 6")
print(sol.trap([4,2,0,3,2,5]), "expected 9")


"""
Solution 2: one extra array, similar to solution 1, use one array for everything

class Solution:
    def trap(self, height: List[int]) -> int:
        temp = [0] * len(height)

        peak = height[0]
        temp[0] = height[0]
        for i in range(1, len(height)):
            if height[i] > peak:
                peak = height[i]
            temp[i] = peak

        peak = height[-1]
        temp[-1] = peak
        ans = 0
        for i in range(len(height) - 1, -1, -1):
            if height[i] > peak:
                peak = height[i]
            ans += min(peak, temp[i]) - height[i]
        return ans
"""

"""
Solution 1: two extra arrays O(n) time, O(n) space

class Solution:
    def trap(self, height: List[int]) -> int:
        lr = [0] * len(height)
        rl = [0] * len(height)

        peak = height[0]
        lr[0] = height[0]
        for i in range(1, len(height)):
            if height[i] > peak:
                peak = height[i]
                lr[i] = peak
            else:
                lr[i] = peak

        peak = height[-1]
        rl[-1] = peak
        for i in range(len(height) - 1, -1, -1):
            if height[i] > peak:
                peak = height[i]
                rl[i] = peak
            else:
                rl[i] = peak

        ans = 0
        for i in range(len(height)):
            ans += min(lr[i], rl[i]) - height[i]
        return ans
"""
