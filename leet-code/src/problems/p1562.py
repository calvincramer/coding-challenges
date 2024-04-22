import main


class Solution:
    def findLatestStep(self, arr: list[int], m: int) -> int:
        n = len(arr)
        arr = [None] + arr  # 1 index
        groupSizes = dict()  # groupNumber to length
        ans = -1
        inf = n * 2
        T = [None] * (n + 2)  # edge case None at index 0 and n+1 for simplicity

        for step in range(1, n + 1):
            i = arr[step]
            lgn = inf if T[i - 1] is None else T[i - 1]
            rgn = inf if T[i + 1] is None else T[i + 1]
            T[i] = step  # step is group num
            newSize = 1  # include i
            # Coalesce group nums
            if lgn is not inf:
                if lgn != step:
                    T[i] = lgn
                    newSize += groupSizes[lgn]  # joined with left
                    # left group change size, if previous size is m then the last step had size we needed
                    if groupSizes[lgn] == m:
                        ans = max(ans, step - 1)
            if rgn is not inf:
                if rgn != step:
                    # right group will change size
                    if groupSizes[rgn] == m:
                        ans = max(ans, step - 1)
                    if lgn is not inf and T[i] == T[i - 1]:  # also coalesced to the left?
                        # overwrite all right group with left group
                        for j in range(i + 1, n + 1):
                            if T[j] == None:
                                break
                            T[j] = lgn
                            newSize += 1
                        del groupSizes[rgn]
                    else:  # only have right group
                        T[i] = rgn
                        newSize += groupSizes[rgn]
            groupSizes[T[i]] = newSize

            # check new group size
            if newSize == m:
                ans = max(ans, step)
        return ans


test = main.UTest()
sol = Solution()
test.test_eq(sol.findLatestStep(arr=[3, 5, 1, 2, 4], m=1), 4)
test.test_eq(sol.findLatestStep(arr=[3, 1, 5, 4, 2], m=2), -1)
test.test_eq(sol.findLatestStep(arr=[9, 7, 8, 3, 1, 6, 5, 2, 10, 4], m=5), 8)
test.test_eq(sol.findLatestStep(arr=[1, 2, 3, 4, 5, 6, 7, 8, 9], m=1), 1)
test.test_eq(sol.findLatestStep(arr=[9, 8, 7, 6, 5, 4, 3, 2, 1], m=1), 1)
