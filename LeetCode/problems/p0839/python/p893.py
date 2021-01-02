#[IN PROGRESS]

from typing import List

class Solution:
    def numSimilarGroups(self, A: List[str]) -> int:
        if not A or len(A) == 0:
            return 0
        if len(A) == 1:
            return 1
        max_group_num = 0       # Start at group #2, since we skip over the first word
        ind_to_group = {}       # Map each index in A to a group number
        for i in range(0, len(A)):
            group_found = False
            for j in range(0, i):
                if i == j:
                    continue
                if self.similar(A[i], A[j]):
                    ind_to_group[i] = ind_to_group[j]   # Join group
                    group_found = True
                    break
            if not group_found:
                max_group_num += 1
                ind_to_group[i] = max_group_num
        return max_group_num

    # Assumed to have same length
    def similar(self, a: str, b: str) -> bool:
        num_diff = 0
        for char_a, char_b in zip(a, b):
            if char_a != char_b:
                num_diff += 1
                if num_diff >= 3:
                    return False
        return num_diff <= 2


sol = Solution()
print(sol.numSimilarGroups(["tars","rats","arts","star"]), "expected 2")
print(sol.numSimilarGroups(["kccomwcgcs","socgcmcwkc","sgckwcmcoc","coswcmcgkc","cowkccmsgc","cosgmccwkc","sgmkwcccoc","coswmccgkc","kowcccmsgc","kgcomwcccs"]), "expected 5")

# Close, can't find why its wrong
