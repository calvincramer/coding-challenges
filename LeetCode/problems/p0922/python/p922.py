#[COMPLETED]

# Could make more memory efficient by not storing all bad indices, but finding them dynamically

class Solution:
    # even indices have even numbers
    # odd indices have off numbers
    def sortArrayByParityII(self, A):
        # Based on assumption that there is an even number of even and odd numbers
        # and if there are numbers they are misplaced, then there should be the same
        # number of odd and even numbers displaced
        bad_even_ind = []
        bad_odd_ind = []
        for i, n in enumerate(A):
            if i % 2 == 0:
                if n % 2 != 0:
                    bad_even_ind.append(i)
            elif n % 2 == 0:
                bad_odd_ind.append(i)

        for i in range(len(bad_odd_ind)):
            # Swap
            temp = A[bad_odd_ind[i]]
            A[bad_odd_ind[i]] = A[bad_even_ind[i]]
            A[bad_even_ind[i]] = temp
        return A


sol = Solution()
print(sol.sortArrayByParityII([4,2,5,7]), "expected ?")
