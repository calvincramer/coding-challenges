#[COMPLETED]

class Solution(object):
    """
    :type A: List[List[int]]
    :rtype: List[List[int]]
    """
    def flipAndInvertImage(self, A):
        if A is None:
            return None
        ret = []
        for row in A:
            flipped = row[::-1]
            flip_inv = [(0 if n == 1 else 1) for n in flipped]
            ret.append(flip_inv)
        return ret


sol = Solution()
inp =  [[1,1,0],[1,0,1],[0,0,0]]
out = sol.flipAndInvertImage(inp)
#print("inp=", inp)
#print("out=", out)
assert out == [[1,0,0],[0,1,0],[1,1,1]]

inp = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
out = sol.flipAndInvertImage(inp)
assert out == [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

print("passed all")



