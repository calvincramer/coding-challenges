#[COMPLETED]

import time

class Solution:
    def maxArea(self, height) -> int:
        left = 0
        right = len(height) - 1
        max_area = 0
        while True:
            if left > right:
                break
            height_l = height[left]
            height_r = height[right]
            temp_area = (right - left) * min(height_l, height_r)
            if temp_area > max_area:
                max_area = temp_area
            if height_l < height_r:
                left += 1
            else:
                right -= 1
        return max_area


    # O n^2 worst case
    def maxArea_bad(self, height) -> int:
        import heapq
        max_num = 0
        length = len(height)
        max_area = 0

        # set of indices for increasing numbers
        # increasing = set()
        # for n in height:
        #     if n > max_num:
        #         max_num = n
        #         increasing.add(n)

        # List of pointers to next index of the smaller index (skip over increasing numbers)
        # pointers = [-1] * length
        # for i in range(length):
        #     pass


        # Maximum potential area if this index can make an area with the right side of the box
        max_potential = []  # As priority q, areas as negative value
        for i,n in enumerate(height):
            temp_a = (length - i - 1) * n
            heapq.heappush(max_potential, (-temp_a, i))

        # Main algo
        while max_potential:
            (area, i) = heapq.heappop(max_potential)
            # Find max area for index i now
            for j in range(i + 1, length):
                temp_area = (j - i) * min(height[i], height[j])
                if temp_area > max_area:
                    max_area = temp_area
            # Remove all items in max_potential whose area is <= current max_area
            cur = 0
            while cur < len(max_potential):
                if max_potential[cur][0] * -1 <= max_area:
                    max_potential.pop(cur)
                else:
                    cur += 1
        return max_area

    def maxArea_no_decreasing(self, height) -> int:
        increasing = set()  # set of indices for increasing numbers
        max_num = 0
        max_area = 0
        for i,cur_height in enumerate(height):
            if len(increasing) == 0:
                increasing.add(i)
                max_num = cur_height
                continue
            # Loop over and find areas between here and special group
            for j in increasing:
                temp_area = (i - j) * min(height[j], cur_height)
                if temp_area > max_area:
                    max_area = temp_area
            # Whether want to keep in special group?
            if cur_height > max_num:
                max_num = cur_height
                increasing.add(i)
        return max_area


    def maxArea_BRUTE_FORCE(self, height) -> int:
        max_area = 0
        for i in range(0, len(height) - 1):
            for j in range(i + 1, len(height)):
                temp = (j - i) * min(height[i], height[j])
                max_area = max(max_area, temp)
        return max_area


sol = Solution()
print(sol.maxArea([1,8,6,2,5,4,8,3,7]), "expected 49")
print(sol.maxArea([1,1]), "expected 1")
