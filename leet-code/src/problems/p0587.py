#[IN PROGRESS]

from typing import List

# Start with a random point
# rotate line about that point (choose rotation direction, lets do counter-clockwise)
# hit other point with line
# Continue rotating line from new point
# keep track of points hit
# stop when hit same point multiple times
# circular hit of points is smallest fence around points
# may want to do a ray instead of a line

# theta = dot(u,v) / (mag(u) * mag(v))

import math

def dot(v1, v2):
    return v1[0]*v2[0] + v1[1]*v2[1]
def det(v1, v2):
    return v1[0]*v2[1] - v1[1]*v2[0]
def mag(v):
    return (v[0]**2 + v[1]**2) ** 0.5
def dist_sqr(p1, p2):   # Squared distance
    return (p2[0] - p1[0]) ** 2 + (p2[1] - p1[1]) ** 2
def angle_between(v1, v2):
    return math.atan2(det(v1, v2), dot(v1, v2))

class Solution:
    # Try 1: assuming the rope goes in a "circle", cannot cross over itself.
    def outerTrees_shrink_wrap_circle(self, points: List[List[int]]) -> List[List[int]]:
        if not points or len(points) == 0:
            return None
        elif len(points) <= 3:
            return points
        # Convert List[List] to List[tuple] for hashability
        points = [tuple(p) for p in points]
        traversed = []  # List of points gone over
        seen = {}       # O(1) lookup of traversed (point to count seen)
        prev_point = points[0]
        curr_point = points[1]
        curr_i = 1
        traversed.append(prev_point)
        seen[points[0]] = 1
        # Stop when seen a point 3 times (2 may only be a small loop)
        while curr_point not in seen or seen[curr_point] < 3:
            # Let curr_point be the origin
            prev_point_from_curr = (prev_point[0] - curr_point[0], prev_point[1] - curr_point[1])
            # flip the previous point to "be out in front" of the current point
            # this is so we can optimize for the smallest non-negative angle
            prev_point_from_curr = (-prev_point_from_curr[0], -prev_point_from_curr[1])
            # Find all angles between previous line and line between this point and all other points
            min_ind_p = -1
            min_ang = 100     # Done in radians, so don't care
            for i, p in enumerate(points):
                if p == curr_point or p == prev_point:
                    continue
                ang = angle_between(prev_point_from_curr, (p[0] - curr_point[0], p[1] - curr_point[1]) )
                if 0 <= ang < min_ang:
                    min_ang = ang
                    min_ind_p = i
                elif ang >= 0 and (ang - min_ang) <= 0.00001:
                    # Take minimum distance point
                    if dist_sqr(curr_point, p) < dist_sqr(curr_point, prev_point_from_curr):
                        min_ang = ang
                        min_ind_p = i
            # Update list, seen
            traversed.append(curr_point)
            if curr_point in seen:
                seen[curr_point] = seen[curr_point] + 1
            else:
                seen[curr_point] = 1
            # Update current point, curr_i and prev point
            prev_point = curr_point
            curr_point = points[min_ind_p]
            curr_i = min_ind_p
        # Get perimeter
        perim = []
        found_in_perim = set()
        curr = traversed[-1]
        ind = len(traversed) - 1
        while curr not in found_in_perim:
            perim.append(curr)
            found_in_perim.add(curr)
            ind -= 1
            curr = traversed[ind]
        # perim = traversed[seen[curr_point][0]:]
        # Convert to list of list
        perim = [list(p) for p in perim]
        return perim

# Distance of adjacent points
def perimeter_dist(points):
    dist = 0
    for i in range(1, len(points)):
        dist += dist_sqr(points[i], points[i-1]) ** 0.5
    dist += dist_sqr(points[-1], points[0]) ** 0.5
    return dist

sol = Solution()
def check(inp, exp):
    out = sol.outerTrees(inp)
    print("inp: ", str(inp))
    print("out: ", str(out), "\n\tdist = ", perimeter_dist(out))
    print("exp: ", str(exp), "\n\tdist = ", perimeter_dist(exp))
    print("out = exp?: ", str(out == exp))
    print()

check([[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]], [[1,1],[2,0],[4,2],[3,3],[2,4]])
check([[1,2],[2,2],[4,2]], [[1,2],[2,2],[4,2]])

# This test case shows that the rope can cross over itself. This is legal :(
check([[0,2],[1,1],[2,2],[2,4],[4,2],[3,3]], [[2,4],[3,3],[4,2],[0,2],[1,1]])
