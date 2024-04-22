#[COMPLETED]

from itertools import combinations

class Solution(object):
    """
    :type num: int
    :rtype: List[str]
    """
    def readBinaryWatch(self, num):
        lights = list(range(0,10))
        combs = combinations(lights, num)
        # first four spots are hours, rest are minutes
        times = []
        for c in list(combs):
            hour = 0
            minute = 0
            for dig in c:
                if dig < 4:
                    hour += 2 ** dig
                else:
                    minute += 2 ** (dig - 4)
            if hour <= 11 and minute <= 59:
                # Format output
                hour = str(hour)
                minute = str(minute)
                if len(minute) < 2:
                    minute = "0" + minute
                times.append(hour + ":" + minute)
        return times


sol = Solution()
out = sol.readBinaryWatch(2)
print("len out= ", len(out))
cor = ["0:03","0:05","0:06","0:09","0:10","0:12","0:17","0:18","0:20","0:24","0:33","0:34","0:36","0:40","0:48","1:01","1:02","1:04","1:08","1:16","1:32","2:01","2:02","2:04","2:08","2:16","2:32","3:00","4:01","4:02","4:04","4:08","4:16","4:32","5:00","6:00","8:01","8:02","8:04","8:08","8:16","8:32","9:00","10:00"]
print("len cor= ", len(cor))
print("Out = ", sol.readBinaryWatch(2))
print("Cor = ", cor)
for s in cor:
    if s not in out:
        print(s, "in cor, not in out")
for s in out:
    if s not in cor:
        print(s, "in out, not in cor")
