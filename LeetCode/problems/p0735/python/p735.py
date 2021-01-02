#[COMPLETED]

class Solution:
    def asteroidCollision(self, asteroids):
        popped_left = []
        popped_right = []

        while len(asteroids) > 0:
            if asteroids[0] < 0:
                popped_left.append(asteroids.pop(0))
                continue
            if asteroids[-1] > 0:
                popped_right.insert(0, asteroids.pop())
                continue
            # Resolve collision
            i = 0
            while asteroids[i] > 0:
                i += 1
            i -= 1
            while i >= 0 and i+1 < len(asteroids) and asteroids[i] > 0 and asteroids[i+1] < 0:
                if asteroids[i] > abs(asteroids[i+1]):
                    del asteroids[i+1]
                elif asteroids[i] == abs(asteroids[i+1]):
                    del asteroids[i:i+2]
                    i -= 1
                else:
                    del asteroids[i]
                    i -= 1
        popped_left.extend(popped_right)
        return popped_left



sol = Solution()
print(sol.asteroidCollision([5, 10, -5]), "expected [5,10]")
print(sol.asteroidCollision([8, -8]), "expected []")
print(sol.asteroidCollision([10, 2, -5]), "expected [10]")
print(sol.asteroidCollision([-2, -1, 1, 2]), "expected [-2, -1, 1, 2]")
