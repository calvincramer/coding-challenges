#[COMPLETED]

class Solution:
    dir_off = {0:(1,0), 1:(0,1), 2:(-1,0), 3:(0,-1)}
    def isRobotBounded(self, instructions: str) -> bool:
        instructions = instructions * 4 # Now must loop if it eventually loops
        dir = 0
        total_x = 0
        total_y = 0
        for c in instructions:
            if c == "L":
                dir = (dir - 1) % 4
            elif c == "R":
                dir = (dir + 1) % 4
            else:
                total_x, total_y = total_x + self.dir_off[dir][0], total_y + self.dir_off[dir][1]
        return total_x == 0 and total_y == 0


sol = Solution()
print(sol.isRobotBounded("GGLLGG"), "expected True")
print(sol.isRobotBounded("GG"), "expected False")
print(sol.isRobotBounded("GL"), "expected True")
