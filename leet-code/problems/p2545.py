import main


class Solution:
    def sortTheStudents(self, score: list[list[int]], k: int) -> list[list[int]]:
        score.sort(key=lambda x: x[k], reverse=True)
        return score


test = main.UTest()
sol = Solution()
test.test_eq(sol.sortTheStudents(score=[[10, 6, 9, 1], [7, 5, 11, 2], [4, 8, 3, 15]], k=2), [[7, 5, 11, 2], [10, 6, 9, 1], [4, 8, 3, 15]])
test.test_eq(sol.sortTheStudents(score=[[3, 4], [5, 6]], k=0), [[5, 6], [3, 4]])
