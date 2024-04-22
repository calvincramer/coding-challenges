class Solution:
    def intersection(self, nums: list[list[int]]) -> list[int]:
        nums = [set(foo) for foo in nums]
        min_set = nums[0]
        min_size = len(nums[0])
        for s in nums:
            if len(s) < min_size:
                min_size = len(s)
                min_set = s
        ans = set()
        for n in min_set:
            good = True
            for s in nums:
                if n not in s:
                    good = False
                    break
            if good:
                ans.add(n)
        return sorted(list(ans))


sol = Solution()
print(f"{sol.intersection([[3,1,2,4,5],[1,2,3,4],[3,4,5,6]])}, expected [3, 4]")
print(f"{sol.intersection([[1,2,3],[4,5,6]])}, expected []")
