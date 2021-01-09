#[IN PROGRESS]

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
class NestedInteger:
	def __init__(self, value=None):
		"""
		If value is not specified, initializes an empty list.
		Otherwise initializes a single integer equal to value.
		"""
		self.val = value
		self.lst = None

	def isInteger(self):
		"""
		@return True if this NestedInteger holds a single integer, rather than a nested list.
		:rtype bool
		"""
		return self.lst != None

	def add(self, elem):
		"""
		Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
		:rtype void
		"""
		if self.lst == None:
			self.lst = [elem]
			self.val = None
			return
		self.lst.append(elem)

	def setInteger(self, value):
		"""
		Set this NestedInteger to hold a single integer equal to value.
		:rtype void
		"""
		self.lst = None
		self.val = value

	def getInteger(self):
		"""
		@return the single integer that this NestedInteger holds, if it holds a single integer
		Return None if this NestedInteger holds a nested list
		:rtype int
		"""
		return self.val

	def getList(self):
		"""
		@return the nested list that this NestedInteger holds, if it holds a nested list
		Return None if this NestedInteger holds a single integer
		:rtype List[NestedInteger]
		"""
		return self.lst

	def printNI(self):
		print(str(self.val) + "\t" + str(self.lst))

class Solution:
	def deserialize(self, s):
		"""
		:type s: str
		:rtype: NestedInteger
		"""
		if s[0] == '['

	def takeInt(self, s):
		"""
		return integer from start of string
		"""
		nums = ['0','1','2','3','4','5','6','7','8','9']
		prefix = ""
		while len(s) > 0 and s[0] in nums:
			prefix = prefix + s[0]
			s = s[1:]
		return [int(prefix), s]

		

ni = NestedInteger()
ni.printNI()
ni.setInteger(5)
ni.printNI()
ni.add(2)
ni.printNI()
ni.add(4)
ni.printNI()

s = Solution()
str = "2345a"
[ans, str] = s.takeInt(str)
print(ans, str)