#[IN PROGRESS]

class Solution(object):
    """
    Combines two integer intervals into its separate intervals
    [a,b]+[c,d] -> [a,b] when c >= a and d <= b
    [a,b]+[c,d] -> [c,d] when a >= c and b <= d
    :type existing_interval: List[int]
    :type new_interval: List[int]
    :rtype: List[List[int]]
    """
    def combineSplit(self, interval_one, interval_two):
        #1 +++
        #2 --- ===
        #3 --++++++++
        #4 +++==
        #5 --+++--
        #6 --+==    -++=
        start = None
        if interval_one[0] < interval_two[0]:
            start = interval_one
        elif interval_one[0] == interval_two[0]:
            start = interval_one if interval_one[1] < interval_two[1] else interval_two
        else:
            start = interval_two
        end = interval_one if start == interval_two else interval_two

        if start[0] == end[0] and start[1] == end[1]: # 1. Equal intervals
            return [start]
        elif start[1] < end[0]: # 2.
            return [ start, end]
        elif start[1] == end[1]: # 3.
            return [ [start[0], end[0] - 1], end ]
        elif start[1] < end[1] and start[0] == end[0]: # 4.
            return [ start, [start[1] + 1, end[1]] ]
        elif end[0] > start[0] and end[1] < start[1]: # 5.
            return [ [start[0], end[0] - 1], end, [end[1] + 1, start[1]] ]
        else: # 6.
            return [ [start[0], end[0] - 1], [end[0], start[1]], [start[1] + 1, end[1]] ]


    def findMinArrowShots(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        # While don't have any balloons left, pop the maximum number of overlapping balloons
        # take out the balloons popped, increment counter
        num_shots = 0


        return num_shots

sol = Solution()
#print(sol.combineSplit([0,3],[0,3]), "expected [[0,3]]")
#print(sol.combineSplit([0,0],[0,0]), "expected [[0,0]]")
#print(sol.combineSplit([0,1],[4,5]), "expected [[0,1],[4,5]]")
#print(sol.combineSplit([0,5],[3,5]), "expected [[0,2],[3,5]]")
#print(sol.combineSplit([0,3],[0,5]), "expected [[0,3],[4,5]]")
#print(sol.combineSplit([0,6],[2,4]), "expected [[0,1],[2,4],[5,6]]")
#print(sol.combineSplit([0,5],[1,4]), "expected [[0,0],[1,4],[5,5]")
#print(sol.combineSplit([0,5],[2,7]), "expected [[0,1],[2,5],[6,7]]")


