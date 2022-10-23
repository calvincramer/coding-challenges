class Solution:
    def capitalizeTitle(self, title: str) -> str:
        words = list()
        for word in title.split(" "):
            if len(word) == 0:
                continue
            if len(word) <= 2:
                words.append(word.lower())
            else:
                words.append(f"{word[0].upper()}{word[1:].lower()}")
        return " ".join(words)


sol = Solution()
print(sol.capitalizeTitle("capiTalIze tHe titLe"))
print(sol.capitalizeTitle("First leTTeR of EACH Word"))
