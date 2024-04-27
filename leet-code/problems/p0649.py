#[IN PROGRESS]

class Solution:
    # Optimum strategy is to block the person from the opposite group after you
    def predictPartyVictory(self, senate: str) -> str:
        active = [True] * len(senate)
        i = 0
        while True:
            if active[i]:
                # Find next active from opposite group
                opponent = 'R' if senate[i] == 'D' else 'D'
                k = (i + 1) % len(senate)
                while k != i:
                    if active[k] and senate[k] == opponent:
                        break
                    k = (k+1) % len(senate)
                if k == i:
                    # No opponents found, return answer
                    return "Dire" if senate[i] == 'D' else "Radiant"
                active[k] = False
            i = (i+1) % len(senate)


sol = Solution()
print(sol.predictPartyVictory("RD"), "expected Radiant")
print(sol.predictPartyVictory("DR"), "expected Dire")
print(sol.predictPartyVictory("RDD"), "expected Dire")
print(sol.predictPartyVictory("DRRDRDRDRDDRDRDR"), "expected Radiant")