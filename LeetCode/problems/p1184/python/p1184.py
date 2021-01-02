#[COMPLETED]

from typing import List

class Solution:
    def distanceBetweenBusStops(self, distance: List[int], start: int, destination: int) -> int:
        # Opti
        len_d = len(distance)
        # To the right
        l_distance = 0
        i = start
        while i != destination:
            l_distance += distance[i]
            i = (i+1) % len_d
        # To the left
        r_distance = 0
        i = start
        while i != destination:
            i = (i-1) % len_d   # Take the previous distance
            r_distance += distance[i]
        return min(l_distance, r_distance)


sol = Solution()
print(sol.distanceBetweenBusStops([1,2,3,4], 0, 1), "expected 1")
