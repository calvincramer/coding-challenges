#[IN PROGRESS]

class Solution:
    def champagneTower(self, poured: int, query_row: int, query_glass: int) -> float:
        row_prev_sat_val = (query_row + 1) * query_row // 2
        if poured <= row_prev_sat_val:
            return 0.0
        cur_row_sat_val = row_prev_sat_val + row_prev_sat_val + 1
        if poured >= cur_row_sat_val:
            return 1.0



sol = Solution()
print(sol.champagneTower(1, 1, 1), "expected 0.0")
print(sol.champagneTower(2, 1, 1), "expected 0.5")
print(sol.champagneTower(4, 2, 0), "expected 0.25")
print(sol.champagneTower(4, 2, 1), "expected 0.5")

