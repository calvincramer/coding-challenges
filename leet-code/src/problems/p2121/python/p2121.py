class Solution:
    def getDistances(self, arr: list[int]) -> list[int]:
        def cal(arr):
            ans = [0] * len(arr)
            prev = {}  # num to (prev index, number of num, total)
            for i, num in enumerate(arr):
                if num not in prev:
                    prev[num] = (i, 1, 0)
                else:
                    ans[i] += prev[num][2] + prev[num][1] * (i - prev[num][0])
                    prev[num] = (i, prev[num][1] + 1, ans[i])
            return ans

        return [i + j for i, j in zip(cal(arr), cal(arr[::-1])[::-1])]


sol = Solution()
print(f"{sol.getDistances([2,1,3,1,2,3,3])}, expected [4,2,7,2,4,4,5]")
print(f"{sol.getDistances([10,5,10,10])}, expected [5,0,3,4]")


"""
Too Slow:
        # for i in range(len(arr)):
        #     for j in range(i + 1, len(arr)):
        #         if arr[i] != arr[j]:
        #             continue
        #         ans[i] += j - i
        #         ans[j] += j - i


Too Slow:
        c = dict()
        ans = [0] * len(arr)
        for i, n in enumerate(arr):
            if n not in c.keys():
                c[n] = [i]
            else:
                c[n].append(i)
        for n in c.keys():
            indices = c[n]
            for i in indices:
                dist = 0
                for j in indices:
                    if i == j:
                        continue
                    dist += abs(j - i)
                ans[i] = dist

"""
