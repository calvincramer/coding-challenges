#[COMPLETED]

from typing import List

class Solution:
    def checkStraightLine(self, coordinates: List[List[int]]) -> bool:
        if not coordinates:
            return False
        if len(coordinates) <= 1:
            return False
        if len(coordinates) == 2:
            return True
        def get_slope_x_inter(p0, p1):
            # Handle vertical case
            if p0[0] == p1[0]:
                return float("inf"), p0[0]
            # Handle horizontal case
            if p0[1] == p1[1]:
                return 0, float("inf")
            t_s = (p1[1] - p0[1]) / (p1[0] - p0[0])
            return t_s, -p0[1] / t_s + p0[0]
        c = coordinates
        slope, x_inter = get_slope_x_inter(c[0], c[1])
        for i in range(2, len(c)):
            temp_slope, temp_inter = get_slope_x_inter(c[0], c[i])
            if slope != temp_slope or x_inter != temp_inter:
                return False
        return True

"""
(x1, y1)
(x2, y2)

y = (x - x_i)slope + y_i
slope = (y_2 - y_1) / (x_2 - x_1)


"""