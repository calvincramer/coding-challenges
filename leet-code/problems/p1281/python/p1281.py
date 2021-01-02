#[COMPLETED]

class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        def prod(lst):
            p = 1
            for el in lst:
                p *= el
            return p
        pod = prod([int(c) for c in str(n)])
        sod = sum([int(c) for c in str(n)])
        return pod - sod
