#[COMPLETED]

class Solution:
    def findKthLargest(self, nums, k: int) -> int:
        q = [None] * k
        q[0] = nums[0]
        num_in_q = 1     # Last filled in spot in q
        for n in nums[1:]:
            # Insert n in q inorder
            if num_in_q < k:
                i = -1
                for j in range(num_in_q):
                    if n >= q[j]:
                        i = j
                        break
                if i == -1:
                    i = num_in_q
                q.insert(i, n)
                q.pop()
                num_in_q += 1

            elif n <= q[-1]:
                continue
            else:
                # Insert in order
                i = -1
                for j in range(len(q)):
                    if n >= q[j]:
                        i = j
                        break
                q.insert(i, n)
                q.pop()

        return q[-1]


sol = Solution()
print(sol.findKthLargest([3,2,1,5,6,4], 2), " expected 5")
print(sol.findKthLargest([3,2,3,1,2,4,5,5,6], 4), " expected 4")
