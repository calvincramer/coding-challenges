#[COMPLETED]

class Solution:
	def inRange(self, height, width, y, x):
		return y >= 0 and x >= 0 and y < height and x < width

	def imageSmoother(self, M):
		"""
		:type M: List[List[int]]
		:rtype: List[List[int]]
		"""
		height = len(M)
		width =  len(M[0])
		smooth = [[0 for x in range(width)] for y in range(height)] 


		for y in range(0, height):
			for x in range(0, width):
				#print(smooth)
				numNeighbors = 1
				total = M[y][x]
				if self.inRange(height, width, y - 1, x):
					numNeighbors = numNeighbors + 1
					total = total + M[y-1][x]
				if self.inRange(height, width, y - 1, x + 1):
					numNeighbors = numNeighbors + 1
					total = total + M[y-1][x+1]
				if self.inRange(height, width, y, x + 1):
					numNeighbors = numNeighbors + 1
					total = total + M[y][x+1]
				if self.inRange(height, width, y + 1, x + 1):
					numNeighbors = numNeighbors + 1
					total = total + M[y+1][x+1]
				if self.inRange(height, width, y + 1, x):
					numNeighbors = numNeighbors + 1
					total = total + M[y+1][x]
				if self.inRange(height, width, y + 1, x - 1):
					numNeighbors = numNeighbors + 1
					total = total + M[y+1][x-1]
				if self.inRange(height, width, y, x - 1):
					numNeighbors = numNeighbors + 1
					total = total + M[y][x-1]
				if self.inRange(height, width, y - 1, x - 1):
					numNeighbors = numNeighbors + 1
					total = total + M[y-1][x-1]
				smooth[y][x] = total // numNeighbors
		return smooth
		
#inp = [[1,1,1],[1,0,1],[1,1,1]]
#print(inp)
#s = Solution()
#print(s.imageSmoother(inp))

inp = [[2,3,4],[5,6,7],[8,9,10],[11,12,13],[14,15,16]]
print(inp)
s = Solution()
print(s.imageSmoother(inp))

