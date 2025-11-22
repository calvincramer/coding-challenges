import main


class Solution:
    def geneDiff(self, gene_a: str, gene_b: str) -> int:
        diff = 0
        # Assume length of both genes is 8
        for i in range(8):
            if gene_a[i] != gene_b[i]:
                diff += 1
        return diff

    def minMutation(self, startGene: str, endGene: str, bank: list[str]) -> int:
        steps = 0
        population = set([startGene])
        while endGene not in population:
            nextPopulation = set()

            # For each individual in population mutate in all possible ways
            for gene_individual in population:
                for gene_mutation in bank:
                    # If is one off? then add to next population
                    if self.geneDiff(gene_individual, gene_mutation) == 1:
                        nextPopulation.add(gene_mutation)

            # Failures:
            if len(nextPopulation) == 0:
                return -1
            if steps > len(bank):
                return -1

            population = nextPopulation
            steps += 1

        return steps


test = main.UTest()
sol = Solution()
test.test_eq(sol.minMutation(startGene="AACCGGTT", endGene="AACCGGTA", bank=["AACCGGTA"]), 1)
test.test_eq(sol.minMutation(startGene="AACCGGTT", endGene="AAACGGTA", bank=["AACCGGTA", "AACCGCTA", "AAACGGTA"]), 2)
