class Solution:
    def shipWithinDays(self, weights: list[int], days: int) -> int:
        # Binary search on capacity of ship
        low = max(weights)
        high = sum(weights)
        while low < high:
            mid = (high + low) // 2
            daysNeeded = 1
            current = 0
            for w in weights:
                if current + w > mid:
                    daysNeeded += 1
                    current = 0
                current += w
            if daysNeeded > days:
                low = mid + 1  # Too small, take higher capacity
            else:
                high = mid  # Search smaller
        return low
